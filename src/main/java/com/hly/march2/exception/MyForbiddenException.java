package com.hly.march2.exception;

import org.apache.shiro.subject.Subject;

public class MyForbiddenException extends SysException {
    private Subject subject;
    public MyForbiddenException(String msg, Subject subject) {
        super("你的账户已被封禁:"+msg);
        this.subject = subject;
    }
}
