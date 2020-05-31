package com.hly.march2.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录DTO
 */
public class UserAccountDTO implements Serializable {

    private Long userId;

    private String userIp;

    private String userName;

    private String userEmail;

    private String userProfilePhoto;

    private Integer userCredit;

    private Integer userRights;

    private Date userRegistrationTime;

    private Date userBirthday;

    private Byte userAge;

    private String userTelephoneNumber;

    private String userNickname;

    private Date userLastLoginTime;

    private Integer accountStatus;


    private String userSession;

    private String userAccount;

    private String rememberMe;

    private String userCaptchaCode;

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

    public String getUserProfilePhoto() {
        return userProfilePhoto;
    }

    public void setUserProfilePhoto(String userProfilePhoto) {
        this.userProfilePhoto = userProfilePhoto;
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

    public Date getUserRegistrationTime() {
        return userRegistrationTime;
    }

    public void setUserRegistrationTime(Date userRegistrationTime) {
        this.userRegistrationTime = userRegistrationTime;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public Byte getUserAge() {
        return userAge;
    }

    public void setUserAge(Byte userAge) {
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

    public String getUserSession() {
        return userSession;
    }

    public void setUserSession(String userSession) {
        this.userSession = userSession;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getUserCaptchaCode() {
        return userCaptchaCode;
    }

    public void setUserCaptchaCode(String userCaptchaCode) {
        this.userCaptchaCode = userCaptchaCode;
    }


    @Override
    public String toString() {
        return "UserAccountDTO{" +
                "userId=" + userId +
                ", userIp='" + userIp + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userProfilePhoto='" + userProfilePhoto + '\'' +
                ", userCredit=" + userCredit +
                ", userRights='" + userRights + '\'' +
                ", userRegistrationTime=" + userRegistrationTime +
                ", userBirthday=" + userBirthday +
                ", userAge=" + userAge +
                ", userTelephoneNumber='" + userTelephoneNumber + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userLastLoginTime=" + userLastLoginTime +
                ", accountStatus='" + accountStatus + '\'' +
                ", userSession='" + userSession + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", rememberMe='" + rememberMe + '\'' +
                ", userCaptchaCode='" + userCaptchaCode + '\'' +
                '}';
    }
}
