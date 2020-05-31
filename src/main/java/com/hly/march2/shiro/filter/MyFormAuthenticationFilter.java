package com.hly.march2.shiro.filter;

import com.hly.march2.controller.LoginAndRegisterController;
import com.hly.march2.entity.User;
import com.hly.march2.service.IUserService;
import com.wf.captcha.utils.CaptchaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.checkerframework.framework.qual.StubFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.util.StringUtils;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**由于前台登录是ajax请求，表单处理成功后返回302重定向要求(SuccessUrl),ajax不能重定向,所以还是用login controller手动处理登录请求。
 因此setSuccessUrl对于login controller是无用的，需要手动设置。

 这里已经调通，但是如上原因弃用。
 **/
@Slf4j
public class MyFormAuthenticationFilter  extends FormAuthenticationFilter {

    /**
     * 验证验证码
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws Exception
     */
    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String captchaCode = httpServletRequest.getParameter("captchaCode");
        log.info("captchaCode:{}",captchaCode);
        log.info("getFailureKeyAttribute:{}",getFailureKeyAttribute());
        if (CaptchaUtil.ver(captchaCode, httpServletRequest)) {
            log.info("captchaCode:{} , verify pass",captchaCode);
            return super.onPreHandle(request, response, mappedValue);
        }else{
            CaptchaUtil.clear(httpServletRequest);  // 清除session中的验证码
            log.info("captchaCode:{} , verify FAIL ",captchaCode);
            httpServletRequest.setAttribute(LoginAndRegisterController.MY_SHIRO_MSG, "验证码不正确");
            return true;
        }

    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        String className = e.getClass().getName();
        String message = "";
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        if (UnknownAccountException.class.getName().equals(className)) {
            message = "用户名错误!";
        } else if (IncorrectCredentialsException.class.getName().equals(className)) {
            message = "密码错误!";
        } else if (LockedAccountException.class.getName().equals(className)) {
            message = "该账号已锁定!";
        } else if (DisabledAccountException.class.getName().equals(className)) {
            message = "该账号已禁用!";
        } else if (ExcessiveAttemptsException.class.getName().equals(className)) {
            message = "该账号登录失败次数过多!";
        } else if (e.getMessage() != null && StringUtils.startsWith(e.getMessage(), "msg:")) {
            message = StringUtils.replace(e.getMessage(), "msg:", "");
        } else {
            message = "系统出现点问题，请稍后再试！";
            e.printStackTrace(); // 输出到控制台
        }
        CaptchaUtil.clear(httpServletRequest);  // 清除session中的验证码
        httpServletRequest.setAttribute(LoginAndRegisterController.MY_SHIRO_MSG, message);
        log.info("login fail, reason:{}",message);
        return super.onLoginFailure(token, e, httpServletRequest, response);
    }

    /**

     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        User loginer = (User) subject.getPrincipal();
        log.info("login success, loginer:{}",loginer.toString());
//        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        return super.onLoginSuccess(token, subject, request, response);
    }
}
