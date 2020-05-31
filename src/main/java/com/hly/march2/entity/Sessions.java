package com.hly.march2.entity;

import java.io.Serializable;
import java.util.Date;

public class Sessions implements Serializable {
    private String id;

    private String session;

    private Date updateTime;

    private String ipAddress;

    private String userName;

    public Sessions() {
    }

    public Sessions(String id, String session) {
        this.id = id;
        this.session = session;
    }

    public Sessions(String id, String session,Date updateTime) {
        this.id = id;
        this.session = session;
        this.updateTime = updateTime;
    }

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session == null ? null : session.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
}