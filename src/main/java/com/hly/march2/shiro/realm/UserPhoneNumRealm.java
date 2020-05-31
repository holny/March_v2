package com.hly.march2.shiro.realm;

import com.hly.march2.controller.UserController;
import com.hly.march2.entity.User;
import com.hly.march2.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserPhoneNumRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(UserPhoneNumRealm.class);
    @Autowired
    private IUserService userService;
    /**
     * 权限注解:
     * Sh   iro支持三种方式的授权：
     * 1. 编程式：subject.hasRole();subject.checkPermission();
     * 2. 注解式：如下，没有权限就抛出异常。Service进行了代理模式实现事务，所以不要用在Service上.
     * @RequiresUser  表示经过身份认证或记住我登录的都可以。
     * @RequiresAuthentication  表示当前subject经过了Login认证，即subject.isAuthenticated()=true。不包括记住我
     * @RequiresGuest  表示当前subject没有经过认证或者记住我登陆
     * @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
     * @RequiresPermissions(value = {"user:a","user:b"},logical = Logical.OR)
     *
     * 3. JSP/GSP标签：在JSP/GSP前提页面通过相应标签完成。首先要导入<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     *
     * 3. JSP/GSP标签：在JSP/GSP前提页面通过相应标签完成。
     * @param principalCollection
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取从doGetAuthenticationInfo()传递过来的user
        User user = (User) principalCollection.getPrimaryPrincipal();
        log.info("account:{}",user.toString());
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<String>();
        if(UserController.AccountStatusEnum.DELETED_STATUS.getCode().equals(user.getAccountStatus())){
            roles.add(UserController.AccountStatusEnum.DELETED_STATUS.getDesc());
        }else if(UserController.AccountStatusEnum.FORBIDDEN_STATUS.getCode().equals(user.getAccountStatus())){
            roles.add(UserController.AccountStatusEnum.FORBIDDEN_STATUS.getDesc());
        }else{
            roles.add(UserController.RolesEnum.USER_ROLE.getDesc());
            permissions.add(UserController.PermissionsEnum.BLOG_ALL.getDesc());
            if(UserController.AccountStatusEnum.INCOMPLETE_STATUS.getCode().equals(user.getAccountStatus())){
                roles.add(UserController.AccountStatusEnum.INCOMPLETE_STATUS.getDesc());       // 新注册用户，未经认证
            }else {
                roles.add(UserController.RolesEnum.USER_ROLE.getDesc());
                if(UserController.RolesEnum.SUPER_ADMIN_ROLE.getCode().equals(user.getUserRights())){
                    roles.add(UserController.RolesEnum.ADMIN_ROLE.getDesc());
                    roles.add(UserController.RolesEnum.SUPER_ADMIN_ROLE.getDesc());
                    permissions.add(UserController.PermissionsEnum.USER_ALL.getDesc());
                    log.info("account:{} as super Admin",user.toString());
                }else if (UserController.RolesEnum.ADMIN_ROLE.getCode().equals(user.getUserRights())){
                    roles.add(UserController.RolesEnum.ADMIN_ROLE.getDesc());
                    permissions.add(UserController.PermissionsEnum.USER_ALL.getDesc());
                    log.info("account:{} as Admin",user.toString());
                }
            }
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roles);
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userAccount = (String) authenticationToken.getPrincipal();
        log.info("account:{}",userAccount);
        List<User> users = userService.findUserByPhoneNum(userAccount);
        if(users.size()<=0) throw new UnknownAccountException();   // 找不到此账户所属用户
        if(users.size()>=2) log.info("account:{} . this account amount from DB greater than 2",userAccount);
        User user =users.get(0);    // 无论查找到的用户多少(>=1)，只取第一个。
        log.info("from DB UserId:{}",user.getUserId());
        if(!"normal".equals(user.getAccountStatus())) throw new LockedAccountException();       // 此账户状态有异常
        // 交给AuthenticationRealm使用CredentialsMatcher进行密码匹配。
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUserName(), // 用户名,经试验，这里的账户对验证不起作用，只会往下传递给doGetAuthorizationInfo()方法，所以我这里直接传User对象就不用再查数据库了
                user.getUserPassword(), // 密码，这个密码是数据库取得的密码。前台用户传过来的密码这里没有，只有account。
                ByteSource.Util.bytes(user.getSalt()),  // 加密后的盐值
                getName()           // realm name
        );

        return authenticationInfo;
    }
}
