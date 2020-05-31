package com.hly.march2.controller;

import org.springframework.web.bind.WebDataBinder;

//@ControllerAdvice
public class GlobalControllerAdvice {

    /**
     * 使用initBinder在controller接收到数据之前转换数据。
     * InitBinder只对其所在的Controller起作用，不过通过@ControllerAdvice可作用全局
     * 这里注释掉了，不起作用。
     * @param binder
     */
//    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("initBinder---"+binder.getObjectName());
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy年MM月dd日"), true));
//        binder.registerCustomEditor(String.class,  new EmptyStringEditor());

//        binder.registerCustomEditor(Date.class,
//                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));



    }

}
