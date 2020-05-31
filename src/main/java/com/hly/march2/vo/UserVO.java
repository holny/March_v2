package com.hly.march2.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserVO {
    private Long userId;

    private String userName;

    private String userProfilePic;

    private Integer userCredit;

    private String userNickname;

    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date userLastLoginTime;

    private Integer accountStatus;

    private String userSex;

    private String userMotto;

    private String userBg;

    private String githubLink;

    private String weiboLink;

    private String qqLink;

    private String wechatLink;

    private String emailLink;

    private String csdnLink;

    private String otherLink;

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

    @Override
    public String toString() {
        return "UserVO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userProfilePic='" + userProfilePic + '\'' +
                ", userCredit=" + userCredit +
                ", userNickname='" + userNickname + '\'' +
                ", userLastLoginTime=" + userLastLoginTime +
                ", accountStatus='" + accountStatus + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userMotto='" + userMotto + '\'' +
                ", userBg='" + userBg + '\'' +
                ", githubLink='" + githubLink + '\'' +
                ", weiboLink='" + weiboLink + '\'' +
                ", qqLink='" + qqLink + '\'' +
                ", wechatLink='" + wechatLink + '\'' +
                ", emailLink='" + emailLink + '\'' +
                ", csdnLink='" + csdnLink + '\'' +
                ", otherLink='" + otherLink + '\'' +
                '}';
    }
}
