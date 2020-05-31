package com.hly.march2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class Draft implements Serializable {

    @Range(min=-2,max = Long.MAX_VALUE,message = "Blog Id超出范围")
    private Long blogId;

    @Range(min=0,max = Long.MAX_VALUE,message = "User Id超出范围")
    private Long userId;

    @Size(max=50, message="标题长度最大50")
    private String blogTitle;

    private Integer blogStatus;

    private Integer blogType;

    @Range(min=0,max = Long.MAX_VALUE,message = "Series Id超出范围")
    private Long seriesId;

    @Null(message = "无创建时间")
    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date blogCreateTime;

    @Null(message = "无更新时间")
    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date blogUpdateTime;

    @Size(max=300, message="简介长度最大300")
    private String blogIntro;

    @Size(max=200, message="Blog media长度最大200")
    private String blogMedia;

    @Size(max=20, message="Blog tag长度最大20")
    private String blogTag;

    @Size(max=100, message="Blog info长度最大100")
    private String blogInfo;

    private Long blogVersion;

    @Range(min=-2,max = Long.MAX_VALUE,message = "origin blog Id超出范围")
    private Long originalBlogId;

    @Size(max=5000, message="Blog内容长度最大5000")
    private String blogContent;

    private String sourceFrom;

    @Size(max=20, message="Series name长度最大20")
    private String newSeries;

    @Size(max=20, message="Type name长度最大20")
    private String newType;

    private Boolean newSeriesCheck;

    private Boolean newTypeCheck;

    private static final long serialVersionUID = 1L;

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle == null ? null : blogTitle.trim();
    }

    public Integer getBlogStatus() {
        return blogStatus;
    }

    public void setBlogStatus(Integer blogStatus) {
        this.blogStatus = blogStatus;
    }

    public Integer getBlogType() {
        return blogType;
    }

    public void setBlogType(Integer blogType) {
        this.blogType = blogType;
    }

    public Long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Long seriesId) {
        this.seriesId = seriesId;
    }

    public Date getBlogCreateTime() {
        return blogCreateTime;
    }

    public void setBlogCreateTime(Date blogCreateTime) {
        this.blogCreateTime = blogCreateTime;
    }

    public Date getBlogUpdateTime() {
        return blogUpdateTime;
    }

    public void setBlogUpdateTime(Date blogUpdateTime) {
        this.blogUpdateTime = blogUpdateTime;
    }

    public String getBlogIntro() {
        return blogIntro;
    }

    public void setBlogIntro(String blogIntro) {
        this.blogIntro = blogIntro == null ? null : blogIntro.trim();
    }

    public String getBlogMedia() {
        return blogMedia;
    }

    public void setBlogMedia(String blogMedia) {
        this.blogMedia = blogMedia == null ? null : blogMedia.trim();
    }

    public String getBlogTag() {
        return blogTag;
    }

    public void setBlogTag(String blogTag) {
        this.blogTag = blogTag == null ? null : blogTag.trim();
    }

    public String getBlogInfo() {
        return blogInfo;
    }

    public void setBlogInfo(String blogInfo) {
        this.blogInfo = blogInfo == null ? null : blogInfo.trim();
    }

    public Long getBlogVersion() {
        return blogVersion;
    }

    public void setBlogVersion(Long blogVersion) {
        this.blogVersion = blogVersion;
    }

    public Long getOriginalBlogId() {
        return originalBlogId;
    }

    public void setOriginalBlogId(Long originalBlogId) {
        this.originalBlogId = originalBlogId;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent == null ? null : blogContent.trim();
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    public String getNewSeries() {
        return newSeries;
    }

    public void setNewSeries(String newSeries) {
        this.newSeries = newSeries;
    }

    public String getNewType() {
        return newType;
    }

    public void setNewType(String newType) {
        this.newType = newType;
    }

    public Boolean getNewSeriesCheck() {
        return newSeriesCheck;
    }

    public void setNewSeriesCheck(Boolean newSeriesCheck) {
        this.newSeriesCheck = newSeriesCheck;
    }

    public Boolean getNewTypeCheck() {
        return newTypeCheck;
    }

    public void setNewTypeCheck(Boolean newTypeCheck) {
        this.newTypeCheck = newTypeCheck;
    }

    @Override
    public String toString() {
        return "Draft{" +
                "blogId=" + blogId +
                ", userId=" + userId +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogStatus=" + blogStatus +
                ", blogType=" + blogType +
                ", seriesId=" + seriesId +
                ", blogCreateTime=" + blogCreateTime +
                ", blogUpdateTime=" + blogUpdateTime +
                ", blogIntro='" + blogIntro + '\'' +
                ", blogMedia='" + blogMedia + '\'' +
                ", blogTag='" + blogTag + '\'' +
                ", blogInfo='" + blogInfo + '\'' +
                ", blogVersion=" + blogVersion +
                ", originalBlogId=" + originalBlogId +
                ", blogContent='" + blogContent + '\'' +
                ", sourceFrom='" + sourceFrom + '\'' +
                ", newSeries='" + newSeries + '\'' +
                ", newType='" + newType + '\'' +
                ", newSeriesCheck=" + newSeriesCheck +
                ", newTypeCheck=" + newTypeCheck +
                '}';
    }
}