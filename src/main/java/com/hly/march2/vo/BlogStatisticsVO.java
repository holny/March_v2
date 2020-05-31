package com.hly.march2.vo;

public class BlogStatisticsVO {
    private Long userId;

    private Long count;

    private Long likeSUM;

    private Long commentSUM;

    private Long viewSUM;

    private Long popularSUM;

    private String sourceFrom;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getLikeSUM() {
        return likeSUM;
    }

    public void setLikeSUM(Long likeSUM) {
        this.likeSUM = likeSUM;
    }

    public Long getCommentSUM() {
        return commentSUM;
    }

    public void setCommentSUM(Long commentSUM) {
        this.commentSUM = commentSUM;
    }

    public Long getViewSUM() {
        return viewSUM;
    }

    public void setViewSUM(Long viewSUM) {
        this.viewSUM = viewSUM;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    public Long getPopularSUM() {
        return popularSUM;
    }

    public void setPopularSUM(Long popularSUM) {
        this.popularSUM = popularSUM;
    }

    @Override
    public String toString() {
        return "BlogStatisticsVO{" +
                "userId=" + userId +
                ", count=" + count +
                ", likeSUM=" + likeSUM +
                ", commentSUM=" + commentSUM +
                ", viewSUM=" + viewSUM +
                ", popularSUM=" + popularSUM +
                ", sourceFrom='" + sourceFrom + '\'' +
                '}';
    }
}
