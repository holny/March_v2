package com.hly.march2.exception;

public class MyNotLoginException extends SysException {
    public MyNotLoginException(String msg) {
        super("请先登录:"+msg);
    }
}
