package com.hly.march2.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hly.march2.entity.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class MyExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);
    private static final String ERROR_VIEW = "404";

    /**
     * 自定义的ExceptionHandler，注意这里的exception处理顺序从上至下，只到匹配到合适的handler。
     * 本项目是处理exception，获取错误信息，跳转错误页并显示。
     * @param request
     * @param response
     * @param e
     * @return
     * @throws IOException
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = SysException.class)
    public Object myExceptionHandler(HttpServletRequest request,
                                     HttpServletResponse response, SysException e) throws IOException {

        // 获取到异常对象
        SysException ex = null;
        if (e instanceof SysException) {
            ex = (SysException) e;
        } else {
            ex = new SysException("系统正在维护...");
        }
        log.warn("url:{}",request.getRequestURL());
        log.warn("exception.msg:{}",e.getMsg());
        log.warn("exception.string:{}",e.toString());
        e.printStackTrace();
        if (isAjax(request)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            //具体操作
            ObjectMapper mapper = new ObjectMapper();

            writer.write(mapper.writeValueAsString(Msg.fail().setMsg( ex.getMessage()).add("url", request.getRequestURL())));
            //
            writer.flush();
            writer.close();
            return null;
        } else {
            ModelAndView mv = new ModelAndView();
            mv.addObject("msg", ex.getMsg());
            mv.addObject("url", request.getRequestURL());
            mv.setViewName(ERROR_VIEW);
            return mv;
        }

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    public Object exceptionHandler(HttpServletRequest request,
                                   HttpServletResponse response, Exception e) throws IOException {
        log.warn("url:{}",request.getRequestURL());
        log.warn("exception.string:{}",e.toString());
        e.printStackTrace();
        // 获取到异常对象
        Exception ex = null;
        if (e instanceof Exception) {
            ex = (Exception) e;
        } else {
            ex = new Exception("系统正在维护...");
        }
        if (isAjax(request)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            //具体操作
            ObjectMapper mapper = new ObjectMapper();

            writer.write(mapper.writeValueAsString(Msg.fail().setMsg( ex.getMessage()).add("url", request.getRequestURL())));
            //
            writer.flush();
            writer.close();
            return null;
        } else {
            ModelAndView mv = new ModelAndView();
            mv.addObject("msg", ex.getMessage());
            mv.addObject("url", request.getRequestURL());
            mv.setViewName(ERROR_VIEW);
            return mv;
        }
    }

    /**
     * 判断是否是ajax请求
     */
    public static boolean isAjax(HttpServletRequest httpRequest) {
        return (httpRequest.getHeader("X-Requested-With") != null
                && "XMLHttpRequest"
                .equals(httpRequest.getHeader("X-Requested-With").toString()));
    }

    /**
     * 判断请求是否为ajax请求
     *
     * @param request
     * @return
     */
    private boolean isAjaxRequest(HttpServletRequest request) {
        if ((request.getHeader("accept") != null && request.getHeader("accept").contains("application/json")) || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").contains("XMLHttpRequest"))) {
            return true;
        } else {
            return false;
        }
    }
}
