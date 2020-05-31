package com.hly.march2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hly.march2.controller.AdminController;
import com.hly.march2.controller.LoginAndRegisterController;
import com.hly.march2.controller.UserController;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    @Null(groups = {LoginAndRegisterController.Insert.class},message = "不能指定Id")
    private Long userId;

    @Null(message = "不能指定Ip")
    private String userIp;

    @Size(max=10, message="用户名长度最大10")
    @Pattern(groups = {AdminController.Insert.class,LoginAndRegisterController.Insert.class, UserController.Update.class},regexp = "(^\\s?)|(^([a-zA-Z0-9_\\.-]+){5,10}$)|(^([\\u4e00-\\u9fa5\\.]+){2,10}$)"
            ,message = "用户名必须是字母或者汉字组成(最大10位)")
    private String userName;

    @Size(max=15, message="密码长度最大15")
    @Pattern(regexp = "(^\\s?)|(^[a-zA-Z0-9_-]{0,15}$)"
            ,message = "密码不符合规范(最大15位)")
    private String userPassword;

    @Email(groups = {AdminController.Insert.class,LoginAndRegisterController.Insert.class,UserController.Update.class},message = "Email格式不符合")
    private String userEmail;

    @Null(groups = {AdminController.Insert.class,LoginAndRegisterController.Insert.class,UserController.Update.class},message = "无Profile image")
    private String userProfilePic;

    @Null(groups = {AdminController.Insert.class,LoginAndRegisterController.Insert.class,UserController.Update.class},message = "无Credit")
    private Integer userCredit;

    @Null(groups = {LoginAndRegisterController.Login.class,LoginAndRegisterController.Insert.class,UserController.Update.class},message = "无权限")
    private Integer userRights;

    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date userRegisterTime;

    @JsonFormat(pattern="yyyy年MM月dd日",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日")
    private Date userBirthday;

    private Integer userAge;

    @Pattern(groups = {AdminController.Insert.class,LoginAndRegisterController.Insert.class,UserController.Update.class},regexp = "(^\\s?)|(^1[3456789]\\d{9}$)"
            ,message = "手机号码不符合规范(最大10位)")
    private String userTelephoneNumber;

    @Size(max=10, message="用户别名长度最大10")
    @Pattern(groups = {AdminController.Insert.class,LoginAndRegisterController.Insert.class,UserController.Update.class},regexp = "(^\\s?)|(^([a-zA-Z0-9_\\.-]+){5,10}$)|(^([\\u4e00-\\u9fa5\\.]+){2,10}$)"
            ,message = "用户名必须是字母或者汉字组成(最大10位)")
    private String userNickname;

    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date userLastLoginTime;

    @Null(groups = {LoginAndRegisterController.Login.class,LoginAndRegisterController.Insert.class,UserController.Update.class},message = "无权限1")
    private Integer accountStatus;

    @Null(message = "无权限1")
    private String salt;

    @Null(message = "无权限2")
    private String userSession;

    @Null(message = "无权限3")
    private String userConfig;

    @Size(max=3, message="性别长度最大10")
    private String userSex;

    @Size(max=150, message="Motto长度最大10")
    private String userMotto;

    private String userBg;

    private String githubLink;

    private String weiboLink;

    private String qqLink;

    private String wechatLink;

    private String emailLink;

    private String csdnLink;

    private String otherLink;

    private String remember;

    @Size(max=5, message="验证长度最大5")
    @Pattern(regexp = "(^\\s?)|(^[a-zA-Z0-9_-]{0,5}$)"
            ,message = "验证码不符合规范")
    private String captchaCode;

    @Size(max=15, message="新密码长度最大15")
    @Pattern(regexp = "(^\\s?)|(^[a-zA-Z0-9_-]{0,15}$)"
            ,message = "新密码不符合规范(最大15位)")
    private String newPassword;

    @Size(max=15, message="账号长度最大15")
    @Pattern(regexp = "(^\\s?)|(^[@\\.a-zA-Z0-9_-]{0,15}$)"
            ,message = "账号不符合规范(最大15位)")
    private String account;

    @Size(max=5, message="邀请码长度不对")
    @Pattern(regexp = "(^\\s?)|(^[a-zA-Z0-9_-]{0,5}$)"
            ,message = "邀请码不符合规范")
    private String invitationCode;

    private static final long serialVersionUID = 1L;

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
        this.userIp = userIp == null ? null : userIp.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserProfilePic() {
        return userProfilePic;
    }

    public void setUserProfilePic(String userProfilePic) {
        this.userProfilePic = userProfilePic == null ? null : userProfilePic.trim();
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
        this.userTelephoneNumber = userTelephoneNumber == null ? null : userTelephoneNumber.trim();
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getUserSession() {
        return userSession;
    }

    public void setUserSession(String userSession) {
        this.userSession = userSession == null ? null : userSession.trim();
    }

    public String getUserConfig() {
        return userConfig;
    }

    public void setUserConfig(String userConfig) {
        this.userConfig = userConfig == null ? null : userConfig.trim();
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public String getUserMotto() {
        return userMotto;
    }

    public void setUserMotto(String userMotto) {
        this.userMotto = userMotto == null ? null : userMotto.trim();
    }

    public String getUserBg() {
        return userBg;
    }

    public void setUserBg(String userBg) {
        this.userBg = userBg == null ? null : userBg.trim();
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink == null ? null : githubLink.trim();
    }

    public String getWeiboLink() {
        return weiboLink;
    }

    public void setWeiboLink(String weiboLink) {
        this.weiboLink = weiboLink == null ? null : weiboLink.trim();
    }

    public String getQqLink() {
        return qqLink;
    }

    public void setQqLink(String qqLink) {
        this.qqLink = qqLink == null ? null : qqLink.trim();
    }

    public String getWechatLink() {
        return wechatLink;
    }

    public void setWechatLink(String wechatLink) {
        this.wechatLink = wechatLink == null ? null : wechatLink.trim();
    }

    public String getEmailLink() {
        return emailLink;
    }

    public void setEmailLink(String emailLink) {
        this.emailLink = emailLink == null ? null : emailLink.trim();
    }

    public String getCsdnLink() {
        return csdnLink;
    }

    public void setCsdnLink(String csdnLink) {
        this.csdnLink = csdnLink == null ? null : csdnLink.trim();
    }

    public String getOtherLink() {
        return otherLink;
    }

    public void setOtherLink(String otherLink) {
        this.otherLink = otherLink == null ? null : otherLink.trim();
    }

    public String getRemember() {
        return remember;
    }

    public void setRemember(String remember) {
        this.remember = remember;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userIp='" + userIp + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userProfilePic='" + userProfilePic + '\'' +
                ", userCredit=" + userCredit +
                ", userRights=" + userRights +
                ", userRegisterTime=" + userRegisterTime +
                ", userBirthday=" + userBirthday +
                ", userAge=" + userAge +
                ", userTelephoneNumber='" + userTelephoneNumber + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userLastLoginTime=" + userLastLoginTime +
                ", accountStatus=" + accountStatus +
                ", salt='" + salt + '\'' +
                ", userSession='" + userSession + '\'' +
                ", userConfig='" + userConfig + '\'' +
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
                ", remember='" + remember + '\'' +
                ", captchaCode='" + captchaCode + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", account='" + account + '\'' +
                ", invitationCode='" + invitationCode + '\'' +
                '}';
    }
}