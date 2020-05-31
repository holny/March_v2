package com.hly.march2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

public class UserSearchDTO implements Serializable {
    @Range(min=0,max = Long.MAX_VALUE,message = "User Id超出范围")
    private Long userId;

    @Null(message = "无Ip")
    private String userIp;

    @Size(max=10, message="用户名长度最大10")
    @Pattern(regexp = "(^\\s?)|(^([a-zA-Z0-9_\\.-]+){5,10}$)|(^([\\u4e00-\\u9fa5\\.]+){2,10}$)"
            ,message = "用户名必须是字母或者汉字组成(最大10位)")
    private String userName;

    @Size(max=20, message="Email长度最大20")
    private String userEmail;


    private String userProfilePic;


    private Integer userCredit;

    private Integer userRights;

    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date userRegisterTime;


    @JsonFormat(pattern="yyyy年MM月dd日",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日")
    private Date userBirthday;

    @Max(value = 150,message = "年龄不在正常范围")
    @Min(value = 0,message = "年龄不在正常范围")
    private Integer userAge;

    @Size(max=15, message="手机号码长度最大15")
    private String userTelephoneNumber;

    @Size(max=10, message="用户别名长度最大10")
    @Pattern(regexp = "(^\\s?)|(^([a-zA-Z0-9_\\.-]+){5,10}$)|(^([\\u4e00-\\u9fa5\\.]+){2,10}$)"
            ,message = "别名必须是字母或者汉字组成(最大10位)")
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

    private String newPassword;

    private Integer pn;

    private String listSort;

    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date createTimeStart;

    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date createTimeEnd;

    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date lastLoginTimeStart;

    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date lastLoginTimeEnd;

    private String sourceFrom;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
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

    public Integer getUserRights() {
        return userRights;
    }

    public void setUserRights(Integer userRights) {
        this.userRights = userRights;
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

    public String getUserTelephoneNumber() {
        return userTelephoneNumber;
    }

    public void setUserTelephoneNumber(String userTelephoneNumber) {
        this.userTelephoneNumber = userTelephoneNumber;
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Integer getPn() {
        return pn;
    }

    public void setPn(Integer pn) {
        this.pn = pn;
    }

    public String getListSort() {
        return listSort;
    }

    public void setListSort(String listSort) {
        this.listSort = listSort;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Date getLastLoginTimeStart() {
        return lastLoginTimeStart;
    }

    public void setLastLoginTimeStart(Date lastLoginTimeStart) {
        this.lastLoginTimeStart = lastLoginTimeStart;
    }

    public Date getLastLoginTimeEnd() {
        return lastLoginTimeEnd;
    }

    public void setLastLoginTimeEnd(Date lastLoginTimeEnd) {
        this.lastLoginTimeEnd = lastLoginTimeEnd;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    @Override
    public String toString() {
        return "UserSearchDTO{" +
                "userId=" + userId +
                ", userIp='" + userIp + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userProfilePic='" + userProfilePic + '\'' +
                ", userCredit=" + userCredit +
                ", userRights='" + userRights + '\'' +
                ", userRegisterTime=" + userRegisterTime +
                ", userBirthday=" + userBirthday +
                ", userAge=" + userAge +
                ", userTelephoneNumber='" + userTelephoneNumber + '\'' +
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
                ", newPassword='" + newPassword + '\'' +
                ", pn=" + pn +
                ", listSort='" + listSort + '\'' +
                ", createTimeStart=" + createTimeStart +
                ", createTimeEnd=" + createTimeEnd +
                ", lastLoginTimeStart=" + lastLoginTimeStart +
                ", lastLoginTimeEnd=" + lastLoginTimeEnd +
                ", sourceFrom='" + sourceFrom + '\'' +
                '}';
    }
}
