package com.hly.march2.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hly.march2.entity.BlogSeries;
import com.hly.march2.entity.BlogType;
import com.hly.march2.entity.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class BlogUserVo implements Serializable {
    private Long blogId;

    private Long userId;

    private String blogTitle;

    private Long blogViews;

    private Long blogCommentCount;

    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date blogCreateTime;

    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date blogUpdateTime;

    private Long blogLikeCount;

    private String blogIntro;

    private Integer blogStatus;

    private Integer blogType;

    private String blogTypeName;

    private Long seriesId;

    private String blogSeriesName;

    private String blogInfo;

    private Long blogVersion;

    private String blogMedia;

    private String blogTag;

    private Long blogPopular;

    private String blogContent;

    private BlogType blogTypeEntity;

    private BlogSeries blogSeriesEntity;

    // Draft比Blog多出此字段
    private Long originalBlogId;

    // 判别数据来自哪个表
    private String sourceFrom;

    private User user;

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

    public String getBlogIntro() {
        return blogIntro;
    }

    public void setBlogIntro(String blogIntro) {
        this.blogIntro = blogIntro;
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

    public String getBlogTypeName() {
        return blogTypeName;
    }

    public void setBlogTypeName(String blogTypeName) {
        this.blogTypeName = blogTypeName;
    }

    public Long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Long seriesId) {
        this.seriesId = seriesId;
    }

    public String getBlogSeriesName() {
        return blogSeriesName;
    }

    public void setBlogSeriesName(String blogSeriesName) {
        this.blogSeriesName = blogSeriesName;
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

    public String getBlogMedia() {
        return blogMedia;
    }

    public void setBlogMedia(String blogMedia) {
        this.blogMedia = blogMedia;
    }

    public String getBlogTag() {
        return blogTag;
    }

    public void setBlogTag(String blogTag) {
        this.blogTag = blogTag;
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
        this.blogContent = blogContent;
    }

    public BlogType getBlogTypeEntity() {
        return blogTypeEntity;
    }

    public void setBlogTypeEntity(BlogType blogTypeEntity) {
        this.blogTypeEntity = blogTypeEntity;
    }

    public BlogSeries getBlogSeriesEntity() {
        return blogSeriesEntity;
    }

    public void setBlogSeriesEntity(BlogSeries blogSeriesEntity) {
        this.blogSeriesEntity = blogSeriesEntity;
    }

    public Long getOriginalBlogId() {
        return originalBlogId;
    }

    public void setOriginalBlogId(Long originalBlogId) {
        this.originalBlogId = originalBlogId;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BlogUserVo{" +
                "blogId=" + blogId +
                ", userId=" + userId +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogViews=" + blogViews +
                ", blogCommentCount=" + blogCommentCount +
                ", blogCreateTime=" + blogCreateTime +
                ", blogUpdateTime=" + blogUpdateTime +
                ", blogLikeCount=" + blogLikeCount +
                ", blogIntro='" + blogIntro + '\'' +
                ", blogStatus=" + blogStatus +
                ", blogType=" + blogType +
                ", blogTypeName='" + blogTypeName + '\'' +
                ", seriesId=" + seriesId +
                ", blogSeriesName='" + blogSeriesName + '\'' +
                ", blogInfo='" + blogInfo + '\'' +
                ", blogVersion=" + blogVersion +
                ", blogMedia='" + blogMedia + '\'' +
                ", blogTag='" + blogTag + '\'' +
                ", blogPopular=" + blogPopular +
                ", blogContent='" + blogContent + '\'' +
                ", blogTypeEntity=" + blogTypeEntity +
                ", blogSeriesEntity=" + blogSeriesEntity +
                ", originalBlogId=" + originalBlogId +
                ", sourceFrom='" + sourceFrom + '\'' +
                ", user=" + user +
                '}';
    }
}
