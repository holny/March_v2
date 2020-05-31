package com.hly.march2.controller;

import com.hly.march2.entity.Msg;
import com.hly.march2.entity.User;
import com.hly.march2.service.IBlogSeriesService;
import com.hly.march2.service.IBlogService;
import com.hly.march2.service.IUserService;
import com.hly.march2.utils.MyBase64Utils;
import com.hly.march2.utils.WrappedBeanCopier;
import com.hly.march2.vo.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;

/**
 * 这里作为大部分页面跳转逻辑处理
 */
@Controller
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private IBlogService blogService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IBlogSeriesService blogSeriesService;

    @Autowired
    private SessionDAO sessionDAO;

    /**
     * 这里是首页的推荐链接，我们可以在这修改
     */
    public enum MyLinkEnum {
        GITHUB_LINK(1, "https://www.hao123.com/"),
        WEIBO_LINK(2, "https://www.hao123.com/"),
        WEIXIN_LINK(3, "https://www.hao123.com/"),
        EMAIL_LINK(4, "https://www.hao123.com/"),
        DEFAULT_LINK(5, "https://www.hao123.com/");

        private Integer code;
        private String desc;

        MyLinkEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


    @RequestMapping("/login")
    public ModelAndView goLogin(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }


    @RequestMapping(value = "/home")
    public ModelAndView goHome(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        return mv;
    }

    @RequiresUser
    @RequiresPermissions(value = {"blog:query"})
    @RequestMapping(value = "/myblog")
    public ModelAndView goMyBlog(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("bloglist");
        return mv;
    }

    @RequiresUser
    @RequestMapping(value = "/my",method = RequestMethod.GET)
    public ModelAndView goMy(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("my");
        return mv;
    }

//    @RequestMapping(value = "/user/profile")
//    public ModelAndView goUserProfile(){
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("userprofile");
//        return mv;
//    }

    /**
     * 这里是获取的网站信息，比如发布了多少Blog，网站浏览量点赞量，用户量。首页推荐blog、series、用户、推荐链接。
     * 在前端home page sidebar显示。
     * @return
     */
    @ResponseBody
    @RequestMapping("/summary")
    public Msg getStatistic() {
        Subject subject = SecurityUtils.getSubject();
        String host = subject.getSession().getHost();

        HomeSummaryVO homeSummaryVO = new HomeSummaryVO();
        // 获取最近更新过的blog和最多View的Blog。3:7的比例
        List<BlogUserVo> blogUserVos1 = blogService.getLatestBlogUserByStatusAndLimit(BlogController.BlogStatusEnum.getGuestViewList(),3);
        List<BlogUserVo> blogUserVos2 = blogService.getMostHotBlogUserByStatusAndLimit(BlogController.BlogStatusEnum.getGuestViewList(),7);
        blogUserVos2.addAll(blogUserVos1);
        for(BlogUserVo v:blogUserVos2){
            MyBase64Utils.blogUserVoDecode(v);
        }
        homeSummaryVO.setBlogList(blogUserVos2);

        // 获取最多view的series
        List<SeriesVO> blogSeries = blogSeriesService.getSeriesCountGroupByBlogStatus(BlogController.BlogStatusEnum.getGuestViewList());
        homeSummaryVO.setBlogSeries(blogSeries);
        // 获取积分最高的用户
        List<User> topUserList = userService.getTopRankUserByLimit(10);
        List<UserVO> topUserVOList = WrappedBeanCopier.copyPropertiesOfList(topUserList, UserVO.class);
        homeSummaryVO.setTopUserList(topUserVOList);
        // 获取最近登录的用户
        List<User> LatestLoginUserList = userService.getLatestLoginUserByLimit(10);
        List<UserVO> LatestLoginUserVOList = WrappedBeanCopier.copyPropertiesOfList(LatestLoginUserList, UserVO.class);
        homeSummaryVO.setLatestLoginUserList(LatestLoginUserVOList);
        // 获取series总数
        Long seriesNum = blogSeriesService.countTotalSeriesByStatus(BlogController.BlogStatusEnum.getGuestViewList());
        homeSummaryVO.setSeriesNum(seriesNum);
        // 获取最高产的用户
        List<UserBriefStatisticsVO> topProductUserList = userService.countSumUserBlogByBlogExample(BlogController.BlogStatusEnum.getGuestViewList());
        homeSummaryVO.setTopProductUserList(topProductUserList);

        // 获取Blog总数,点赞总数，浏览总数，评论总数
        HomeSummaryVO homeSummaryVO1  = blogService.getCountBlogByStatus(BlogController.BlogStatusEnum.getGuestViewList());
        if(homeSummaryVO1.getBlogNum()!=null) {
            homeSummaryVO.setBlogNum(homeSummaryVO1.getBlogNum());
        }
        if(homeSummaryVO1.getLikeNum()!=null) {
            homeSummaryVO.setLikeNum(homeSummaryVO1.getLikeNum());
        }
        if(homeSummaryVO1.getViewNum()!=null) {
            homeSummaryVO.setViewNum(homeSummaryVO1.getViewNum());
        }
        if(homeSummaryVO1.getCommentNum()!=null) {
            homeSummaryVO.setCommentNum(homeSummaryVO1.getCommentNum());
        }
        // 获取总的链接，这里默认是用户Id12的
        User user = userService.getUserByUserId(new Long(12));
        if(user!=null){
            homeSummaryVO.setCsdnLink(user.getCsdnLink());
            homeSummaryVO.setEmailLink(user.getEmailLink());
            homeSummaryVO.setGithubLink(user.getGithubLink());
            homeSummaryVO.setWeiboLink(user.getWeiboLink());
            homeSummaryVO.setWechatLink(user.getWechatLink());
            homeSummaryVO.setQqLink(user.getQqLink());
            homeSummaryVO.setOtherLink(user.getOtherLink());
        }
        // 获取系统通知，也默认是ID12的
        homeSummaryVO.setNotice(user.getUserMotto());
        // 获取用户总数
        Long userSum = userService.countTotalUser(UserController.AccountStatusEnum.getNormalList());
        homeSummaryVO.setUserNum(userSum);
        // 获取在线用户数
        Collection<Session> sessions =  sessionDAO.getActiveSessions();
        homeSummaryVO.setLivingNum(new Long(sessions.size()));
        homeSummaryVO.setGuestIp(host);
        log.debug("----done");
        return Msg.success().add("summary",homeSummaryVO);
    }


}
