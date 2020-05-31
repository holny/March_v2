package com.hly.march2.shiro;

import org.apache.shiro.session.mgt.SimpleSession;

import java.io.Serializable;
import java.util.Date;

public class MyShiroSession extends SimpleSession implements Serializable {
    public static final String SESSION_UPDATE_FALG = "SESSION_UPDATE_FALG";
    public static final String SESSION_TIMESTAMP_FOR_UPDATE = "SESSION_TIMESTAMP_FOR_UPDATE";

    @Override
    public void setLastAccessTime(Date lastAccessTime) {
        super.setLastAccessTime(lastAccessTime);
    }

    @Override
    public void setAttribute(Object key, Object value) {
        super.setAttribute(key, value);
    }

    @Override
    public Object removeAttribute(Object key) {
        return super.removeAttribute(key);
    }
}
