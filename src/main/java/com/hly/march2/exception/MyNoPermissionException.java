package com.hly.march2.exception;

public class MyNoPermissionException extends SysException {

    public MyNoPermissionException(String msg) {
        super("无此权限:"+msg);
    }
}
