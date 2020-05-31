package com.hly.march2.controller;

import com.hly.march2.entity.BlogSeries;
import com.hly.march2.entity.Msg;
import com.hly.march2.entity.User;
import com.hly.march2.exception.MyNoPermissionException;
import com.hly.march2.exception.MyNotFoundException;
import com.hly.march2.shiro.ShiroHelper;
import com.hly.march2.service.IBlogSeriesService;
import com.hly.march2.service.IBlogService;
import com.hly.march2.service.IUserService;
import com.hly.march2.utils.MD5;
import com.hly.march2.utils.MyBase64Utils;
import com.hly.march2.utils.WrappedBeanCopier;
import com.hly.march2.vo.BlogStatisticsVO;
import com.hly.march2.vo.BlogUserVo;
import com.hly.march2.vo.UserBriefStatisticsVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.groups.Default;
import java.util.*;

@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;

    @Autowired
    private IBlogService blogService;

    @Autowired
    private IBlogSeriesService blogSeriesService;

    @Autowired
    private ShiroHelper shiroHelper;

    /**
     * 账号权限,code与保存在数据库的user_rights对应
     */
    public enum RolesEnum {
        SUPER_ADMIN_ROLE(1, "superAdmin"),
        ADMIN_ROLE(2, "admin"),
        LEVEL_4_ROLE(3, "level-4"),
        LEVEL_3_ROLE(4, "level-3"),
        LEVEL_2_ROLE(5, "level-2"),
        LEVEL_1_ROLE(6, "level-1"),
        USER_ROLE(7, "user");

        private Integer code;
        private String desc;

        RolesEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        private static List<Integer> adminCodeList;

        private static List<String> adminDescList;

        static {
            List<Integer> list1 = new ArrayList<>();
            list1.add(SUPER_ADMIN_ROLE.getCode());
            list1.add(ADMIN_ROLE.getCode());
            adminCodeList = Collections.unmodifiableList(list1);

            List<String> list2 = new ArrayList<>();
            list2.add(SUPER_ADMIN_ROLE.getDesc());
            list2.add(ADMIN_ROLE.getDesc());
            adminDescList = Collections.unmodifiableList(list2);

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

        public static List<Integer> getAdminCodeList(){
            return adminCodeList;
        }

        public static List<String> getAdminDescList(){
            return adminDescList;
        }
    }

    /**
     * 账号状态，code与数据库字段account_status对应
     */
    public enum AccountStatusEnum {
        NORMAL_STATUS(1, "normal"),
        FRIENDLY_STATUS(2, "friendly"),
        INVISIBLE_STATUS(3, "invisible"),
        INCOMPLETE_STATUS(4, "incomplete"),
        FORBIDDEN_STATUS(5, "forbidden"),
        DELETED_STATUS(6, "deleted");

        private Integer code;
        private String desc;

        AccountStatusEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        private static List<Integer> visibleList;

        private static List<Integer> forbiddenList;

        private static List<Integer> normalList;

        static {
            List<Integer> list1 = new ArrayList<>();
            list1.add(NORMAL_STATUS.getCode());
            list1.add(FRIENDLY_STATUS.getCode());
            visibleList = Collections.unmodifiableList(list1);

            List<Integer> list2 = new ArrayList<>();
            list2.add(DELETED_STATUS.getCode());
            list2.add(FORBIDDEN_STATUS.getCode());
            forbiddenList = Collections.unmodifiableList(list2);

            List<Integer> list3 = new ArrayList<>();
            list3.add(NORMAL_STATUS.getCode());
            list3.add(FRIENDLY_STATUS.getCode());
            list3.add(INVISIBLE_STATUS.getCode());
            list3.add(INCOMPLETE_STATUS.getCode());
            normalList = Collections.unmodifiableList(list3);
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

        public static List<Integer> getVisibleList() {
            return visibleList;
        }

        public static List<Integer> getForbiddenList() {
            return forbiddenList;
        }

        public static List<Integer> getNormalList() {
            return normalList;
        }
    }

    /**
     * 权限
     */
    public enum PermissionsEnum {
        BLOG_QUERY(1, "blog:query"),
        BLOG_CREATE(2, "blog:create"),
        BLOG_EDIT(3, "blog:edit"),
        BLOG_ALL(4, "blog:*"),
        USER_QUERY(5, "user:query"),
        USER_CREATE(6, "user:create"),
        USER_EDIT(7, "user:edit"),
        USER_ALL(8, "user:*");

        private Integer code;
        private String desc;

        PermissionsEnum(Integer code, String desc) {
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

    /**
     * 前端对User list的sort
     */
    public enum UserSortEnum {
        NO_SORT(1, null),
        LOGIN_TIME_ASC(2, "lastLoginTimeASC"),
        LOGIN_TIME_DESC(3, "lastLoginTimeDESC"),
        REGISTER_TIME_ASC(4, "registerTimeASC"),
        REGISTER_TIME_DESC(5, "registerTimeDESC"),
        BIRTHDAY_TIME_ASC(6, "birthdayTimeASC"),
        BIRTHDAY_TIME_DESC(7, "birthdayTimeDESC"),
        CREDIT_ASC(8, "creditASC"),
        CREDIT_DESC(9, "creditDESC");

        private Integer code;
        private String desc;

        UserSortEnum(Integer code, String desc) {
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

    /**
     * 更新用户信息。
     * 只供个人修改本人账号用户信息
     * @param updateUser
     * @param result
     * @return
     * @throws MyNoPermissionException
     */
    @RequiresAuthentication
    @RequiresRoles(value = {"user"})
    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public Msg updateUser(@Validated({Update.class}) User updateUser, BindingResult result) throws MyNoPermissionException {
        if(result.hasErrors()){
            // 校验失败
            Map<String,Object> map = new HashMap<>();
            List<FieldError> errors =  result.getFieldErrors();
            for(FieldError fieldError:errors){
                log.debug("Validated 错误的字段名"+fieldError.getField());
                log.debug("Validated 错误信息:"+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().setMsg(map.toString());
        }
        Subject subject = SecurityUtils.getSubject();

        User user = (User) subject.getPrincipal();
        if (!user.getUserId().equals(updateUser.getUserId())) {
            throw new MyNoPermissionException("当前账号不正确!");
        }
        // 修改密码
        if (updateUser.getNewPassword() != null) {
            if (updateUser.getUserPassword() != null) {
                User originUser = userService.getUserByUserId(updateUser.getUserId());
                // 需要MD5加密密码
                String oldSalt = originUser.getSalt();
                String oldPsw = updateUser.getUserPassword().trim();
                String olMd5PSW = MD5.MD5(oldPsw, oldSalt).toString();
                if (originUser.getUserPassword().equals(olMd5PSW)) {
                    // 需要MD5加密密码
                    String newSalt = MD5.getRandomSalt();
                    String newPSW = updateUser.getNewPassword().trim();
                    String newMd5PSW = MD5.MD5(newPSW, newSalt).toString();
                    User newUser = new User();
                    newUser.setUserId(updateUser.getUserId());
                    newUser.setSalt(newSalt);
                    newUser.setUserPassword(newMd5PSW);
                    userService.updateUserByUserId(newUser);
                    shiroHelper.clearAuthenticationInfo();
                    log.debug("update psw success. userId:{}",originUser.getUserId());
                    return Msg.success().setMsg("密码更新成功!");
                }
            }
            return Msg.fail().setMsg("旧密码不正确!");
        } else {
            // 更新基本信息
            User newUser = new User();
            newUser.setUserId(updateUser.getUserId());
            // 邮箱验证
            if (updateUser.getUserEmail() != null) {
                List<User> testUserList1 = userService.findUserByEmailOnlyFirst(updateUser.getUserEmail().trim());
                if (testUserList1.size() > 0) {
                    for (User u : testUserList1) {
                        if (u.getUserEmail().equals(updateUser.getUserEmail().trim())) {
                            if (!u.getUserId().equals(updateUser.getUserId())) {
                                // 数据库有邮箱相同，但UserId不同的账号，就返回失败
                                return Msg.fail().setMsg("邮箱已经注册过了!");
                            }
                        }
                    }
                }
                newUser.setUserEmail(updateUser.getUserEmail().trim());
            }
            // 手机号码验证
            if (updateUser.getUserTelephoneNumber() != null) {
                List<User> testUserList2 = userService.findUserByPhoneNum(updateUser.getUserTelephoneNumber().trim());
                if (testUserList2.size() > 0) {
                    for (User u : testUserList2) {
                        if (u.getUserTelephoneNumber().equals(updateUser.getUserTelephoneNumber().trim())) {
                            if (!u.getUserId().equals(updateUser.getUserId())) {
                                // 数据库有手机号码相同，但UserId不同的账号，就返回失败
                                return Msg.fail().setMsg("手机号码已经注册过了!");
                            }
                        }
                    }
                }
                newUser.setUserTelephoneNumber(updateUser.getUserTelephoneNumber().trim());
            }
            //去除危险信息
            if (updateUser.getUserName() != null) {
                newUser.setUserName(updateUser.getUserName().trim());
            }
            newUser.setUserBirthday(updateUser.getUserBirthday());
            newUser.setUserAge(updateUser.getUserAge());

            if (updateUser.getUserNickname() != null) {
                newUser.setUserNickname(updateUser.getUserNickname().trim());
            }
            if (updateUser.getUserSex() != null) {
                newUser.setUserSex(updateUser.getUserSex().trim());
            }
            if (updateUser.getUserMotto() != null) {
                newUser.setUserMotto(updateUser.getUserMotto().trim());
            }

            if (updateUser.getGithubLink() != null) {
                newUser.setGithubLink(updateUser.getGithubLink().trim());
            }
            if (updateUser.getWeiboLink() != null) {
                newUser.setWeiboLink(updateUser.getWeiboLink().trim());
            }
            if (updateUser.getWechatLink() != null) {
                newUser.setWechatLink(updateUser.getWechatLink().trim());
            }
            if (updateUser.getOtherLink() != null) {
                newUser.setOtherLink(updateUser.getOtherLink().trim());
            }
            userService.updateUserByUserId(newUser);
            shiroHelper.clearAuthenticationInfo();
            log.debug("update user info success. userId:{}",newUser.getUserId());
            return Msg.success().setMsg("用户信息更新成功!");
        }

    }

    /**
     * 获取某个用户的信息。
     * 只供个人查看本人信息。
     * @return
     * @throws MyNotFoundException
     */
    @RequiresUser
    @RequiresRoles(value = {"user"})
    @ResponseBody
    @RequestMapping(value = "/myinfo", method = RequestMethod.GET)
    public Msg getUser() throws MyNotFoundException {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        User myInfo = userService.getUserByUserId(user.getUserId());
        if (myInfo != null) {
            String host = subject.getSession().getHost();
            myInfo.setUserIp(host);
            myInfo.setUserPassword(null);
            myInfo.setSalt(null);
            myInfo.setUserSession(null);
            return Msg.success().add("user", myInfo);
        } else {
            throw new MyNotFoundException("不存在此账号!");
        }

    }

    /**
     * 获取某个用户的统计信息。
     * 是供公众查看某个账号的统计信息，作为个人profile
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/{userid}", method = RequestMethod.GET)
    public Msg getUserStatistic(@PathVariable("userid") Long userId) {
        log.debug("User Id:{}",userId);
        User originUser = userService.getUserByUserId(userId);
        if (originUser == null) {
            return Msg.fail().setMsg("无此用户!");
        }
        if (AccountStatusEnum.getForbiddenList().contains(originUser.getAccountStatus())) {
            return Msg.fail().setMsg("此用户已被封禁!");
        }
        String clauseOrderByUpdateTime = " blog_update_time DESC limit 5";
        String clauseOrderByViews = " blog_views DESC limit 5";
        UserBriefStatisticsVO userBriefStatisticsVO = WrappedBeanCopier.copyProperties(originUser, UserBriefStatisticsVO.class);

        List<BlogUserVo> blogUserVoOrderByHotList = blogService.getBlogSeriesTypeByUserIdAndSql(userId, BlogController.BlogStatusEnum.getGuestViewList(), clauseOrderByViews);
        if (blogUserVoOrderByHotList.size() > 0) {
            for (BlogUserVo blog : blogUserVoOrderByHotList) {
                MyBase64Utils.blogUserVoDecode(blog);
            }
            userBriefStatisticsVO.setMostHotBlogList(blogUserVoOrderByHotList);
        }

        List<BlogUserVo> blogUserVoOrderByNewList = blogService.getBlogSeriesTypeByUserIdAndSql(userId, BlogController.BlogStatusEnum.getGuestViewList(), clauseOrderByViews);
        if (blogUserVoOrderByNewList.size() > 0) {
            for (BlogUserVo blog : blogUserVoOrderByNewList) {
                MyBase64Utils.blogUserVoDecode(blog);
            }
            userBriefStatisticsVO.setLatestBlogList(blogUserVoOrderByNewList);
        }

        userBriefStatisticsVO.setBlogList(null);
        BlogStatisticsVO blogStatisticsVO = blogService.getUserSUMByUserId(userId);
        userBriefStatisticsVO.setBlogStatisticsVO(blogStatisticsVO);
        List<BlogSeries> blogSeriesList = blogSeriesService.getSeriesByUserId(userId);
        userBriefStatisticsVO.setBlogSeries(blogSeriesList);
        log.debug("User Id:{}  get user statistics done",userId);
        return Msg.success().add("userStatistics", userBriefStatisticsVO);
    }

    /**
     * 获取某个账号的blog list。
     * 供公众查看某个账号的profile
     * @param userId
     * @return
     * @throws MyNotFoundException
     */
    @RequestMapping(value = "/profile/{userid}")
    public ModelAndView goProfileBlog(@PathVariable("userid") Long userId) throws MyNotFoundException {
        log.debug("User Id:{}",userId);
        ModelAndView mv = new ModelAndView();
        User user = userService.getUserByUserId(userId);
        if (user != null) {
            if (AccountStatusEnum.getForbiddenList().contains(user.getAccountStatus())) {
                throw new MyNotFoundException("此账号已被封禁");
            }
            mv.addObject("userId", user.getUserId());
            mv.addObject("seriesId", -1);
            mv.addObject("seriesName", "");
            mv.addObject("userName", user.getUserNickname() + "(" + user.getUserName() + ")");
            mv.addObject("type", "blog");
            mv.setViewName("profile");
            log.debug("User Id:{}  success",userId);
            System.out.println("goProfileBlog- success--userId=" + userId);
            return mv;
        } else {
            throw new MyNotFoundException("无此账号");
        }
    }

    public interface Update extends Default {

    }
}
