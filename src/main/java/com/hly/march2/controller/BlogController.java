package com.hly.march2.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hly.march2.dto.BlogSearchDTO;
import com.hly.march2.entity.*;
import com.hly.march2.exception.MyNoPermissionException;
import com.hly.march2.exception.MyNotFoundException;
import com.hly.march2.exception.MyNotLoginException;
import com.hly.march2.exception.SysException;
import com.hly.march2.schedule.LikeSchedule;
import com.hly.march2.schedule.PVSchedule;
import com.hly.march2.service.*;
import com.hly.march2.utils.DateUtils;
import com.hly.march2.utils.MyBase64Utils;
import com.hly.march2.utils.WrappedBeanCopier;
import com.hly.march2.vo.BlogUserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.groups.Default;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
public class BlogController {
    private static final Logger log = LoggerFactory.getLogger(BlogController.class);
    /**
     * <option value="7">highlight</option>
     * <option value="6">normal</option>
     * <option value="5">friendly</option>
     * <option value="4">private</option>
     * <option value="3">draft</option>
     * <option value="2" disabled>preview</option>
     * <option value="1" disabled>pre-create</option>
     */
    public enum BlogStatusEnum {
        PRE_CREATE_STATUS(1, "pre-create"),
        PREVIEW_STATUS(2, "preview"),
        DRAFT_STATUS(3, "draft"),
        PRIVATE_STATUS(4, "private"),
        FRIENDLY_STATUS(5, "friendly"),
        NORMAL_STATUS(6, "normal"),
        HIGHLIGHT_STATUS(7, "highlight");

        private Integer code;
        private String desc;

        BlogStatusEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static long MAX_CODE = 7;

        private static List<Integer> draftTableList;

        private static List<Integer> blogTableList;

        private static List<Integer> guestViewList;

        static {
            List<Integer> list1 = new ArrayList<>();
            list1.add(PRE_CREATE_STATUS.getCode());
            list1.add(PREVIEW_STATUS.getCode());
            list1.add(DRAFT_STATUS.getCode());
            draftTableList = Collections.unmodifiableList(list1);

            List<Integer> list2 = new ArrayList<>();
            list2.add(PRIVATE_STATUS.getCode());
            list2.add(FRIENDLY_STATUS.getCode());
            list2.add(NORMAL_STATUS.getCode());
            list2.add(HIGHLIGHT_STATUS.getCode());
            blogTableList = Collections.unmodifiableList(list2);

            List<Integer> list3 = new ArrayList<>();
            list3.add(NORMAL_STATUS.getCode());
            list3.add(HIGHLIGHT_STATUS.getCode());
            guestViewList = Collections.unmodifiableList(list3);
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

        public static List<Integer> getDraftTableList() {
            return draftTableList;
        }

        public static List<Integer> getBlogTableList() {
            return blogTableList;
        }

        public static List<Integer> getGuestViewList() {
            return guestViewList;
        }
    }

    public enum BlogSortEnum {
        NO_SORT(1, null),
        VIEW_ASC(2, "viewASC"),
        VIEW_DESC(3, "viewDESC"),
        COMMENT_ASC(4, "commentASC"),
        COMMENT_DESC(5, "commentDESC"),
        LIKE_ASC(6, "likeASC"),
        LIKE_DESC(7, "likeDESC"),
        UPDATE_TIME_ASC(8, "updateTimeASC"),
        UPDATE_TIME_DESC(9, "updateTimeDESC");

        private Integer code;
        private String desc;

