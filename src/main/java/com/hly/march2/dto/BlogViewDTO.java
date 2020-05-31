package com.hly.march2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class BlogViewDTO implements Serializable {
    private Long blogId;

    private Long userId;

    private String blogTitle;

    private Long blogViews;

    private Long blogCommentCount;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date blogCreateDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date blogUpdateDate;

    private Long blogLikeCount;

    private String blogBriefIntroduction;

    private String blogStatus;

    private String blogTag;

    private String blogCategory;

    private String blogInfo;

    private Long blogVersion;

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
        this.blogTitle = blogTitle;
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

    public Date getBlogCreateDate() {
        return blogCreateDate;
    }

    public void setBlogCreateDate(Date blogCreateDate) {
        this.blogCreateDate = blogCreateDate;
    }

    public Date getBlogUpdateDate() {
        return blogUpdateDate;
    }

    public void setBlogUpdateDate(Date blogUpdateDate) {
        this.blogUpdateDate = blogUpdateDate;
    }

    public Long getBlogLikeCount() {
        return blogLikeCount;
    }

    public void setBlogLikeCount(Long blogLikeCount) {
        this.blogLikeCount = blogLikeCount;
    }

    public String getBlogBriefIntroduction() {
        return blogBriefIntroduction;
    }

    public void setBlogBriefIntroduction(String blogBriefIntroduction) {
        this.blogBriefIntroduction = blogBriefIntroduction;
    }

    public String getBlogStatus() {
        return blogStatus;
    }

    public void setBlogStatus(String blogStatus) {
        this.blogStatus = blogStatus;
    }

    public String getBlogTag() {
        return blogTag;
    }

    public void setBlogTag(String blogTag) {
        this.blogTag = blogTag;
    }

    public String getBlogCategory() {
        return blogCategory;
    }

    public void setBlogCategory(String blogCategory) {
        this.blogCategory = blogCategory;
    }

    public String getBlogInfo() {
        return blogInfo;
    }

    public void setBlogInfo(String blogInfo) {
        this.blogInfo = blogInfo;
    }

    public Long getBlogVersion() {
        return blogVersion;
    }

    public void setBlogVersion(Long blogVersion) {
        this.blogVersion = blogVersion;
    }

    @Override
    public String toString() {
        return "BriefBlog{" +
                "blogId=" + blogId +
                ", userId=" + userId +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogViews=" + blogViews +
                ", blogCommentCount=" + blogCommentCount +
                ", blogCreateDate=" + blogCreateDate +
                ", blogUpdateDate=" + blogUpdateDate +
                ", blogLikeCount=" + blogLikeCount +
                ", blogBriefIntroduction='" + blogBriefIntroduction + '\'' +
                ", blogStatus='" + blogStatus + '\'' +
                ", blogTag='" + blogTag + '\'' +
                ", blogCategory='" + blogCategory + '\'' +
                ", blogInfo='" + blogInfo + '\'' +
                ", blogVersion=" + blogVersion +
                '}';
    }
}
