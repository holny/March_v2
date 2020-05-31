package com.hly.march2.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hly.march2.entity.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class SeriesVO {
    private Long seriesId;

    private String seriesShortName;

    private String seriesName;

    private Long userId;

    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date seriesCreateTime;

    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date seriesUpdateTime;

    private Integer seriesStatus;

    private String seriesIntro;

    private User user;

    private List<BlogUserVo> blogUserVoList;

    private Long blogNum;

    private Long likeNum;

    private Long viewNum;

    private Long commentNum;

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
        this.seriesShortName = seriesShortName;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
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

    public Integer getSeriesStatus() {
        return seriesStatus;
    }

    public void setSeriesStatus(Integer seriesStatus) {
        this.seriesStatus = seriesStatus;
    }

    public String getSeriesIntro() {
        return seriesIntro;
    }

    public void setSeriesIntro(String seriesIntro) {
        this.seriesIntro = seriesIntro;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getBlogNum() {
        return blogNum;
    }

    public void setBlogNum(Long blogNum) {
        this.blogNum = blogNum;
    }

    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    public Long getViewNum() {
        return viewNum;
    }

    public void setViewNum(Long viewNum) {
        this.viewNum = viewNum;
    }

    public Long getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Long commentNum) {
        this.commentNum = commentNum;
    }

    public List<BlogUserVo> getBlogUserVoList() {
        return blogUserVoList;
    }

    public void setBlogUserVoList(List<BlogUserVo> blogUserVoList) {
        this.blogUserVoList = blogUserVoList;
    }

    @Override
    public String toString() {
        return "SeriesVO{" +
                "seriesId=" + seriesId +
                ", seriesShortName='" + seriesShortName + '\'' +
                ", seriesName='" + seriesName + '\'' +
                ", userId=" + userId +
                ", seriesCreateTime=" + seriesCreateTime +
                ", seriesUpdateTime=" + seriesUpdateTime +
                ", seriesStatus=" + seriesStatus +
                ", seriesIntro='" + seriesIntro + '\'' +
                ", user=" + user +
                ", blogUserVoList=" + blogUserVoList +
                ", blogNum=" + blogNum +
                ", likeNum=" + likeNum +
                ", viewNum=" + viewNum +
                ", commentNum=" + commentNum +
                '}';
    }
}