        BlogSortEnum(Integer code, String desc) {
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


    @Autowired
    private IBlogService blogService;

    @Autowired
    private IDraftService draftService;

    @Autowired
    private IBlogSeriesService blogSeriesService;

    @Autowired
    private IBlogTypeService blogTypeService;

    @Autowired
    private IUserService userService;

    /**
     * 获取Blog或Draft list page
     * 权限注解:
     * Shiro支持三种方式的授权：
     * 1. 编程式：subject.hasRole();subject.checkPermission();
     * 2. 注解式：如下，没有权限就抛出异常。Service进行了代理模式实现事务，所以不要用在Service上.
     *
     * @RequiresUser 表示经过身份认证或记住我登录的都可以。
     * @RequiresAuthentication 表示当前subject经过了Login认证，即subject.isAuthenticated()=true。不包括记住我
     * @RequiresGuest 表示当前subject没有经过认证或者记住我登陆
     * @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
     * @RequiresPermissions(value = {"user:a","user:b"},logical = Logical.OR)
     * <p>
     * 3. JSP/GSP标签：在JSP/GSP前提页面通过相应标签完成。首先要导入<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     * <p>
     * 获取所有所有博文Brief
     * rest风格：
     * /user/{id}  GET 查询用户
     * /user       POST 保存用户
     * /user/{id}  PUT 修改用户
     * /user/{id}  DELETE 删除用户
     *
     * @param blogSearchDTO  sourceFrom 区分从blog还是draft取得page数据。同时其他参数作为前端传来的搜索条件进行过滤
     * @return
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "/blog/page", method = RequestMethod.POST)
    public Msg getBlogPage(@Validated @RequestBody BlogSearchDTO blogSearchDTO, BindingResult result) {
        log.debug("getBlogPage  blogSearchDTO:{}",blogSearchDTO.toString());
        if(result.hasErrors()){
            // 校验失败
            Map<String,Object> map = new HashMap<>();
            List<FieldError> errors =  result.getFieldErrors();
            for(FieldError fieldError:errors){
                log.debug("Validated getBlogPage-错误的字段名"+fieldError.getField());
                log.debug("Validated getBlogPage-错误信息:"+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().setMsg(map.toString());
        }
        Subject subject = SecurityUtils.getSubject();
        List<BlogUserVo> blogUserVos = null;
        // sourceFrom表示是请求的数据是(主页)的blog表，normal,highlight状态的blog
        if ("home".equals(blogSearchDTO.getSourceFrom())) {
            if (!BlogStatusEnum.getGuestViewList().contains(blogSearchDTO.getBlogStatus())) {
                blogSearchDTO.setBlogStatusList(BlogStatusEnum.getGuestViewList());
                blogSearchDTO.setBlogStatus(null);
            } else if (blogSearchDTO.getBlogStatus() == null) {
                blogSearchDTO.setBlogStatusList(BlogStatusEnum.getGuestViewList());
            }
            PageHelper.startPage(blogSearchDTO.getPn(), 10);
            blogUserVos = blogService.getBlogUserBySearch(blogSearchDTO);
        } else {
            // 否则就是请求的个人的所有(这里就要区分blog表还是draft表)的数据。
            if (subject.isRemembered() || subject.isAuthenticated()) {
                User user = (User) subject.getPrincipal();
                blogSearchDTO.setUserId(user.getUserId());
                blogSearchDTO.setUserName(null);
                if ("blog".equals(blogSearchDTO.getSourceFrom())) {
                    PageHelper.startPage(blogSearchDTO.getPn(), 10);
                    blogUserVos = blogService.getBlogUserBySearch(blogSearchDTO);
                } else if ("draft".equals(blogSearchDTO.getSourceFrom())) {
                    PageHelper.startPage(blogSearchDTO.getPn(), 10);
                    blogUserVos = draftService.getDraftUserBySearch(blogSearchDTO);
                } else {
                    return Msg.fail().setMsg("参数信息不正确!");
                }
            } else {
                return Msg.stranger();
            }
        }
        log.debug("getBlogPage--- List size:{}",blogUserVos.size());
        for (BlogUserVo blogUserVo : blogUserVos) {
            blogUserVo = MyBase64Utils.blogUserVoDecode(blogUserVo);
        }
        PageInfo<BlogUserVo> page = new PageInfo(blogUserVos, 10);
        return Msg.success().add("pageinfo", page);
    }

    /**
     * 获取blog或者draft用作展示
     * @param blogId
     * @param sourceFrom
     * @param request
     * @return
     * @throws MyNotLoginException
     * @throws MyNotFoundException
     * @throws MyNoPermissionException
     */
    @ResponseBody
    @RequestMapping(value = "/blog/{blogId}", method = RequestMethod.GET)
    public ModelAndView getBlogForShow(@PathVariable(value = "blogId") Long blogId, @RequestParam(value = "sf", required = false) String sourceFrom, HttpServletRequest request) throws MyNotLoginException, MyNotFoundException, MyNoPermissionException {
        log.debug("blogId:{}. sf:{}",blogId,sourceFrom);
        Subject subject = SecurityUtils.getSubject();
        ModelAndView mv = new ModelAndView();
        // 如果没有sourceFrom，说明请求的是normal、highlight状态的blog。
        if (sourceFrom == null || "".equals(sourceFrom.trim())) {
            List<BlogUserVo> blogUserVos = blogService.getBlogUserByBlogIdAndStatusWithBLOBs(blogId, BlogStatusEnum.getGuestViewList());
            if (blogUserVos.size() > 0) {
                BlogUserVo blogUserVo = blogUserVos.get(0);
                blogUserVo = MyBase64Utils.blogUserVoDecode(blogUserVo);
                if (PVSchedule.blogViewCheckAndPush(request.getSession().getId(), blogUserVo.getBlogId())) {
                    blogUserVo.setBlogViews(blogUserVo.getBlogViews() + 1);
                }
                log.info("get guest blog success. Id:{}",blogUserVo.getBlogId());
                mv.setViewName("blog");
                mv.addObject("blog", blogUserVo);
                return mv;
            } else {
                throw new MyNotFoundException("不存在此Blog,BlogId:" + blogId);
            }

            // 否则就是请求的私密的blog或draft(draft一直是私密)，下面就需要区分blog还是draft。并且只有blog属于访问用户个人或者访问者是admin才允许。
        } else if ("blog".equals(sourceFrom)) {
            BlogUserVo blogUserVo = blogService.getBlogUserByBlogIdWithBLOBs(blogId);
            if (blogUserVo != null) {
                if (BlogStatusEnum.getGuestViewList().contains(blogUserVo.getBlogStatus())) {
                    blogUserVo = MyBase64Utils.blogUserVoDecode(blogUserVo);
                    log.info("get guest blog success. Id:{}",blogUserVo.getBlogId());
                    mv.setViewName("blog");
                    mv.addObject("blog", blogUserVo);
                    return mv;
                } else {
                    if (subject.isRemembered() || subject.isAuthenticated()) {
                        User user = (User) subject.getPrincipal();
                        if ((UserController.AccountStatusEnum.getForbiddenList().contains(blogUserVo.getUser().getAccountStatus()))&&!(subject.hasRole(UserController.RolesEnum.ADMIN_ROLE.getDesc()) || subject.hasRole(UserController.RolesEnum.SUPER_ADMIN_ROLE.getDesc()))) {
                            throw new MyNotFoundException("对方账户已被封禁");
                        }
                        if (blogUserVo.getUserId().equals(user.getUserId())) {
                            blogUserVo = MyBase64Utils.blogUserVoDecode(blogUserVo);
                            log.info("get self blog success. Id:{}",blogUserVo.getBlogId());
                            mv.setViewName("blog");
                            mv.addObject("blog", blogUserVo);
                            return mv;
                        } else if ((subject.hasRole(UserController.RolesEnum.ADMIN_ROLE.getDesc()) || subject.hasRole(UserController.RolesEnum.SUPER_ADMIN_ROLE.getDesc())) && user.getUserRights() < blogUserVo.getUser().getUserRights()) {
                            blogUserVo = MyBase64Utils.blogUserVoDecode(blogUserVo);
                            log.info("get blog by admin success. Id:{}",blogUserVo.getBlogId());
                            mv.setViewName("blog");
                            mv.addObject("blog", blogUserVo);
                            return mv;
                        } else {
                            throw new MyNoPermissionException("不能查看私人blog,BlogId:" + blogId);
                        }
                    } else {
                        throw new MyNotLoginException("请先登录,BlogId:" + blogId);
                    }
                }
            } else {
                throw new MyNotFoundException("不存在blog,BlogId:" + blogId);
            }
        } else {
            // 1.首选判断当前用户是否已登录过
            if (subject.isRemembered() || subject.isAuthenticated()) {
                BlogUserVo blogUserVo = null;
                User user = (User) subject.getPrincipal();

                if (blogId >= 0 && sourceFrom != null) {
                    if ("draft".equals(sourceFrom)) {
                        blogUserVo = draftService.getDraftUserByBlogIdWithBLOBs(blogId);
                        log.info("guest user id:{} rights:{} , blogId:{} , blogUserRihgt:{}",user.getUserId(),user.getUserRights(),blogId,blogUserVo.getUser().getUserRights());
                    }
                    if (blogUserVo != null) {
                        if ((UserController.AccountStatusEnum.getForbiddenList().contains(blogUserVo.getUser().getAccountStatus()))&&!(subject.hasRole(UserController.RolesEnum.ADMIN_ROLE.getDesc()) || subject.hasRole(UserController.RolesEnum.SUPER_ADMIN_ROLE.getDesc()))){
                            throw new MyNotFoundException("此账户已被封禁");
                        }
                        if (blogUserVo.getUserId().equals(user.getUserId())) {
                            blogUserVo = MyBase64Utils.blogUserVoDecode(blogUserVo);
                            log.info("get self draft success. Id:{}",blogUserVo.getBlogId());
                            mv.setViewName("blog");
                            mv.addObject("blog", blogUserVo);
                            return mv;
                        }else if ((subject.hasRole(UserController.RolesEnum.ADMIN_ROLE.getDesc()) || subject.hasRole(UserController.RolesEnum.SUPER_ADMIN_ROLE.getDesc())) && user.getUserRights() < blogUserVo.getUser().getUserRights()) {
                            blogUserVo = MyBase64Utils.blogUserVoDecode(blogUserVo);
                            log.info("get draft by admin success. Id:{}",blogUserVo.getBlogId());
                            mv.setViewName("blog");
                            mv.addObject("blog", blogUserVo);
                            return mv;
                        }
                    }
                }
                throw new MyNotFoundException("此BlogId错误,BlogId:" + blogId);
            } else {
                throw new MyNotLoginException("还未登录,请先登录");
            }
        }

    }

    /**
     * 获取blog或者draft作为编辑。这里只允许数据拥有者访问。或者新建draft。
     * @param blogId
     * @param sourceFrom
     * @return
     * @throws MyNotFoundException
     */
    @RequiresUser
    @RequiresPermissions(value = {"blog:create"})
    @RequestMapping(value = "/blog/edit/{blogId}", method = RequestMethod.GET)
    public ModelAndView getBlogForEdit(@PathVariable(value = "blogId") Long blogId, @RequestParam(value = "sf", required = false) String sourceFrom) throws MyNotFoundException {
        log.debug("blogId:{}. sf:{}",blogId,sourceFrom);
        Subject subject = SecurityUtils.getSubject();
        ModelAndView mv = new ModelAndView();
        User user = (User) subject.getPrincipal();
        if ((blogId != null && blogId >= 0) && (sourceFrom != null && !"".equals(sourceFrom.trim()))) {
            if ("blog".equals(sourceFrom)) {
                Blog blog = blogService.getBlogByBlogIdWithBLOBs(blogId);
                if (blog != null && blog.getUserId().equals(user.getUserId())) {
                    blog = MyBase64Utils.blogDecode(blog);
                    Draft draft = WrappedBeanCopier.copyProperties(blog, Draft.class);
                    log.info("get self blog success. Id:{}",draft.getBlogId());
                    mv.setViewName("edit");
                    mv.addObject("blog", draft);
                    return mv;
                }
            } else if ("draft".equals(sourceFrom)) {
                Draft draft = draftService.getDraftByBlogIdWithBLOBs(blogId);
                if (draft != null && draft.getUserId().equals(user.getUserId())) {
                    draft = MyBase64Utils.draftDecode(draft);
                    log.info("get self draft success. Id:{}",draft.getBlogId());
                    mv.setViewName("edit");
                    mv.addObject("blog", draft);
                    return mv;
                }
            }
            throw new MyNotFoundException("参数信息错误,BlogId:" + blogId);
        } else {
            // 新建草稿
            Draft draft = new Draft();
            Date now = DateUtils.getCurrentDateTime();
            draft.setOriginalBlogId(new Long(-1));
            draft.setBlogCreateTime(now);
            draft.setBlogUpdateTime(now);
            draft.setUserId(user.getUserId());
            draft.setBlogId(new Long(-1));
            draft.setSourceFrom("draft");
            draft.setBlogStatus(BlogStatusEnum.PRE_CREATE_STATUS.getCode());
            mv.setViewName("edit");
            mv.addObject("blog", draft);
            log.info("new draft success. user Id:{}",user.getUserId());
            return mv;
        }
    }


    /**
     * 保存修改或者新建的blog或者draft
     *
     * ModelAndView 返回最好别用@ResponseBody。用了传少量值到前台还行，大量(blog文本)的话，前台需要对json字符串解析成Json，容易解析出错。
     * @param draftData
     * @param result
     * @return
     * @throws MyNoPermissionException
     */
    @RequiresUser
    @RequiresPermissions(value = {"blog:edit"})
    @ResponseBody
    @RequestMapping(value = "/blog", method = RequestMethod.POST)
    public Msg saveBlog(@Validated @RequestBody Draft draftData, BindingResult result) throws MyNoPermissionException {
        log.debug("saveBlog");
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
        if (draftData.getUserId() == null || !user.getUserId().equals(draftData.getUserId())) {
            log.debug("save fail. User Id not match");
            throw new MyNoPermissionException("保存失败!这篇blog不属于你");
        }

        if (draftData.getSourceFrom() != null) {
            if (draftData.getBlogId() == null || draftData.getBlogId() <= -2) {
                log.debug("save fail---sf={},但是blogId=null或者<=0",draftData.getSourceFrom());
                return Msg.fail().setMsg("保存失败!参数不正确");
            }
        } else {
            if (draftData.getBlogId() != null && draftData.getBlogId() > 0) {
                log.debug("save fail---sf==null,但是blogId={}",draftData.getBlogId());
                return Msg.fail().setMsg("保存失败!2,参数不正确");
            }
        }

        try {
            if (draftData.getBlogTitle() == null || "".equals(draftData.getBlogTitle().trim())) {
                draftData.setBlogTitle("新建Blog,还未设置标题");
            }
            draftData = MyBase64Utils.draftEncode(draftData);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.debug("save fail. Encode fail");
            return Msg.fail().setMsg("保存失败! 数据有误,Encode失败!");
        }

        // 预览编辑的Blog。预览是先保存，再取(保存到draft表，同一个人同时只存在一个preview)
        if (BlogStatusEnum.PREVIEW_STATUS.getCode().equals(draftData.getBlogStatus())) {
            List<Integer> statusList = new ArrayList<>();
            statusList.add(BlogStatusEnum.PREVIEW_STATUS.getCode());
            List<Draft> testDraftList = draftService.getDraftByUserIdAndStatus(draftData.getUserId(), statusList);
            // 根据数据库内是否有同Id数据来决定更新(更新旧的是为了保持数据库中同用户下只存在一个preview)还是新建
            if (testDraftList.size() > 0) {
                draftData.setBlogId(testDraftList.get(0).getBlogId());
                Msg msg = draftService.updatePreviewByBlogId(draftData);
                log.info("update as preview. Draft Id:{}",draftData.getBlogId());
                return msg.add("blogId", draftData.getBlogId()).add("originId", draftData.getOriginalBlogId())
                        .add("seriesId", draftData.getSeriesId()).add("typeId", draftData.getBlogType());
            } else {
                Msg msg = draftService.insertDraftWithCheck(draftData);
                log.info("save as preview. Draft Id:{}",draftData.getBlogId());
                return msg.add("blogId", draftData.getBlogId()).add("originId", draftData.getOriginalBlogId())
                        .add("seriesId", draftData.getSeriesId()).add("typeId", draftData.getBlogType());
            }

        } else if (BlogStatusEnum.DRAFT_STATUS.getCode().equals(draftData.getBlogStatus())) {
            /** 保存到draft表。如果是blog表数据保存到Draft表，就新建draft，blog表源数据不会删除或者更新，draft只是作为一个副本。
             * 如果是draft表保存到draft表，只会更新draft表数据。
             * (这里用sourceFrom区分是来自draft还是blog。如果sourceFrom=blog，说明是新建草稿，如果sourceFrom=draft说明是更新草稿)
             * 这里要一起保存origin blogId(只标记draft来自的Blog表的Id。若是直接新建Draft,则origin Id=-1)
             **/
            if (draftData.getNewType() != null && !"".equals(draftData.getNewType().trim())) {
                BlogType blogType = new BlogType();
                blogTypeService.insertTypeWithCheck(draftData, blogType);
                log.info("insert new type. Type Id:{}",blogType.getTypeId());
                draftData.setBlogType(blogType.getTypeId());
                draftData.setNewType(null);
            }
            if (draftData.getNewSeries() != null && !"".equals(draftData.getNewSeries().trim())) {
                BlogSeries blogSeries = new BlogSeries();
                blogSeriesService.insertSeriesWithCheck(draftData, blogSeries);
                log.info("insert new series. Series Id:{}",blogSeries.getSeriesId());
                draftData.setSeriesId(blogSeries.getSeriesId());
                draftData.setNewSeries(null);
            }
            if (draftData.getBlogId() != null && draftData.getBlogId() > 0) {
                if ("draft".equals(draftData.getSourceFrom())) {
                    Msg msg = draftService.updateDraftWithCheck(draftData);
                    if (msg.getCode() == 100) {
                        log.info("update as draft . Draft Id:{}",draftData.getBlogId());
                        return msg.add("blogId", draftData.getBlogId()).add("originId", draftData.getOriginalBlogId())
                                .add("seriesId", draftData.getSeriesId()).add("typeId", draftData.getBlogType());
                    } else {
                        return msg;
                    }
                }
            }
            Msg msg = draftService.insertDraftWithCheck(draftData);
            if (msg.getCode() == 100) {
                log.info("insert as draft . Draft Id:{}",draftData.getBlogId());
                return msg.add("blogId", draftData.getBlogId()).add("originId", draftData.getOriginalBlogId())
                        .add("seriesId", draftData.getSeriesId()).add("typeId", draftData.getBlogType());
            } else {
                return msg;
            }

        } else if (BlogStatusEnum.getBlogTableList().contains(draftData.getBlogStatus())) {
            /** 保存到blog表。如果是blog表数据保存到blog表，就是更新blog.
             * 如果draft表保存到blog表就是新建blog。
             * 根据sourceFrom区分，若sourceFrom=draft就是新建blog，若sourceFrom=blog就是更新blog。
             * 同时根据origin Id区分是更新Blog表哪一条数据，
             **/
            // Series与Type
            if (draftData.getNewType() != null&&!"".equals(draftData.getNewType().trim()  )) {
                List<BlogType> blogTypeList = blogTypeService.getTypeByTypeNameAndUserId(draftData.getNewType().trim(), draftData.getUserId());
                if (blogTypeList.size() > 0) {
                    // 如果已有同名Type，拿过来替换
                    draftData.setNewType(null);
                    draftData.setBlogType(blogTypeList.get(0).getTypeId());
                } else {
                    // 如果没有就要新建
                    BlogType blogType = new BlogType();
                    blogTypeService.insertTypeWithCheck(draftData, blogType);
                    log.info("insert new type. Type Id:{}",blogType.getTypeId());
                    draftData.setNewType(null);
                    draftData.setBlogType(blogType.getTypeId());
                }
            }
            if (draftData.getNewSeries() != null&&!"".equals(draftData.getNewSeries().trim()  )) {
                List<BlogSeries> blogSeriesList = blogSeriesService.getSeriesBySeriesNameAndUserId(draftData.getNewSeries().trim(), draftData.getUserId());
                if (blogSeriesList.size() > 0) {
                    // 如果用户id名下已有同名series，拿过来替换
                    draftData.setNewSeries(null);
                    draftData.setSeriesId(blogSeriesList.get(0).getSeriesId());
                } else {
                    // 如果没有就要新建
                    BlogSeries blogSeries = new BlogSeries();
                    blogSeriesService.insertSeriesWithCheck(draftData, blogSeries);
                    log.info("insert new series. Series Id:{}",blogSeries.getSeriesId());
                    draftData.setNewSeries(null);
                    draftData.setSeriesId(blogSeries.getSeriesId());
                }
            }
            Blog blog = WrappedBeanCopier.copyProperties(draftData, Blog.class);
            if (blog.getBlogId() != null && blog.getBlogId() > 0) {
                if ("blog".equals(blog.getSourceFrom()) || ("draft".equals(blog.getSourceFrom()) && blog.getOriginalBlogId() != null && blog.getOriginalBlogId() > 0)) {
                    Msg msg = blogService.updateBlogWithCheck(blog);
                    if (msg.getCode() == 100) {
                        log.info("update as blog . Blog Id:{}",blog.getBlogId());
                        return msg.add("blogId", blog.getBlogId()).add("originId", -1)
                                .add("seriesId", draftData.getSeriesId()).add("typeId", draftData.getBlogType());
                    } else {
                        return msg;
                    }
                }
            }
            Msg msg = blogService.insertBlogWithCheck(blog);
            if (msg.getCode() == 100) {
                log.info("insert as blog . Blog Id:{}",blog.getBlogId());
                return msg.add("blogId", blog.getBlogId()).add("originId", -1)
                        .add("seriesId", draftData.getSeriesId()).add("typeId", draftData.getBlogType());
            } else {
                return msg;
            }
        } else {
            return Msg.fail().setMsg("参数错误!");
        }

    }

    /**
     * 取预览preview Blog，供展示。
     * @param blogId
     * @return
     * @throws MyNotFoundException
     */
    @RequestMapping(value = "/blog/preview/{blogid}", method = RequestMethod.GET)
    public ModelAndView preview(@PathVariable(value = "blogid", required = true) Long blogId) throws MyNotFoundException {
        log.debug("Draft Id:{}",blogId);
        ModelAndView mv = new ModelAndView();
        List<BlogUserVo> blogDraftList = draftService.getDraftUserByBlogIdAndStatus(blogId, BlogStatusEnum.PREVIEW_STATUS.getCode());
        if (blogDraftList.size() <= 0) {
            throw new MyNotFoundException("草稿表没有这个BlogId的preview数据");
        }
        BlogUserVo blogUserVo = blogDraftList.get(0);
        // 获得数据库数据后blog内容要解码
        blogUserVo = MyBase64Utils.blogUserVoDecode(blogUserVo);

        mv.addObject("blog", blogUserVo);
        mv.setViewName("blog");
        log.debug("Draft Id:{}. get Preview success",blogUserVo.getBlogId());
        return mv;
    }

    /**
     * 删除Blog或者Draft
     * @param blogId
     * @param sourceFrom 区分是删除Blog表中数据还是draft表中数据
     * @return
     */
    @RequiresUser
    @RequiresPermissions(value = {"blog:edit"})
    @ResponseBody
    @RequestMapping(value = "/blog/{blogid}", method = RequestMethod.DELETE)
    public Msg deleteBlog(@PathVariable("blogid") Long blogId, @RequestParam("sf") String sourceFrom)  {
        log.debug("blogId:{}. sf:{}",blogId,sourceFrom);
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if ("blog".equals(sourceFrom)) {
            List<Blog> blogList = blogService.getBlogByBlogIdAndUserId(blogId, user.getUserId());
            if (blogList.size() <= 0) {
                return Msg.fail().setMsg("寻找不到此Blog");
            } else {
                blogService.deleteByPrimaryKey(blogId);
                log.info("delete blog success. Id:{}",blogId);
                return Msg.success().setMsg("删除成功!").add("time", DateUtils.getCurrentDateTime());
            }
        } else if ("draft".equals(sourceFrom)) {
            List<Draft> draftList = draftService.getDraftByBlogIdAndUserId(blogId, user.getUserId());
            if (draftList.size() <= 0) {
                return Msg.fail().setMsg("寻找不到此Draft");
            } else {
                draftService.deleteByPrimaryKey(blogId);
                log.info("delete draft success. Id:{}",blogId);
                return Msg.success().setMsg("删除成功!").add("time", DateUtils.getCurrentDateTime());
            }
        } else {
            return Msg.fail().setMsg("参数信息有误!");
        }

    }

    /**
     * 获取Series 列表
     * @param userId
     * @param sourceFrom 区分是获取个人展示状态(normal,highlight)的series list还是个人全部的list.
     * @return
     * @throws MyNoPermissionException
     * @throws MyNotLoginException
     */
    @ResponseBody
    @RequestMapping(value = "/blog/serieslist/{userid}", method = RequestMethod.GET)
    public Msg getBlogSeriesList(@PathVariable("userid") Long userId, @RequestParam("sourceFrom") String sourceFrom) throws MyNoPermissionException, MyNotLoginException {
        log.debug("userId:{}. sf:{}",userId,sourceFrom);
        Subject subject = SecurityUtils.getSubject();
        if (sourceFrom != null && "home".equals(sourceFrom.trim())) {
            // 说明是取得个人展示状态(normal,highlight)的series list
            List<BlogSeries> blogSeriesList = blogSeriesService.getSeriesByStatus(BlogStatusEnum.getGuestViewList());
            log.debug("get home series list success");
            return Msg.success().add("seriesList", blogSeriesList);
        } else if (subject.isRemembered() || subject.isAuthenticated()) {
            // 否则是取个人全部的series list。但是只能数据拥有者或者admin取得。
            User user = (User) subject.getPrincipal();
            if (user.getUserId().equals(userId)|| (UserController.RolesEnum.getAdminCodeList().contains(user.getUserRights())&&"admin".equals(sourceFrom))) {
                // 就获取全部
                List<BlogSeries> blogSeriesList = blogSeriesService.getSeriesByUserId(userId);
                log.debug("get self or admin series list success");
                return Msg.success().add("seriesList", blogSeriesList);
            } else {
                List<BlogSeries> blogSeriesList = blogSeriesService.getSeriesByUserIdAndStatus(userId, BlogStatusEnum.getGuestViewList());
                log.debug("get guest series list success");
                return Msg.success().add("seriesList", blogSeriesList);
            }
        } else {
            throw new MyNotLoginException("请先登录");
        }
    }

    /**
     * 获取type list
     * 逻辑跟getBlogSeriesList大同小异
     * @param userId
     * @param sourceFrom
     * @return
     * @throws MyNoPermissionException
     */
    @ResponseBody
    @RequestMapping(value = "/blog/typelist/{userid}", method = RequestMethod.GET)
    public Msg getBlogTypeList(@PathVariable("userid") Long userId, @RequestParam("sourceFrom") String sourceFrom) throws MyNoPermissionException {
        log.debug("userId:{}. sf:{}",userId,sourceFrom);
        Subject subject = SecurityUtils.getSubject();
        if (sourceFrom != null && "home".equals(sourceFrom.trim())) {
            List<BlogType> blogTypeList = blogTypeService.getTypeByStatus(BlogStatusEnum.getGuestViewList());
            log.debug("get home type list success");
            return Msg.success().add("typeList", blogTypeList);

        } else if (sourceFrom != null && UserController.RolesEnum.getAdminDescList().contains(sourceFrom.trim())) {
            if (subject.hasRole(UserController.RolesEnum.ADMIN_ROLE.getDesc()) || subject.hasRole(UserController.RolesEnum.SUPER_ADMIN_ROLE.getDesc())) {
                List<BlogType> blogTypeList = blogTypeService.getAllType();
                log.debug("get admin type list success");
                return Msg.success().add("typeList", blogTypeList);
            } else {
                throw new MyNoPermissionException("权限不足");
            }
        } else if (subject.isRemembered() || subject.isAuthenticated()) {
            User user = (User) subject.getPrincipal();
            if (user.getUserId().equals(userId)) {
                List<BlogType> blogTypeList = blogTypeService.getTypeByUserId(userId);
                log.debug("get self type list success");
                return Msg.success().add("typeList", blogTypeList);
            } else {
                List<BlogType> blogTypeList = blogTypeService.getTypeByUserIdAndStatus(userId, BlogStatusEnum.getGuestViewList());
                log.debug("get guest type list success");
                return Msg.success().add("typeList", blogTypeList);
            }
        } else {
            throw new MyNoPermissionException("权限不足");
        }
    }

    /**
     * 获取某个series Id下的blog list。
     * 供前端查看个人Profile series数据，
     * @param seriesId
     * @return
     * @throws SysException
     */
    @RequestMapping(value = "/series/{seriesId}",method = RequestMethod.GET)
    public ModelAndView goProfileSeries(@PathVariable("seriesId") Long seriesId) throws SysException {
        log.debug("Series Id:{}",seriesId);
        ModelAndView mv = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        BlogSeries blogSeries = blogSeriesService.getSeriesBySeriesId(seriesId);
        if (blogSeries !=null) {
            User user = userService.getUserByUserId(blogSeries.getUserId());
            if(UserController.AccountStatusEnum.getForbiddenList().contains(user.getAccountStatus())){
                throw new MyNotFoundException("此账号已被禁用");
            }
            if(!BlogStatusEnum.getGuestViewList().contains(blogSeries.getSeriesStatus())){
                if(subject.isRemembered() || subject.isAuthenticated()) {
                    User comer = (User)subject.getPrincipal();
                    if((!UserController.RolesEnum.getAdminCodeList().contains(comer.getUserRights()))&&(!comer.getUserId().equals(blogSeries.getUserId()))) {
                        throw new MyNoPermissionException("无查看此Series的权限");
                    }
                    // 如果series Id是当前访问用户的或者当前是admin，则返回series 下所有状态的blog。而不是只有Normal、higihlight
                    log.debug("get self or admin series profile");
                    mv.addObject("blogSeries", blogSeries);
                }else{
                    throw new MyNotLoginException("请先登录");
                }
            }else{
                if(subject.isRemembered() || subject.isAuthenticated()) {
                    User comer = (User)subject.getPrincipal();
                    if((UserController.RolesEnum.getAdminCodeList().contains(comer.getUserRights()))||(comer.getUserId().equals(blogSeries.getUserId()))) {
                        // 如果series Id是当前访问用户的或者当前是admin，则返回series 下所有状态的blog。而不是只有Normal、higihlight
                        log.debug("get self or admin series profile");
                        mv.addObject("blogSeries", blogSeries);
                    }
                }
            }
            mv.addObject("userId", blogSeries.getUserId());
            mv.addObject("userName", user.getUserNickname() + "(" + user.getUserName() + ")");
            mv.addObject("seriesId", blogSeries.getSeriesId());
            mv.addObject("seriesName", blogSeries.getSeriesName());
            mv.addObject("type", "series");
            log.debug("get series profile success. series Id:{}",blogSeries.getSeriesId());
            mv.setViewName("profile");
            return mv;
        } else {
            throw new MyNotFoundException("无此Series,Id:"+seriesId);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/series",method = RequestMethod.PUT)
    public Msg updateSeries(HttpServletRequest request, @Validated BlogSeries blogSeries, BindingResult result){
        log.debug("blogSeries:{}",blogSeries.toString());
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
        User hostUser = (User)subject.getPrincipal();
        BlogSeries originSeries = blogSeriesService.getSeriesBySeriesId(blogSeries.getSeriesId());
        if(originSeries==null){
            return Msg.fail().setMsg("不存在此Series");
        }
        if(hostUser.getUserId().equals(originSeries.getUserId())){
            Msg msg = blogSeriesService.updateSeriesWithCheck(blogSeries,hostUser.getUserId());
            log.info("update self series. msg:{}",msg);
            return msg;
        }else if (UserController.RolesEnum.getAdminCodeList().contains(hostUser.getUserRights())){
            Msg msg = blogSeriesService.updateSeriesWithCheck(blogSeries,originSeries.getUserId());
            log.info("update admin series. msg:{}",msg);
            return msg;
        }
        return Msg.fail().setMsg("无此权限");
    }

    /**
     * 删除series
     * @param seriesId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/series/{seriesId}",method = RequestMethod.DELETE)
    public Msg deleteSeries(@PathVariable("seriesId") Long seriesId){
        log.debug("series Id:{}",seriesId);
        Subject subject = SecurityUtils.getSubject();
        User hostUser = (User)subject.getPrincipal();
        if (UserController.RolesEnum.getAdminCodeList().contains(hostUser.getUserRights())){
            blogSeriesService.deleteSeriesBySeriesId(seriesId);
            log.info("series Id:{}, delete admin success",seriesId);
            return Msg.success().setMsg("删除成功!");
        }else{
            blogSeriesService.deleteSeriesBySeriesIdAndUserId(seriesId,hostUser.getUserId());
            log.info("series Id:{}, delete self success",seriesId);
            return Msg.success().setMsg("删除成功!");
        }
    }

    /**
     * 点赞
     * @param blogId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/like/{blogId}", method = RequestMethod.GET)
    public Msg getBlogLikeList(@PathVariable("blogId") Long blogId, HttpServletRequest request) {
        log.debug("blog Id:{}",blogId);
        HttpSession session = request.getSession();
        if (session.getAttribute("like") != null) {
            ArrayList<Long> likeList = (ArrayList<Long>) session.getAttribute("like");
            if (likeList.contains(blogId)) {
                return Msg.fail().setMsg("已有点赞记录");
            } else {
                likeList.add(blogId);
                LikeSchedule.blogLikeCheckAndPush(session.getId(), blogId);
                return Msg.success();
            }
        } else {
            List<Long> likeList = new ArrayList<>();
            likeList.add(blogId);
            LikeSchedule.blogLikeCheckAndPush(session.getId(), blogId);
            session.setAttribute("like", likeList);
            return Msg.success();
        }

    }


    public interface Insert extends Default {

    }

    public interface Update extends Default {

    }

    public interface Select extends Default {

    }


}

