package com.hly.march2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class BlogType implements Serializable {
    private Integer typeId;

    private String typeName;

    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date typeCreateTime;

    private Long userId;

    private Integer typeStatus;

    private String typeLink;

    private Long typePopular;

    private Integer typeNum;

    private String typeShortName;

    private String fartherType;

    private Integer fartherId;

    private static final long serialVersionUID = 1L;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Date getTypeCreateTime() {
        return typeCreateTime;
    }

    public void setTypeCreateTime(Date typeCreateTime) {
        this.typeCreateTime = typeCreateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(Integer typeStatus) {
        this.typeStatus = typeStatus;
    }

    public String getTypeLink() {
        return typeLink;
    }

    public void setTypeLink(String typeLink) {
        this.typeLink = typeLink == null ? null : typeLink.trim();
    }

    public Long getTypePopular() {
        return typePopular;
    }

    public void setTypePopular(Long typePopular) {
        this.typePopular = typePopular;
    }

    public Integer getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(Integer typeNum) {
        this.typeNum = typeNum;
    }

    public String getTypeShortName() {
        return typeShortName;
    }

    public void setTypeShortName(String typeShortName) {
        this.typeShortName = typeShortName == null ? null : typeShortName.trim();
    }

    public String getFartherType() {
        return fartherType;
    }

    public void setFartherType(String fartherType) {
        this.fartherType = fartherType == null ? null : fartherType.trim();
    }

    public Integer getFartherId() {
        return fartherId;
    }

    public void setFartherId(Integer fartherId) {
        this.fartherId = fartherId;
    }

    @Override
    public String toString() {
        return "BlogType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", typeCreateTime=" + typeCreateTime +
                ", userId=" + userId +
                ", typeStatus=" + typeStatus +
                ", typeLink='" + typeLink + '\'' +
                ", typePopular=" + typePopular +
                ", typeNum=" + typeNum +
                ", typeShortName='" + typeShortName + '\'' +
                ", fartherType='" + fartherType + '\'' +
                ", fartherId=" + fartherId +
                '}';
    }
}