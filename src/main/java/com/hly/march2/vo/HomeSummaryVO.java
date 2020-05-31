package com.hly.march2.vo;

import com.hly.march2.entity.BlogType;

import java.util.List;

public class HomeSummaryVO {
    private List<BlogUserVo> blogList;

    //
    private List<BlogUserVo> latestBlogList;

    //
    private List<BlogUserVo> mostHotBlogList;

    private List<SeriesVO> blogSeries;

    private List<BlogType> blogTypes;

    private List<UserVO> topUserList;

    private List<UserVO> latestLoginUserList;

    private List<UserBriefStatisticsVO> topProductUserList;

    private String githubLink;

    private String weiboLink;

    private String qqLink;

    private String wechatLink;

    private String emailLink;

    private String csdnLink;

    private String otherLink;

    private Long blogNum;

    private Long SeriesNum;

    private Long viewNum;

    private Long likeNum;

    private Long commentNum;

    private Long pictureNum;

    private Long userNum;

    private Long livingNum;

    private String sourceFrom;

    private String guestIp;

    private String notice;

    public List<BlogUserVo> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<BlogUserVo> blogList) {
        this.blogList = blogList;
    }

    public List<BlogUserVo> getLatestBlogList() {
        return latestBlogList;
    }

    public void setLatestBlogList(List<BlogUserVo> latestBlogList) {
        this.latestBlogList = latestBlogList;
    }

    public List<BlogUserVo> getMostHotBlogList() {
        return mostHotBlogList;
    }

    public void setMostHotBlogList(List<BlogUserVo> mostHotBlogList) {
        this.mostHotBlogList = mostHotBlogList;
    }

    public List<SeriesVO> getBlogSeries() {
        return blogSeries;
    }

    public void setBlogSeries(List<SeriesVO> blogSeries) {
        this.blogSeries = blogSeries;
    }

    public List<BlogType> getBlogTypes() {
        return blogTypes;
    }

    public void setBlogTypes(List<BlogType> blogTypes) {
        this.blogTypes = blogTypes;
    }


    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    public String getWeiboLink() {
        return weiboLink;
    }

    public void setWeiboLink(String weiboLink) {
        this.weiboLink = weiboLink;
    }

    public String getQqLink() {
        return qqLink;
    }

    public void setQqLink(String qqLink) {
        this.qqLink = qqLink;
    }

    public String getWechatLink() {
        return wechatLink;
    }

    public void setWechatLink(String wechatLink) {
        this.wechatLink = wechatLink;
    }

    public String getEmailLink() {
        return emailLink;
    }

    public void setEmailLink(String emailLink) {
        this.emailLink = emailLink;
    }

    public String getCsdnLink() {
        return csdnLink;
    }

    public void setCsdnLink(String csdnLink) {
        this.csdnLink = csdnLink;
    }

    public String getOtherLink() {
        return otherLink;
    }

    public void setOtherLink(String otherLink) {
        this.otherLink = otherLink;
    }

    public Long getBlogNum() {
        return blogNum;
    }

    public void setBlogNum(Long blogNum) {
        this.blogNum = blogNum;
    }

    public Long getSeriesNum() {
        return SeriesNum;
    }

    public void setSeriesNum(Long seriesNum) {
        SeriesNum = seriesNum;
    }

    public Long getViewNum() {
        return viewNum;
    }

    public void setViewNum(Long viewNum) {
        this.viewNum = viewNum;
    }

    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    public Long getPictureNum() {
        return pictureNum;
    }

    public void setPictureNum(Long pictureNum) {
        this.pictureNum = pictureNum;
    }

    public Long getUserNum() {
        return userNum;
    }

    public void setUserNum(Long userNum) {
        this.userNum = userNum;
    }

    public Long getLivingNum() {
        return livingNum;
    }

    public void setLivingNum(Long livingNum) {
        this.livingNum = livingNum;
    }

    public List<UserVO> getTopUserList() {
        return topUserList;
    }

    public void setTopUserList(List<UserVO> topUserList) {
        this.topUserList = topUserList;
    }

    public Long getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Long commentNum) {
        this.commentNum = commentNum;
    }

    public List<UserVO> getLatestLoginUserList() {
        return latestLoginUserList;
    }

    public void setLatestLoginUserList(List<UserVO> latestLoginUserList) {
        this.latestLoginUserList = latestLoginUserList;
    }

    public List<UserBriefStatisticsVO> getTopProductUserList() {
        return topProductUserList;
    }

    public void setTopProductUserList(List<UserBriefStatisticsVO> topProductUserList) {
        this.topProductUserList = topProductUserList;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    public String getGuestIp() {
        return guestIp;
    }

    public void setGuestIp(String guestIp) {
        this.guestIp = guestIp;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    @Override
    public String toString() {
        return "HomeSummaryVO{" +
                "blogList=" + blogList +
                ", latestBlogList=" + latestBlogList +
                ", mostHotBlogList=" + mostHotBlogList +
                ", blogSeries=" + blogSeries +
                ", blogTypes=" + blogTypes +
                ", topUserList=" + topUserList +
                ", latestLoginUserList=" + latestLoginUserList +
                ", topProductUserList=" + topProductUserList +
                ", githubLink='" + githubLink + '\'' +
                ", weiboLink='" + weiboLink + '\'' +
                ", qqLink='" + qqLink + '\'' +
                ", wechatLink='" + wechatLink + '\'' +
                ", emailLink='" + emailLink + '\'' +
                ", csdnLink='" + csdnLink + '\'' +
                ", otherLink='" + otherLink + '\'' +
                ", blogNum=" + blogNum +
                ", SeriesNum=" + SeriesNum +
                ", viewNum=" + viewNum +
                ", likeNum=" + likeNum +
                ", commentNum=" + commentNum +
                ", pictureNum=" + pictureNum +
                ", userNum=" + userNum +
                ", livingNum=" + livingNum +
                ", sourceFrom='" + sourceFrom + '\'' +
                ", guestIp='" + guestIp + '\'' +
                ", notice='" + notice + '\'' +
                '}';
    }
}
