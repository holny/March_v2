package com.hly.march2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;


public class Blog implements Serializable {
    private Long blogId;

    private Long userId;

    @Range(min=0,max = 7,message = "TypeId超出范围")
    private Integer blogStatus;

    @Range(min=0,max = Integer.MAX_VALUE,message = "TypeId超出范围")
    private Integer blogType;

    @Range(min=0,max = Long.MAX_VALUE,message = "SeriesId超出范围")
    private Long seriesId;

    // JSR303校验，自定义正则表达式,message为错误提示信息
    @Pattern(regexp = "(^[a-zA-Z0-9_-]{2,30})|(^[\\u2E80-\\u9FFF]{2,20})"
            ,message = "英文字符数字组合在2-30位，汉字组合在2-20位！")
    private String blogTitle;

    private String blogIntro;

    private Long blogViews;

    private Long blogCommentCount;

    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date blogCreateTime;

    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date blogUpdateTime;

    private Long blogLikeCount;

    private String blogMedia;

    private String blogTag;

    private String blogInfo;

    private Long blogVersion;

    private Long blogPopular;

    private String blogContent;

    private String sourceFrom;

    private Long originalBlogId;

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

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle == null ? null : blogTitle.trim();
    }

    public String getBlogIntro() {
        return blogIntro;
    }

    public void setBlogIntro(String blogIntro) {
        this.blogIntro = blogIntro == null ? null : blogIntro.trim();
    }

    public Long getBlogViews() {
        return blogViews;
    }

    public void setBlogViews(Long blogViews) {
        this.blogViews = blogViews;
    }

    public Long getBlogCommentCount() {
        return blogCommentCount;
    }

    public void setBlogCommentCount(Long blogCommentCount) {
        this.blogCommentCount = blogCommentCount;
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

    public Long getBlogLikeCount() {
        return blogLikeCount;
    }

    public void setBlogLikeCount(Long blogLikeCount) {
        this.blogLikeCount = blogLikeCount;
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

    public Long getBlogPopular() {
        return blogPopular;
    }

    public void setBlogPopular(Long blogPopular) {
        this.blogPopular = blogPopular;
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

    public Long getOriginalBlogId() {
        return originalBlogId;
    }

    public void setOriginalBlogId(Long originalBlogId) {
        this.originalBlogId = originalBlogId;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", userId=" + userId +
                ", blogStatus=" + blogStatus +
                ", blogType=" + blogType +
                ", seriesId=" + seriesId +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogIntro='" + blogIntro + '\'' +
                ", blogViews=" + blogViews +
                ", blogCommentCount=" + blogCommentCount +
                ", blogCreateTime=" + blogCreateTime +
                ", blogUpdateTime=" + blogUpdateTime +
                ", blogLikeCount=" + blogLikeCount +
                ", blogMedia='" + blogMedia + '\'' +
                ", blogTag='" + blogTag + '\'' +
                ", blogInfo='" + blogInfo + '\'' +
                ", blogVersion=" + blogVersion +
                ", blogPopular=" + blogPopular +
                ", blogContent='" + blogContent + '\'' +
                ", sourceFrom='" + sourceFrom + '\'' +
                ", originalBlogId=" + originalBlogId +
                '}';
    }
}