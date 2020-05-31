package com.hly.march2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class BlogSeries implements Serializable {
    private Long seriesId;

    @Size(max=20, message="Series short name长度最大20")
    @Pattern(regexp = "(^\\s?$)|(^(([a-zA-Z09_\\.\\u4e00-\\u9fa5\\.-]+)([\\s]*)([a-zA-Z09_\\.\\u4e00-\\u9fa5\\.-]+)){0,20}$)"
            ,message = "Series short name必须是字母或者汉字组成(最大20位)")
    private String seriesShortName;

    @Size(max=20, message="Series name长度最大20")
    @Pattern(regexp = "(^\\s?$)|(^(([a-zA-Z09_\\.\\u4e00-\\u9fa5\\.-]+)([\\s]*)([a-zA-Z09_\\.\\u4e00-\\u9fa5\\.-]+)){0,20}$)"
            ,message = "Series name必须是字母或者汉字组成(最大20位)")
    private String seriesName;

    private Long userId;

    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date seriesCreateTime;

    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date seriesUpdateTime;

    private Integer seriesNum;

    private Integer seriesStatus;

    private Integer seriesType;

    private String seriesLink;

    private Long seriesPopular;

    private String seriesIntro;

    private String newSeriesName;

    private Long newSeriesId;

    private static final long serialVersionUID = 1L;

    public Long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Long seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesShortName() {
        return seriesShortName;
    }

    public void setSeriesShortName(String seriesShortName) {
        this.seriesShortName = seriesShortName == null ? null : seriesShortName.trim();
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName == null ? null : seriesName.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getSeriesCreateTime() {
        return seriesCreateTime;
    }

    public void setSeriesCreateTime(Date seriesCreateTime) {
        this.seriesCreateTime = seriesCreateTime;
    }

    public Date getSeriesUpdateTime() {
        return seriesUpdateTime;
    }

    public void setSeriesUpdateTime(Date seriesUpdateTime) {
        this.seriesUpdateTime = seriesUpdateTime;
    }

    public Integer getSeriesNum() {
        return seriesNum;
    }

    public void setSeriesNum(Integer seriesNum) {
        this.seriesNum = seriesNum;
    }

    public Integer getSeriesStatus() {
        return seriesStatus;
    }

    public void setSeriesStatus(Integer seriesStatus) {
        this.seriesStatus = seriesStatus;
    }

    public Integer getSeriesType() {
        return seriesType;
    }

    public void setSeriesType(Integer seriesType) {
        this.seriesType = seriesType;
    }

    public String getSeriesLink() {
        return seriesLink;
    }

    public void setSeriesLink(String seriesLink) {
        this.seriesLink = seriesLink == null ? null : seriesLink.trim();
    }

    public Long getSeriesPopular() {
        return seriesPopular;
    }

    public void setSeriesPopular(Long seriesPopular) {
        this.seriesPopular = seriesPopular;
    }

    public String getSeriesIntro() {
        return seriesIntro;
    }

    public void setSeriesIntro(String seriesIntro) {
        this.seriesIntro = seriesIntro == null ? null : seriesIntro.trim();
    }

    public String getNewSeriesName() {
        return newSeriesName;
    }

    public void setNewSeriesName(String newSeriesName) {
        this.newSeriesName = newSeriesName;
    }

    public Long getNewSeriesId() {
        return newSeriesId;
    }

    public void setNewSeriesId(Long newSeriesId) {
        this.newSeriesId = newSeriesId;
    }

    @Override
    public String toString() {
        return "BlogSeries{" +
                "seriesId=" + seriesId +
                ", seriesShortName='" + seriesShortName + '\'' +
                ", seriesName='" + seriesName + '\'' +
                ", userId=" + userId +
                ", seriesCreateTime=" + seriesCreateTime +
                ", seriesUpdateTime=" + seriesUpdateTime +
                ", seriesNum=" + seriesNum +
                ", seriesStatus=" + seriesStatus +
                ", seriesType=" + seriesType +
                ", seriesLink='" + seriesLink + '\'' +
                ", seriesPopular=" + seriesPopular +
                ", seriesIntro='" + seriesIntro + '\'' +
                ", newSeriesName='" + newSeriesName + '\'' +
                ", newSeriesId=" + newSeriesId +
                '}';
    }
}