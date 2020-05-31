package com.hly.march2.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hly.march2.dto.BlogSearchDTO;
import com.hly.march2.dto.UserSearchDTO;
import com.hly.march2.entity.Blog;
import com.hly.march2.entity.Draft;
import com.hly.march2.entity.Msg;
import com.hly.march2.entity.User;
import com.hly.march2.shiro.ShiroHelper;
import com.hly.march2.service.IBlogService;
import com.hly.march2.service.IDraftService;
import com.hly.march2.service.IUserService;
import com.hly.march2.utils.DateUtils;
import com.hly.march2.utils.MD5;
import com.hly.march2.utils.MyBase64Utils;
import com.hly.march2.utils.WrappedBeanCopier;
import com.hly.march2.vo.BlogUserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
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

import javax.validation.groups.Default;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/admin")
@Controller
@Slf4j
public class AdminController {
//    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private IBlogService blogService;

    @Autowired
    private IDraftService draftService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ShiroHelper shiroHelper;

    /**
     * 跳转Admin page
     * @return
     */
    @RequiresAuthentication
    @RequiresRoles(value = {"admin","superAdmin"},logical = Logical.OR)
    @RequestMapping("/go")
    public ModelAndView goAdmin() {
        log.debug("goAdmin");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin");
        return mv;
    }

