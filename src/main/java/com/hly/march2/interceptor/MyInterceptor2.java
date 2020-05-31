package com.hly.march2.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 * 这个借口不需要实现方法，需要实现时，快捷键control+o。
 *
 * 配置多个拦截器后,拦截器的启用顺序跟拦截路径无关，只跟springmvc.xml里配置拦截器的顺序有关，
 * 如下例子(先配置拦截器1)顺序：1.pre->2.pre->controller->2.post->1.post->success.jsp->2.after->1.after
 */
public class MyInterceptor2 implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(MyInterceptor2.class);
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
        log.debug("preHandle执行了");
        // 如果拦截，不放行return false，可以设置请求转发到error页面。
        // 如果return true，请求转发也会跳转到error.jsp。但是后续的controller还是会执行(其中的return success，在浏览器并不会看到success.jsp，不知为什么)
        //request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request,response);

        return true;
    }

    /**
     * 后处理方法,controller方法执行之后,success.jsp执行之前
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("postHandle执行了");
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
