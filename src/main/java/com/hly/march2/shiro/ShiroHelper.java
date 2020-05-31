package com.hly.march2.shiro;

import com.hly.march2.entity.User;
import com.hly.march2.shiro.realm.UserEmailRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

public class ShiroHelper {
    private static final Logger log = LoggerFactory.getLogger(ShiroHelper.class);
    /**
     *
     */
    @Autowired
    private  EhCacheManager cacheManager;

    @Autowired
    private UserEmailRealm userEmailRealm;

    //private static Logger log = LoggerFactory.getLogger(ShiroAuthorizationHelper.class);


    /**
     * 清除用户的授权信息
     * @param userName
     */
    public  void clearAuthorizationInfo(String userName){
        log.debug("user name:{}",userName);
        Cache<Object, Object> cache = cacheManager.getCache("authorizationCache");
        //cache.remove(username);
        Set<Object> keys=cache.keys();
        Iterator<Object> it=keys.iterator();
        log.debug("keys:{}",keys);
        while(it.hasNext()){
            Object key=it.next();
            log.debug("keys.class:{}",key.getClass());
            if(userName.equals(key.toString())){
                cache.remove(key);    // key是登录的账号,不是realm实现类中认证方法中传入的user对象。
                log.debug("user name:{} clear done",userName);
            }
        }
    }

    public  void clearAuthenticationInfo(String userName){
//        User user = (User) principal;
        log.debug("user name:{}",userName);
        Cache<Object, Object> cache = cacheManager.getCache("authenticationCache");
        //cache.remove(username);
        Set<Object> keys=cache.keys();
        Iterator<Object> it=keys.iterator();
        log.debug("keys:{}",keys);
        while(it.hasNext()){
            Object key=it.next();
            if(userName.equals(key.toString())){
                cache.remove(key);      // key是登录的账号,不是realm实现类中认证方法中传入的user对象。
                log.debug("user name:{} clear done",userName);
            }
        }
    }

    /**
     * 清除当前用户的授权信息
     */
    public  void clearAuthorizationInfo(){
        if(SecurityUtils.getSubject().isAuthenticated()||SecurityUtils.getSubject().isRemembered()){
            clearAuthorizationInfo(((User)SecurityUtils.getSubject().getPrincipal()).getUserEmail());
        }
    }

    /**
     * 清除当前用户的认证信息
     */
    public  void clearAuthenticationInfo(){
        Subject subject = SecurityUtils.getSubject();
        log.info("clearAuthenticationInfo");
        if(subject.isAuthenticated()||subject.isRemembered()){
            User user = (User)subject.getPrincipal();
            log.info("clearAuthenticationInfo  userId:{}",user.getUserId());
            clearAuthenticationInfo(user.getUserEmail());
        }
    }

    /**清除session(认证信息)
     * @param JSESSIONID
     */
    public  void clearAuthenticationInfo(Serializable JSESSIONID){
        //shiro-activeSessionCache 为shiro自义cache名，该名在org.apache.shiro.session.mgt.eis.CachingSessionDAO抽象类中定义
        //如果要改变此名，可以将名称注入到sessionDao中，例如注入到CachingSessionDAO的子类EnterpriseCacheSessionDAO类中
        Cache<Object, Object> cache = cacheManager.getCache("shiro-activeSessionCache");
        cache.remove(JSESSIONID);
    }

    public EhCacheManager getEhCacheManager() {
        return cacheManager;
    }

    public void setEhCacheManager(EhCacheManager ehCacheManager) {
        this.cacheManager = ehCacheManager;
    }




}
