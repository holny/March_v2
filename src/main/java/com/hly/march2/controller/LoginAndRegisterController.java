package com.hly.march2.controller;

import com.hly.march2.entity.Msg;
import com.hly.march2.entity.User;
import com.hly.march2.service.IUserService;
import com.hly.march2.utils.DateUtils;
import com.hly.march2.utils.GetRequestInfo;
import com.hly.march2.utils.MD5;
import com.wf.captcha.utils.CaptchaUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginAndRegisterController {
    private static final Logger log = LoggerFactory.getLogger(LoginAndRegisterController.class);
    @Autowired
    private IUserService userService;

    /**
     * 这里是邀请码，只有填对邀请码才能注册。
     */

    public static final String MY_SHIRO_MSG = "msg";

    public enum RegisterCodeEnum {
        SUPER_ADMIN_CODE(1, "SUPERADMIN"),
        ADMIN_CODE(2, "ADMIN"),
        LEVEL_4_CODE(3, "CCCC"),
        LEVEL_3_CODE(4, "DDDD"),
        LEVEL_2_CODE(5, "EEEE"),
        LEVEL_1_CODE(6, "FFFF");

        private Integer code;
        private String desc;

        RegisterCodeEnum(Integer code, String desc) {
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

        public static Integer getCodeByDesc(String desc) {
            RegisterCodeEnum[] list = RegisterCodeEnum.values();
            for (RegisterCodeEnum item : list) {
                if (item.getDesc().equals(desc)) {
                    return item.getCode();
                }
            }
            return null;
        }
    }

    /**
     * 注意：1.如果前端是serialize()序列化了表单，传输的Content-Type：application/x-www-form-urlencoded; charset=UTF-8。
     * 这里就不需要用@RequestBody,
     * 2.如果前端序列化表单了，但是传之前ajax表明了属性contentType: "application/json"，这里就需要用@RequestBody。
     * <p>
     * 3.如果ajax用serializeArray()序列化，Content-Type：`application/x-www-form-urlencoded; charset=UTF-8。
     * 返回的是json对象而非json字符串，不需要使用@RequestBody。
     * <p>
     * 4.  $.ajax({
     * url: "/SerializeArray",
     * type: "post",
     * data: JSON.stringify(jsonData),
     * contentType: "application/json"
     * 后端需要用@RequestBody
     * <p>
     * 总结：1.@RequestBody接收的是一个Json对象的字符串，而不是一个Json对象/javascript值（重点）。
     * 需要JSON.stringify(json)把js对象转换为JSON。JSON变JS对象用JSON.parse()。
     * 2.所以为什么在使用@RequestBody接收contentType："application/json"的数据时,后台一直显示为null，
     * 是需要将data数据使用JSON.stringify()转换成json字符串,当然也可以使用字符串拼接的方式。
     * 3.@RequestParam 用于默认 Content-Type：application/x-www-form-urlencoded; charset=UTF-8,接收Url指定的参数
     * <p>
     * POST请求，获取内容只能是@RquestBody获取请求体内容。
     * GET请求，可以用@RequestParam 和 @PathVariable
     *
     * @param userAccount 前台如果是Ajax，这里model和redirect都不能是前端跳转，因为ajax是局部刷新。
     *                    只能这里@ResponseBody返回Json，前端用 window.location.href="${pageContext.request.contextPath}/home";
     * @return
     * @RequestParam 和request.getParameter是同一回事。因为使用request.getParameter()方式获取参数，可以处理get 方式中queryString的值， 也可以处理post方式中 body data的值。所以，@RequestParam可以处理get 方式中queryString的值，也可以处理post方式中 body data的值。
     * @RequestParam用来处理Content-Type: 为 application/x-www-form-urlencoded编码的内容，提交方式GET、POST。
     */
    @ResponseBody
    @RequestMapping(value = "/userlogin", method = RequestMethod.POST)
    public Msg login(HttpServletRequest request, @Validated({Login.class}) User userAccount, BindingResult result) {
        if(result.hasErrors()){
            // 校验失败
            Map<String,Object> map = new HashMap<>();
            List<FieldError> errors =  result.getFieldErrors();
            for(FieldError fieldError:errors){
                log.debug("Validated 错误的字段名"+fieldError.getField());
                log.debug("Validated 错误信息:"+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().setMsg(map.values().toString());
        }
        log.info("new loginer coming:{}",userAccount.toString());
        String account = userAccount.getAccount();
        String password = userAccount.getUserPassword();
        String remember = userAccount.getRemember();
        String error = null;
        // 以下结合了easy-captcha jar和shiro 实现了用户登录。
        if (account != null && !"null".equals(account.trim()) && password != null) {
            if (!CaptchaUtil.ver(userAccount.getCaptchaCode(), request)) {
                CaptchaUtil.clear(request);  // 清除session中的验证码
                return Msg.fail().setMsg("验证码不正确");
            }
            //初始化
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(account, password);
            if (remember != null) {
                if (remember.equals("on")) {
                    //说明选择了记住我
                    token.setRememberMe(true);
                } else {
                    token.setRememberMe(false);
                }
            } else {
                token.setRememberMe(false);
            }
            try {
                //登录，即身份校验，由通过Spring注入的UserRealm会自动校验输入的用户名和密码在数据库中是否有对应的值
                // 这里Login会转入Realm进行身份认证和权限认证(本项目是转入到我自定义的UserEmailRealm)。相关详细流程见shiro
                subject.login(token);
                /** subject.getPrincipal()获取的是Realm中身份认证环节(doGetAuthenticationInfo())存入的当前登录的user对象。
                 * 我们可以通过这个，无需查询数据库就很方便的查询到当前登录用户的信息。
                 * 前端也可以通过el表达式获取shiro存在session域的的当前用户新。这两个其实是一样的。
                 * 注意如果给shiro配置了authenticate cache，每次登录就会回先找cache中的信息，没有再走realm流程查数据库。登录验证密码也是。
                 * (author权限认证也可以配置cache,session也可以配置sessionDAO。这三个本项目都配置了，不过是用的Mysql，普遍来说是用redis缓存)
                 * 这里有个注意事项：如果配置了authenticate cache，修改了用户信息并不会立即更新cache，也就不会立即更新session域保存的user对象。
                 * 所以有些即时用户信息，比如用户名,头像等更新后只是数据库更新，不是页面更新。就算重新登录也不会更新(因为直接取了cache数据,cache没更新)。
                 * 所有，我们需要在每次更新用户信息后清除authenticate cache这个用户。清理方法见 ShiroHelper.class
                 **/
                User user = (User) subject.getPrincipal();
                log.info("login----account:{}  password:{}   remember:{}  isAuthenticate:{}",account,password,remember,subject.isAuthenticated());
                User newUser = new User();
                newUser.setUserId(user.getUserId());
                newUser.setUserLastLoginTime(DateUtils.getCurrentDateTime());
                newUser.setUserIp(subject.getSession().getHost());
                newUser.setUserSession(subject.getSession().getId().toString());
                userService.updateUserByUserId(newUser);
                // 注意这里并不起重定向作用，而是前端js获取到redirect的数据，自行重定向。
                return Msg.success().add("redirect", "home");

                //下列错误一般是Realm认证会抛出的错误，我们也可以自行抛出。
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                error = "用户账户不存在";
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                error = "用户名或密码错误";
            } catch (LockedAccountException e) {
                e.printStackTrace();
                error = "该账号已锁定";
            } catch (DisabledAccountException e) {
                e.printStackTrace();
                error = "该账号已禁用，错误信息";
            } catch (ExcessiveAttemptsException e) {
                e.printStackTrace();
                error = "该账号登录失败次数过多，错误信息";
            } catch (Exception e) {
                e.printStackTrace();
                error = "未知错误，错误信息";
            }
        } else {
            error = "请输入用户名和密码";
        }
        return Msg.fail().setMsg(error);
    }

    /**
     * easy-captcha jar
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String host1 = GetRequestInfo.getIpAddress(request);
        Subject subject = SecurityUtils.getSubject();
//        String host2 = subject.getSession().getHost();
        CaptchaUtil.out(request, response);
    }

    /**
     * 检测这个邮箱是否可以注册(数据库中无重复)
     * <p>
     * 前端Ajax GET请求,data:"registerEmail="+registerEmail。这里用@PathVarilabel接收不到。
     * 如果是POST请求，这里用@RequestParam可以接收
     * <p>
     * 经试验表单提交的数据(表单内有验证码同样)前后空格都自动去除，但中间空格还在。
     * Ajax传递过来的数据前后空格还在。
     *
     * @param registerEmail
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/registercheck", method = RequestMethod.GET)
    public Msg registerEmailCheck(String registerEmail) {
        log.debug("registerEmail:{}",registerEmail);
        String regx = "(^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$)";
        if (!registerEmail.matches(regx)){
            return Msg.fail().setMsg("邮箱不符合规范");
        }
        if (userService.findUserByEmailOnlyFirst(registerEmail.trim()).size() > 0) {
            // 存在与此邮箱相同的用户，说明前台传来的是重复
            return Msg.fail().setMsg("此邮箱已存在");
        } else {
            return Msg.success().setMsg("此邮箱可以注册");
        }
    }

    /**
     * 注册功能
     *
     * 经试验表单提交的数据(表单内有验证码同样)前后空格都自动去除，但中间空格还在。
     * Ajax传递过来的数据前后空格还在。
     *
     * @param request
     * @param register
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Msg register(HttpServletRequest request, @Validated({Insert.class}) User register, BindingResult result) {
        if(result.hasErrors()){
            // 校验失败
            Map<String,Object> map = new HashMap<>();
            List<FieldError> errors =  result.getFieldErrors();
            for(FieldError fieldError:errors){
                log.debug("Validated 错误的字段名"+fieldError.getField());
                log.debug("Validated 错误信息:"+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().setMsg(map.values().toString());
        }
        log.info("new register coming:{}",register.toString());
        if (!CaptchaUtil.ver(register.getCaptchaCode(), request)) {
            CaptchaUtil.clear(request);  // 清除session中的验证码
            return Msg.fail().setMsg("验证码不正确");
        } else {
            // 邀请码验证
            if (register.getInvitationCode() == null || RegisterCodeEnum.getCodeByDesc(register.getInvitationCode()) == null) {
                return Msg.fail().setMsg( "邀请码不正确");
            }
            // 1. 要检查数据格式是否正确，不过后面一起补上，这里就先检查数据是否完全，后面用正则化
            if (!((register.getUserEmail() != null && !register.getUserEmail().contains(" "))
                    && (register.getUserPassword() != null && !register.getUserPassword().contains(" "))
                    && (register.getUserName() != null && !register.getUserName().contains(" ")))) {
                return Msg.fail().setMsg("信息为空或者存在空格");
            }
            // 2. 先检查账号是否已经存在
            if (userService.findUserByEmailOnlyFirst(register.getUserEmail().trim()).size() > 0) {
                // 存在与此邮箱相同的用户，说明前台传来的是重复
                return Msg.fail().setMsg("邮箱已经注册过了！");
            } else {
                register.setUserEmail(register.getUserEmail().trim());
            }
            // 手机号码验证
            if (register.getUserTelephoneNumber() != null) {
                List<User> testUserList2 = userService.findUserByPhoneNum(register.getUserTelephoneNumber().trim());
                if (testUserList2.size() > 0) {
                    for (User u : testUserList2) {
                        if (u.getUserTelephoneNumber().equals(register.getUserTelephoneNumber().trim())) {
                            if (!u.getUserId().equals(register.getUserId())) {
                                // 数据库有手机号码相同，但UserId不同的账号，就返回失败
                                return Msg.fail().setMsg("手机号码已经注册过了！");
                            }
                        }
                    }
                }
                register.setUserTelephoneNumber(register.getUserTelephoneNumber().trim());
            }
            // 3. 验证通过就要补全基本信息。
            register.setUserRegisterTime(DateUtils.getCurrentDateTime());
            register.setUserLastLoginTime(DateUtils.getCurrentDateTime());
            register.setUserIp(GetRequestInfo.getIpAddress(request));
            register.setUserProfilePic("default.jpeg");
            if (register.getInvitationCode() != null || RegisterCodeEnum.getCodeByDesc(register.getInvitationCode()) != null) {
                register.setUserRights(RegisterCodeEnum.getCodeByDesc(register.getInvitationCode()));
            }else{
                register.setUserRights(UserController.RolesEnum.LEVEL_4_ROLE.getCode());
            }
            register.setAccountStatus(UserController.AccountStatusEnum.INCOMPLETE_STATUS.getCode());
            // 需要MD5加密密码
            /**
             * shiro认证身份建议是用加密手段来加密密码再保存到数据库。这里用的是MD5盐值加密。
             */
            String salt = MD5.getRandomSalt();
            String originalPSW = register.getUserPassword().trim();
            String userEmail = register.getUserEmail().trim();         // 这里注册的是邮箱为账号，下面用于shiro
            String md5PSW = MD5.MD5(originalPSW, salt).toString();
            register.setUserPassword(md5PSW);
            register.setSalt(salt);
            register.setUserCredit(0);
            // 4. 补全完毕，插入数据库
            userService.registerUserAccount(register);
            log.debug("register done . register Id:{}",register.getUserId());
            // 5. 插入完毕,shiro认证
            String error = null;
            //初始化
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userEmail, originalPSW);
            token.setRememberMe(false);
            try {
                //登录，即身份校验，由通过Spring注入的UserRealm会自动校验输入的用户名和密码在数据库中是否有对应的值
                subject.login(token);
                return Msg.success().add("redirect", "home");
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                error = "用户账户不存在";
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                error = "用户名或密码错误";
            } catch (LockedAccountException e) {
                e.printStackTrace();
                error = "该账号已锁定";
            } catch (DisabledAccountException e) {
                e.printStackTrace();
                error = "该账号已禁用，错误信息";
            } catch (ExcessiveAttemptsException e) {
                e.printStackTrace();
                error = "该账号登录失败次数过多，错误信息";
            } catch (Exception e) {
                e.printStackTrace();
                error = "未知错误，错误信息";
            }
            // 走到这说明最终失败了。
            return Msg.fail().setMsg(error);
        }
    }

    public interface Login  extends Default {

    }

    public interface Insert  extends Default {

    }
}