    /**
     * 供Admin获取所有的Blog和Draft列表
     * @param blogSearchDTO sourceFrom 区分从blog还是draft取得page数据。同时其他参数作为前端传来的搜索条件进行过滤
     * @param result
     * @return
     */
    @RequiresAuthentication
    @RequiresRoles(value = {"admin","superAdmin"},logical = Logical.OR)
    @ResponseBody
    @RequestMapping(value = "/blog/page", method = RequestMethod.POST)
    public Msg getAdminBlogPage(@Validated @RequestBody BlogSearchDTO blogSearchDTO, BindingResult result) {
        if(result.hasErrors()){
            // 校验失败
            Map<String,Object> map = new HashMap<>();
            List<FieldError> errors =  result.getFieldErrors();
            for(FieldError fieldError:errors){
                log.info("Validated 错误的字段名"+fieldError.getField());
                log.info("Validated 错误信息:"+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().setMsg(map.toString());
        }
        log.info("sourceFrom:{}",blogSearchDTO.getSourceFrom());
        List<BlogUserVo> blogUserVos = null;
        if ("blog".equals(blogSearchDTO.getSourceFrom())) {
            PageHelper.startPage(blogSearchDTO.getPn(), 15);
            blogUserVos = blogService.getBlogUserBySearch(blogSearchDTO);
        } else if ("draft".equals(blogSearchDTO.getSourceFrom())) {
            PageHelper.startPage(blogSearchDTO.getPn(), 15);
            blogUserVos = draftService.getDraftUserBySearch(blogSearchDTO);
        } else {
            return Msg.fail().setMsg("参数信息不正确!");
        }
        log.info("result size:{}",blogUserVos.size());
        for (BlogUserVo blogUserVo : blogUserVos) {
            blogUserVo = MyBase64Utils.blogUserVoDecode(blogUserVo);
        }
        PageInfo page = new PageInfo(blogUserVos, 10);
        return Msg.success().add("pageinfo", page);
    }

    /**
     * 供具有Admin权限的管理员修改Blog或Draft
     * @param draftData
     * @param result
     * @return
     */
    @RequiresAuthentication
    @RequiresRoles(value = {"superAdmin"})
    @ResponseBody
    @RequestMapping(value = "/blog", method = RequestMethod.POST)
    public Msg adminSaveBlog(@Validated @RequestBody Draft draftData, BindingResult result) {
        if(result.hasErrors()){
            // 校验失败
            Map<String,Object> map = new HashMap<>();
            List<FieldError> errors =  result.getFieldErrors();
            for(FieldError fieldError:errors){
                log.info("Validated 错误的字段名"+fieldError.getField());
                log.info("Validated 错误信息:"+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().setMsg(map.toString());
        }
        log.info(draftData.toString());
        if (draftData.getSourceFrom() != null) {
            if (draftData.getBlogId() == null || draftData.getBlogId() <= -2) {
                log.info("save fail---sf={},但是blogId=null或者<=0",draftData.getSourceFrom());
                return Msg.fail().setMsg("保存失败!参数不正确");
            }
        } else {
            if (draftData.getBlogId() != null && draftData.getBlogId() > 0) {
                log.info("save fail---sf==null,但是blogId={}",draftData.getBlogId());
                return Msg.fail().setMsg("保存失败!参数不正确");
            }
        }

        try {
            if (draftData.getBlogTitle() == null || "".equals(draftData.getBlogTitle().trim())) {
                draftData.setBlogTitle("新建Blog,还未设置标题");
            }
            draftData = MyBase64Utils.draftEncode(draftData);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.info("save fail---Encode失败");
            return Msg.fail().setMsg("保存失败!数据有误!");
        }
        if (BlogController.BlogStatusEnum.PREVIEW_STATUS.getCode().equals(draftData.getBlogStatus())) {
            // 保存为preview预览(还是在Draft表)
            List<Integer> statusList = new ArrayList<>();
            statusList.add(BlogController.BlogStatusEnum.PREVIEW_STATUS.getCode());
            List<Draft> testDraftList = draftService.getDraftByUserIdAndStatus(draftData.getUserId(), statusList);
            // 根据数据库内是否有同Id数据来决定更新(更新旧的是为了保持数据库中同用户下只存在一个preview)还是新建
            if (testDraftList.size() > 0) {
                draftData.setBlogId(testDraftList.get(0).getBlogId());
                Msg msg = draftService.updatePreviewByBlogId(draftData);
                log.info("update as preview. draft Id:{}",draftData.getBlogId());
                return msg.add("blogId", draftData.getBlogId()).add("originId", draftData.getOriginalBlogId())
                        .add("seriesId", draftData.getSeriesId()).add("typeId", draftData.getBlogType());
            } else {
                Msg msg = draftService.insertDraftWithCheck(draftData);
                log.info("insert as preview. draft Id:{}",draftData.getBlogId());
                return msg.add("blogId", draftData.getBlogId()).add("originId", draftData.getOriginalBlogId())
                        .add("seriesId", draftData.getSeriesId()).add("typeId", draftData.getBlogType());
            }

        } else if (BlogController.BlogStatusEnum.DRAFT_STATUS.getCode().equals(draftData.getBlogStatus())) {
            // 保存到Draft表
            // 这里只是初步根据sourceFrom来判断更新还是新建，Service还会做进一步判断
            if (draftData.getBlogId() != null && draftData.getBlogId() > 0) {
                if ("draft".equals(draftData.getSourceFrom())) {
                    Msg msg = draftService.updateDraftWithCheck(draftData);
                    if (msg.getCode() == 100) {
                        log.info("update as draft. draft Id:",draftData.getBlogId());
                        return msg.add("blogId", draftData.getBlogId()).add("originId", draftData.getOriginalBlogId())
                                .add("seriesId", draftData.getSeriesId()).add("typeId", draftData.getBlogType());
                    } else {
                        return msg;
                    }
                }
            }
            Msg msg = draftService.insertDraftWithCheck(draftData);
            if (msg.getCode() == 100) {
                log.info("insert as draft. draft Id:{}",draftData.getBlogId());
                return msg.add("blogId", draftData.getBlogId()).add("originId", draftData.getOriginalBlogId())
                        .add("seriesId", draftData.getSeriesId()).add("typeId", draftData.getBlogType());
            } else {
                return msg;
            }
        } else if (BlogController.BlogStatusEnum.getBlogTableList().contains(draftData.getBlogStatus())) {
            // 保存到Blog表
            Blog blog = WrappedBeanCopier.copyProperties(draftData, Blog.class);
            // 这里只是初步根据sourceFrom来判断更新还是新建，Service还会做进一步判断
            if (blog.getBlogId() != null && blog.getBlogId() > 0) {
                if ("blog".equals(blog.getSourceFrom()) || ("draft".equals(blog.getSourceFrom()) && blog.getOriginalBlogId() != null && blog.getOriginalBlogId() > 0)) {
                    Msg msg = blogService.updateBlogWithCheck(blog);
                    if (msg.getCode() == 100) {
                        log.info("update as blog,blog Id:{}", blog.getBlogId());
                        return msg.add("blogId", blog.getBlogId()).add("originId", -1)
                                .add("seriesId", draftData.getSeriesId()).add("typeId", draftData.getBlogType());
                    } else {
                        return msg;
                    }
                }
            }
            Msg msg = blogService.insertBlogWithCheck(blog);
            if (msg.getCode() == 100) {
                log.info("insert as blog, blog Id:{}",blog.getBlogId());
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
     * Admin删除blog或draft
     * @param blogId
     * @param sourceFrom 以此来判断是删除Blog表数据还是Draft表数据
     * @return
     */
    @RequiresAuthentication
    @RequiresRoles(value = {"superAdmin"})
    @ResponseBody
    @RequestMapping(value = "/blog/{blogid}", method = RequestMethod.DELETE)
    public Msg adminDeleteBlog(@PathVariable("blogid") Long blogId, @RequestParam("sf") String sourceFrom) {
        if ("blog".equals(sourceFrom)) {
            List<Blog> blogList = blogService.getBlogByBlogId(blogId);
            if (blogList.size() <= 0) {
                return Msg.fail().setMsg("寻找不到此Blog");
            } else {
                blogService.deleteByPrimaryKey(blogId);
                log.info("delete blog success. Id:{}",blogId);
                return Msg.success().setMsg("删除成功!").add("time", DateUtils.getCurrentDateTime());
            }
        } else if ("draft".equals(sourceFrom)) {
            List<Draft> draftList = draftService.getDraftByBlogI(blogId);
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
     * 供Admin获取所有用户列表
     * @param userSearchDTO
     * @param result
     * @return
     */
    @RequiresAuthentication
    @RequiresRoles(value = {"admin","superAdmin"},logical = Logical.OR)
    @ResponseBody
    @RequestMapping(value = "/user/page", method = RequestMethod.POST)
    public Msg getAdminUserPage(@Validated @RequestBody UserSearchDTO userSearchDTO, BindingResult result) {
        if(result.hasErrors()){
            // 校验失败
            Map<String,Object> map = new HashMap<>();
            List<FieldError> errors =  result.getFieldErrors();
            for(FieldError fieldError:errors){
                log.info("Validated 错误的字段名"+fieldError.getField());
                log.info("Validated 错误信息:"+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().setMsg(map.toString());
        }
        PageHelper.startPage(userSearchDTO.getPn(), 15);
        List<User> userList = userService.getUserBySearch(userSearchDTO);
        PageInfo page = new PageInfo(userList, 10);
        return Msg.success().add("pageinfo", page);
    }

    /**
     * 供Admin修改用户信息
     * @param userData
     * @param result
     * @return
     */
    @RequiresAuthentication
    @RequiresRoles(value = {"superAdmin"})
    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Msg adminSaveUser(@Validated({Insert.class}) @RequestBody User userData, BindingResult result) {
        if(result.hasErrors()){
            // 校验失败
            Map<String,Object> map = new HashMap<>();
            List<FieldError> errors =  result.getFieldErrors();
            for(FieldError fieldError:errors){
                log.info("Validated 错误的字段名"+fieldError.getField());
                log.info("Validated 错误信息:"+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().setMsg(map.toString());
        }
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (userData.getUserId() != null) {
            User testUser = userService.getUserByUserId(userData.getUserId());
            if (testUser != null) {
                if (user.getUserRights() >= testUser.getUserRights()) {
                    return Msg.fail().setMsg("权限不足!");
                }
                if (userData.getUserRights() != null && userData.getUserRights() <= user.getUserRights()) {
                    return Msg.fail().setMsg("权限不足!");
                }
                // 邮箱验证
                if(userData.getUserEmail()!=null) {
                    List<User> testUserList1 = userService.findUserByEmailOnlyFirst(userData.getUserEmail().trim());
                    if(testUserList1.size()>0){
                        for(User u:testUserList1){
                            if(u.getUserEmail().equals(userData.getUserEmail().trim())){
                                if(!u.getUserId().equals(userData.getUserId())){
                                    return Msg.fail().setMsg("邮箱已经注册过了!");
                                }
                            }
                        }
                    }
                    userData.setUserEmail(userData.getUserEmail().trim());
                }else{
                    return Msg.fail().setMsg("邮箱不能为空!");
                }
                // 手机号码验证
                if(userData.getUserTelephoneNumber()!=null) {
                    List<User> testUserList2 = userService.findUserByPhoneNum(userData.getUserTelephoneNumber().trim());
                    if(testUserList2.size()>0){
                        for(User u:testUserList2){
                            if(u.getUserTelephoneNumber().equals(userData.getUserTelephoneNumber().trim())){
                                if(!u.getUserId().equals(userData.getUserId())){
                                    return Msg.fail().setMsg("手机号码已经注册过了!");
                                }
                            }
                        }
                    }
                    userData.setUserTelephoneNumber(userData.getUserTelephoneNumber().trim());
                }else{
                    return Msg.fail().setMsg("手机号码不能为空!");
                }
                // 修改密码
                if (userData.getNewPassword() != null) {
                    // 需要MD5加密密码
                    String newSalt = MD5.getRandomSalt();
                    String newPSW = userData.getNewPassword().trim();
                    String newMd5PSW = MD5.MD5(newPSW, newSalt).toString();
                    userData.setSalt(newSalt);
                    userData.setUserPassword(newMd5PSW);
                }
                userService.updateUserByUserId(userData);
                shiroHelper.clearAuthenticationInfo(userData.getUserEmail());
                log.info("update user success. Id:{}. Email:{}",userData.getUserId(),userData.getUserEmail());
                return Msg.success().setMsg("用户信息更新成功!");
            } else {
                return Msg.fail().setMsg("不存在此用户!");
            }
        } else {
            return Msg.fail().setMsg("参数信息有误!");
        }
    }

    /**
     * 供Admin删除用户
     * @param userId
     * @return
     */
    @RequiresAuthentication
    @RequiresRoles(value = {"superAdmin"})
    @ResponseBody
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
    public Msg adminDeleteUser(@PathVariable("userId") Long userId) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        User testUser = userService.getUserByUserId(userId);
        if(testUser!=null){
            if (user.getUserRights() >= testUser.getUserRights()) {
                return Msg.fail().setMsg("权限不足!");
            }
            testUser.setAccountStatus(UserController.AccountStatusEnum.DELETED_STATUS.getCode());
            testUser.setUserName(testUser.getUserEmail());
            testUser.setUserNickname(testUser.getUserTelephoneNumber());
            testUser.setUserEmail("null");
            testUser.setUserTelephoneNumber("null");
            userService.updateUserByUserId(testUser);
            log.info("delete user success. Id:{}",testUser.getUserId());
            return Msg.success().setMsg("删除成功!");
        }else{
            return Msg.fail().setMsg("不存在此用户!");
        }
    }

    public interface Insert extends Default {

    }
}
