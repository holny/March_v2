package com.hly.march2.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hly.march2.entity.BlogSeries;
import com.hly.march2.entity.BlogType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserBriefStatisticsVO implements Serializable {
    private Long userId;

    private String userName;

    private String userEmail;

    private String userProfilePic;

    private Integer userCredit;

    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date userRegisterTime;

    @JsonFormat(pattern="yyyy年MM月dd日",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日")
    private Date userBirthday;

    private Integer userAge;

    private String userNickname;

    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date userLastLoginTime;

    private Integer accountStatus;

    private String userSex;

    private String userMotto;

    private String userBg;

    private String userConfig;

    private String githubLink;

    private String weiboLink;

    private String qqLink;

    private String wechatLink;

    private String emailLink;

    private String csdnLink;

    private String otherLink;

    private List<BlogUserVo> blogList;

    private List<BlogUserVo> latestBlogList;

    private List<BlogUserVo> mostHotBlogList;

    private List<BlogSeries> blogSeries;

    private List<BlogType> blogTypes;

    private BlogStatisticsVO blogStatisticsVO;

    private Long blogNum;

    private Long likeNum;

    private Long commentNum;

    private Long viewsNum;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserProfilePic() {
        return userProfilePic;
    }

    public void setUserProfilePic(String userProfilePic) {
        this.userProfilePic = userProfilePic;
    }

    public Integer getUserCredit() {
        return userCredit;
    }

    public void setUserCredit(Integer userCredit) {
        this.userCredit = userCredit;
    }

    public Date getUserRegisterTime() {
        return userRegisterTime;
    }

    public void setUserRegisterTime(Date userRegisterTime) {
        this.userRegisterTime = userRegisterTime;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public Date getUserLastLoginTime() {
        return userLastLoginTime;
    }

    public void setUserLastLoginTime(Date userLastLoginTime) {
        this.userLastLoginTime = userLastLoginTime;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserMotto() {
        return userMotto;
    }

    public void setUserMotto(String userMotto) {
        this.userMotto = userMotto;
    }

    public String getUserBg() {
        return userBg;
    }

    public void setUserBg(String userBg) {
        this.userBg = userBg;
    }

    public String getUserConfig() {
        return userConfig;
    }

    public void setUserConfig(String userConfig) {
        this.userConfig = userConfig;
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

    public List<BlogSeries> getBlogSeries() {
        return blogSeries;
    }

    public void setBlogSeries(List<BlogSeries> blogSeries) {
        this.blogSeries = blogSeries;
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

    public Long getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Long commentNum) {
        this.commentNum = commentNum;
    }

    public Long getViewsNum() {
        return viewsNum;
    }

    public void setViewsNum(Long viewsNum) {
        this.viewsNum = viewsNum;
    }

    public BlogStatisticsVO getBlogStatisticsVO() {
        return blogStatisticsVO;
    }

    public void setBlogStatisticsVO(BlogStatisticsVO blogStatisticsVO) {
        this.blogStatisticsVO = blogStatisticsVO;
    }

    public List<BlogType> getBlogTypes() {
        return blogTypes;
    }

    public void setBlogTypes(List<BlogType> blogTypes) {
        this.blogTypes = blogTypes;
    }

    @Override
    public String toString() {
        return "UserBriefStatisticsVO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userProfilePic='" + userProfilePic + '\'' +
                ", userCredit=" + userCredit +
                ", userRegisterTime=" + userRegisterTime +
                ", userBirthday=" + userBirthday +
                ", userAge=" + userAge +
                ", userNickname='" + userNickname + '\'' +
                ", userLastLoginTime=" + userLastLoginTime +
                ", accountStatus='" + accountStatus + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userMotto='" + userMotto + '\'' +
                ", userBg='" + userBg + '\'' +
                ", userConfig='" + userConfig + '\'' +
                ", githubLink='" + githubLink + '\'' +
                ", weiboLink='" + weiboLink + '\'' +
                ", qqLink='" + qqLink + '\'' +
                ", wechatLink='" + wechatLink + '\'' +
                ", emailLink='" + emailLink + '\'' +
                ", csdnLink='" + csdnLink + '\'' +
                ", otherLink='" + otherLink + '\'' +
                ", blogList=" + blogList +
                ", latestBlogList=" + latestBlogList +
                ", mostHotBlogList=" + mostHotBlogList +
                ", blogSeries=" + blogSeries +
                ", blogTypes=" + blogTypes +
                ", blogStatisticsVO=" + blogStatisticsVO +
                ", blogNum=" + blogNum +
                ", likeNum=" + likeNum +
                ", commentNum=" + commentNum +
                ", viewsNum=" + viewsNum +
                '}';
    }
}
