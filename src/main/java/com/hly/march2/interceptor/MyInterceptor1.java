package com.hly.march2.interceptor;

import com.hly.march2.controller.MainController;
import com.hly.march2.controller.UserController;
import com.hly.march2.entity.User;
import com.hly.march2.exception.MyForbiddenException;
import com.hly.march2.schedule.ProjectSchedule;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 自定义拦截器
 *
 * 配置多个拦截器后,拦截器的启用顺序跟拦截路径无关，只跟springmvc.xml里配置拦截器的顺序有关，
 * 如下例子(先配置拦截器1)顺序：1.pre->2.pre->controller->2.post->1.post->success.jsp->2.after->1.after
 */
public class MyInterceptor1 extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(MyInterceptor1.class);
    /**
     * 预处理，controller方法执行前。
     * 注意:需要在springmvc.xml配置拦截器。
     * @param request
     * @param response
     * @param handler
     * @return return true:放行，执行下一个拦截器，如果没有,执行controller中的方法。
     * return false:不放行
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle执行了");
        Subject subject = SecurityUtils.getSubject();
        // 这里是作用在controller之前判断用户是否被封禁，从而禁止登录
        if (subject.isRemembered() || subject.isAuthenticated()) {
            User comer = (User)subject.getPrincipal();
            log.info("preHandle    comer:{}",comer.toString());
            if(UserController.AccountStatusEnum.getForbiddenList().contains(comer.getAccountStatus())){
                throw new MyForbiddenException("当前账号已被禁止!",subject);
            }
        }
        // 如果拦截，不放行return false，可以设置请求转发到error页面。
        // 如果return true，请求转发也会跳转到error.jsp。但是后续的controller还是会执行(其中的return success，在浏览器并不会看到success.jsp，不知为什么)
        //request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request,response);

        return true;
    }

    /**
     * 后处理方法,controller方法执行之后,success.jsp执行之前
     *
     * 供前端获取当前网站运行总时间和总访问人数。
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        HttpSession session = request.getSession();
        String host = subject.getSession().getHost();
        log.info("postHandle  comer host:{}",host);
        Long visitorNum = ProjectSchedule.guestIpCheckAndPush(host);
        Long runningSec = ProjectSchedule.getServerRunningTime();
        session.setAttribute("visitorNum", visitorNum);
        session.setAttribute("runningSec", runningSec);
        session.setAttribute("githubLink", MainController.MyLinkEnum.GITHUB_LINK.getDesc());
        session.setAttribute("weiboLink", MainController.MyLinkEnum.WEIBO_LINK.getDesc());
        session.setAttribute("weixinLink", MainController.MyLinkEnum.WEIXIN_LINK.getDesc());
        session.setAttribute("emailLink", MainController.MyLinkEnum.EMAIL_LINK.getDesc());
        session.setAttribute("defaultLink", MainController.MyLinkEnum.DEFAULT_LINK.getDesc());
        log.info("postHandle 执行了  visitorNum:{}  runningSec:{}",visitorNum,runningSec);
        if (subject.isRemembered() || subject.isAuthenticated()) {
            User comer = (User)subject.getPrincipal();
            log.info("postHandle    comerId:{} is remembered or authenticated",comer.getUserId());
            session.setAttribute("loginerId", comer.getUserId());
            session.setAttribute("loginerRights", comer.getUserRights());
            session.setAttribute("loginerPic", "file/showPic/"+comer.getUserProfilePic());
        }

    }

    /**
     * success.jsp执行后执行。
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("afterCompletion执行了");
    }
}
