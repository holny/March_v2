package com.hly.march2.entity;

import java.io.Serializable;
import java.util.Date;

public class MyServerInfo implements Serializable {
    private Long id;

    private Date firstStartTime;

    private Long totalRunningTime;

    private Date thisStartTime;

    private Date thisEndTime;

    private Long totalVisitorNum;

    private Long thisVisitorNum;

    private Date thisUpdateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFirstStartTime() {
        return firstStartTime;
    }

    public void setFirstStartTime(Date firstStartTime) {
        this.firstStartTime = firstStartTime;
    }

    public Long getTotalRunningTime() {
        return totalRunningTime;
    }

    public void setTotalRunningTime(Long totalRunningTime) {
        this.totalRunningTime = totalRunningTime;
    }

    public Date getThisStartTime() {
        return thisStartTime;
    }

    public void setThisStartTime(Date thisStartTime) {
        this.thisStartTime = thisStartTime;
    }

    public Date getThisEndTime() {
        return thisEndTime;
    }

    public void setThisEndTime(Date thisEndTime) {
        this.thisEndTime = thisEndTime;
    }

    public Long getTotalVisitorNum() {
        return totalVisitorNum;
    }

    public void setTotalVisitorNum(Long totalVisitorNum) {
        this.totalVisitorNum = totalVisitorNum;
    }

    public Long getThisVisitorNum() {
        return thisVisitorNum;
    }

    public void setThisVisitorNum(Long thisVisitorNum) {
        this.thisVisitorNum = thisVisitorNum;
    }

    public Date getThisUpdateTime() {
        return thisUpdateTime;
    }

    public void setThisUpdateTime(Date thisUpdateTime) {
        this.thisUpdateTime = thisUpdateTime;
    }

    @Override
    public String toString() {
        return "MyServerInfo{" +
                "id=" + id +
                ", firstStartTime=" + firstStartTime +
                ", totalRunningTime=" + totalRunningTime +
                ", thisStartTime=" + thisStartTime +
                ", thisEndTime=" + thisEndTime +
                ", totalVisitorNum=" + totalVisitorNum +
                ", thisVisitorNum=" + thisVisitorNum +
                ", thisUpdateTime=" + thisUpdateTime +
                '}';
    }
}