package com.hly.march2.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.hly.march2.shiro.MyModularRealmAuthenticator;
import com.hly.march2.shiro.MySessionDAO;
import com.hly.march2.shiro.ShiroHelper;
import com.hly.march2.shiro.filter.MyFormAuthenticationFilter;
import com.hly.march2.shiro.realm.UserEmailRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 *
 */
@Configuration
public class ShiroConfig {
    /**
     * 1.1. shiroFilter    shiro的web过滤器
     *
     * @param securityManager 安全管理器
     * @return shiroFilter
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager")SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // Shiro的安全管理器，所有关于安全的操作都会经过SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 系统认证提交地址，如果用户退出即session丢失就会访问这个页面
        shiroFilterFactoryBean.setLoginUrl("/login");
//        shiroFilterFactoryBean.setSuccessUrl("/home");
        // 权限验证失败跳转的页面，需要配合Spring的ExceptionHandler异常处理机制使用
        shiroFilterFactoryBean.setUnauthorizedUrl("/login");

        /**由于前台登录是ajax请求，表单处理成功后返回302重定向要求(SuccessUrl),ajax不能重定向,所以还是用login controller手动处理登录请求。
        因此setSuccessUrl对于login controller是无用的，需要手动设置。
         **/
//        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();//获取filters
//        filters.put("authc", formAuthenticationFilter);
//        shiroFilterFactoryBean.setFilters(filters);

        //        filterMap.put("logout", logoutFilter);

        Map<String, String> filterChainMap = new LinkedHashMap<>();
        filterChainMap.put("/", "anon");
        filterChainMap.put("/logout", "logout");
        filterChainMap.put("/editormd/**", "anon");
        filterChainMap.put("/jquery/**", "anon");
        filterChainMap.put("/marked/**", "anon");
        filterChainMap.put("/res/**", "anon");
        filterChainMap.put("/toastr/**", "anon");
        filterChainMap.put("/xss/**", "anon");
        filterChainMap.put("/userlogin", "anon");  // 用formAuthenticationFilter时 userlogin 为authc
        filterChainMap.put("/blog/page ", "anon");
        filterChainMap.put("/series/** ", "anon");
        filterChainMap.put("/summary", "anon");
        filterChainMap.put("/home", "anon");
        filterChainMap.put("/druid/**", "anon");
        filterChainMap.put("/captcha", "anon");
        filterChainMap.put("/register", "anon");
        filterChainMap.put("/registercheck", "anon");
        filterChainMap.put("/blog/preview", "anon");
        filterChainMap.put("/user/profile", "anon");
        filterChainMap.put("/user/**", "anon");
        filterChainMap.put("/file/showPic/**", "anon");
        filterChainMap.put("/file/showBg/**", "anon");
        filterChainMap.put("/profile/**", "anon");
        filterChainMap.put("/image/**", "anon");
        filterChainMap.put("/like/**", "anon");
        filterChainMap.put("/user", "anon");
        filterChainMap.put("/blog/serieslist/**", "anon");
        filterChainMap.put("/blog/typelist/**", "anon");
        filterChainMap.put("/my", "user");
        filterChainMap.put("/myblog", "user");
        filterChainMap.put("/myinfo", "user");
        filterChainMap.put("/file/deletePic", "user");
        filterChainMap.put("/blog/edit/**", "roles[user]");
        filterChainMap.put("/blog", "roles[user]");
        filterChainMap.put("/file/upload/**", "user");
        filterChainMap.put("/admin/**", "user");
        filterChainMap.put("/file/downloadPic/**", "user");
        filterChainMap.put("/blog/**", "anon");
        filterChainMap.put("/**", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 1.2. 自定义logout filter
     *
     * 配上会出问题:重定向到logout的次数过多。
     * 原因：任何Servlet或Filter bean都将自动注册到servlet容器中。要禁用特定Filter或Servlet bean的注册，请为其创建注册bean并将其标记为禁用。我写的logout过滤器被spring boot自动注册到servlet容器中了，本来是要由shiro去管理这个filter的，现在却变成spring boot管理了。
     *
     *
     * @return
     */
//    @Bean(name = "logoutFilter")
//    public LogoutFilter logoutFilter(){
//        LogoutFilter logoutFilter = new LogoutFilter();
//        logoutFilter.setRedirectUrl("/aaaaa");
//        return logoutFilter;
//    }

    /**
     * 1.3. 基于Form表单的身份验证过滤器
     *  由于前台登录是ajax请求，表单处理成功后返回302重定向要求(SuccessUrl),ajax不能重定向,所以还是用login controller手动处理登录请求。
     *  因此setSuccessUrl对于login controller是无用的，需要手动设置。
     *
     * @return
     */
//    @Bean(name="formAuthenticationFilter")
//    public MyFormAuthenticationFilter formAuthenticationFilter(){
//        // 自定义 FormAuthenticationFilter
//        MyFormAuthenticationFilter formAuthenticationFilter = new MyFormAuthenticationFilter();
//        // 登录表单中参数
//        formAuthenticationFilter.setUsernameParam("account");
//        formAuthenticationFilter.setPasswordParam("userPassword");
//        formAuthenticationFilter.setRememberMeParam("remember");
//        formAuthenticationFilter.setLoginUrl("/userlogin");
////        formAuthenticationFilter.setSuccessUrl("/home");
//        formAuthenticationFilter.setFailureKeyAttribute("exceptionMsg");
//        return formAuthenticationFilter;
//    }


    /**
     * 2. SecurityManager  安全管理器
     *
     * @param emailRealm         自定义emailRealm
     * @param sessionManager    会话管理器
     * @param rememberMeManager 记住我管理器
     * @return securityManager
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("emailRealm") UserEmailRealm emailRealm,@Qualifier("ehCacheManager") EhCacheManager ehCacheManager,
                                           ModularRealmAuthenticator authenticator, ModularRealmAuthorizer authorizer,
                                           @Qualifier("sessionManager")SessionManager sessionManager, @Qualifier("rememberMeManager") RememberMeManager rememberMeManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(emailRealm);
        securityManager.setSessionManager(sessionManager);
        // 加载 CacheManager 缓存管理器
        securityManager.setCacheManager(ehCacheManager);
        // 加载 Authenticator 身份认证管理器
        securityManager.setAuthorizer(authorizer);
        // 加载 Authorizer 权限认证管理器
        securityManager.setAuthenticator(authenticator);
        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;
    }

    /**
     * 3. CacheManager   缓存管理器
     *
     * Shiro整合redis,缓存管理器
     *
     * @return cacheManager
     */
//    @Bean(name = "shiroRedisCacheManager")
//    public CacheManager shiroRedisCacheManager(RedisTemplate redisTemplate, RedisCacheManager redisCacheManager) {
//        ShiroRedisCacheManager shiroRedisCacheManager = new ShiroRedisCacheManager();
//        shiroRedisCacheManager.setRedisCacheManager(redisCacheManager);
//        shiroRedisCacheManager.setRedisTemplate(redisTemplate);
//        return shiroRedisCacheManager;
//    }

    /**
     *  3. CacheManager   缓存管理器
     *
     * 缓存管理器配置,需要加入ehcache的Jar包级配置文件
     * @return
     */
    @Bean(name = "ehCacheManager")
    public EhCacheManager  ehCacheManager(){
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache/ehcache.xml");
        return cacheManager;
    }

    /**
     * 4. Authenticator    身份认证器配置
     *
     * 系统自带的Realm管理，主要针对多realm
     * Authenticator 身份认证器配置，对应Realm的doGetAuthenticationInfo()
     */
    @Bean("authenticator")
    public ModularRealmAuthenticator authenticator() {
        //自己重写的ModularRealmAuthenticator
        ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
        /**
         * 认证策略改为FirstSuccessfulStrategy只要有一个认证成功即可，只返回第一个认证成功的认证信息，其它的忽略。
         * 默认的是AtLeastOneSuccessfulStrategy只要有一个认证成功即可，但将返回所有Realm身份认证成功的认证信息。
         * 还有AllSuccessfulStrategy是所有Realm认证成功才算成功，且返回所有认证成功的认证信息。
         */
        modularRealmAuthenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        return modularRealmAuthenticator;
    }


    /**
     * 5. Authorizer      权限认证器配置
     *
     * Authorizer 权限认证器配置，注意这里配置后，并且有权限认证动作时，才会调用Realm的doGetAuthorizationInfo()
     * @return
     */
    @Bean("authorizer")
    public ModularRealmAuthorizer authorizer() {
        ModularRealmAuthorizer modularRealmAuthorizer = new ModularRealmAuthorizer();
        return modularRealmAuthorizer;
    }

    /**
     * 6. Realm    自定义realm
     *
     * @return emailRealm
     */
    @Bean(name = "emailRealm")
    public UserEmailRealm emailRealm(@Qualifier("credentialsMatcher") CredentialsMatcher credentialsMatcher) {
        UserEmailRealm emailRealm = new UserEmailRealm();
        // 是否启用缓存
        emailRealm.setCachingEnabled(true);
        emailRealm.setAuthenticationCachingEnabled(true);
        emailRealm.setAuthenticationCacheName("authenticationCache");
        emailRealm.setAuthorizationCachingEnabled(true);
        emailRealm.setAuthorizationCacheName("authorizationCache");
        // 指定加密算法和加密次数
        emailRealm.setCredentialsMatcher(credentialsMatcher);
        return emailRealm;
    }

    /**
     * 7. credentialsMatcher      自定义凭证匹配器
     *
     * 指定加密算法和加密次数
     * @return credentialsMatcher
     */
    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法;
        credentialsMatcher.setHashAlgorithmName("md5");
        //散列的次数
        credentialsMatcher.setHashIterations(5);
        //storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    /**
     * 8.1. SessionManager 会话管理器
     *
     * @param sessionDAO 会话持久化DAO
     * @param cookie     会话cookie模板
     * @return sessionManager
     */
    @Bean(name = "sessionManager")
    public SessionManager sessionManager(SessionDAO sessionDAO, @Qualifier(value = "sessionIdCookie") SimpleCookie cookie) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 解决前端URL上会有JESSIONID Bug
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        // 定义的是全局的session会话超时时间，此操作会覆盖web.xml文件中的超时时间配置 单位毫秒
        // 当remember me时间大于这个global session时间时，超过这个session时间还是在网页上的，只不过是remember me身份
        sessionManager.setGlobalSessionTimeout(1800000);
        // 删除所有无效的Session对象，此时的session被保存在了内存里面
        sessionManager.setDeleteInvalidSessions(true);
        // 会话验证是否启用
        // 需要让此session可以使用该定时调度器进行检测
        sessionManager.setSessionValidationSchedulerEnabled(true);

        //30分钟校验一次会话是否过时
        ExecutorServiceSessionValidationScheduler scheduler = new ExecutorServiceSessionValidationScheduler();
        scheduler.setSessionManager(sessionManager);
        scheduler.setInterval(1800000);
        // ** 会话验证调度器
        // 定义要使用的无效的Session定时调度器
        sessionManager.setSessionValidationScheduler(scheduler);
        // Session持久化DAO
        sessionManager.setSessionDAO(sessionDAO);
        // 是否启用sessionIdCookie，默认是启用的
        // 定义sessionIdCookie模版可以进行操作的启用
        sessionManager.setSessionIdCookieEnabled(true);
        // 会话Cookie
        // 所有的session一定要将id设置到Cookie之中，需要提供有Cookie的操作模版
        sessionManager.setSessionIdCookie(cookie);
        return sessionManager;
    }

    /**
     * 8.2. Session ID生成器
     * 会话Id生成器
     *
     * @return sessionIdGenerator
     */
    @Bean(name = "sessionIdGenerator")
    public SessionIdGenerator sessionIdGenerator() {
        SessionIdGenerator sessionIdGenerator = new JavaUuidSessionIdGenerator();
        return sessionIdGenerator;
    }

    /**
     * 8.3. Session DAO 继承EnterpriseCacheSessionDAO
     *
     * 会话持久化DAO,持久化到缓存
     *
     * @param sessionIdGenerator 会话Id生成器
     * @return sessionDAO
     */
    @Bean(name = "sessionDAO")
    public SessionDAO sessionDAO(SessionIdGenerator sessionIdGenerator) {
        MySessionDAO sessionDAO = new MySessionDAO();
        // 同时启用会话缓存,需要配置CacheManager
        sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        sessionDAO.setSessionIdGenerator(sessionIdGenerator);
        return sessionDAO;
    }

    /**
     * 8.4. Session cookie  模板
     *
     * 会话Cookie模板   ，这个是默认模板，除了rememberMeCookie以外的cookie配置
     * @return simpleCookie
     */
    @Bean(name = "sessionIdCookie")
    public SimpleCookie sessionIdCookie() {
        // 在Tomcat运行下默认使用的Cookie的名字为JSESSIONID
        SimpleCookie simpleCookie = new SimpleCookie("sid");
        // maxAge=-1表示浏览器关闭时失效此Cookie
        simpleCookie.setMaxAge(-1);
        // 如果设置为true，则客户端不会暴露给服务端脚本代码，有助于减少某些类型的跨站脚本攻击
        // 保证该系统不会受到跨域的脚本操作供给
        simpleCookie.setHttpOnly(true);
        return simpleCookie;
    }


    /**
     * 8.5. ValidationScheduler   会话验证调度器
     *
     * 会话验证调度器 配置session的定时验证检测程序类，以让无效的session释放
     * @return
     */
//    @Bean(name="sessionValidationScheduler")
//    public QuartzSessionValidationScheduler  quartzSessionValidationScheduler(@Qualifier("sessionManager") ValidatingSessionManager sessionManager){
//        QuartzSessionValidationScheduler scheduler = new QuartzSessionValidationScheduler();
//        //设置session的失效扫描间隔，单位为毫秒
//        scheduler.setSessionValidationInterval(180000);
//        scheduler.setSessionManager(sessionManager);
//        return scheduler;
//    }

    /**
     * 9.1.  rememberMe 管理器
     *
     *  因为浏览器关闭后再打开浏览器发送后端的就不是上次的session id了，所以会话结束。
     * 因此remember me 是依靠前端保存的cookie在勾选remember me后(后端token.setRememberMe(true))，把cookie保存在服务器。
     * 因此关闭浏览器后，用户还能使用，不过这时就是remember me权限(user/ isRemembered()=true)，跟Authenticated有区别(authc/isAuthenticated()=true)。
     * 显然Authenticated权限大于remember me权限，因为Authenticated是刚经过身份认证的(登录验证)
     * @param cookie rememberMeCookie模板
     * @return rememberMeManager
     */
    @Bean(name = "rememberMeManager")
    public RememberMeManager rememberMeManager(@Qualifier(value = "rememberMeCookie") SimpleCookie cookie) {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(cookie);
        rememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return rememberMeManager;
    }

    /**
     * 9.2. rememberMeCookie模板
     *
     * @return rememberMeCookie
     */
    @Bean(name = "rememberMeCookie")
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        // 单位秒。value=2592000= 30天
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }

    /**
     * 10. 相当于调用SecurityUtils.setSecurityManager(securityManager)
     *
     * @param securityManager
     * @return
     */
    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(@Qualifier("securityManager")SecurityManager securityManager) {
        MethodInvokingFactoryBean methodInvokingFactoryBean=new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(securityManager);
        return methodInvokingFactoryBean;
    }

    /**
     * 11. 相当于在web.xml加入shiroFilter
     *
     * @return
     */
    @Bean("delegatingFilterProxy")
    public FilterRegistrationBean delegatingFilterProxy() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

    /**
     * 12. shiro生命周期处理器
     *
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
        return lifecycleBeanPostProcessor;
    }

    /**
     * 13.1. 开启Shiro的注解
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     *
     * @return
     */
    @Bean(name = "advisorAutoProxyCreator")
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 13.2. 开启Shiro的注解
     *
     * @param securityManager 安全管理器
     * @return
     */
    @Bean(name="authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }


    /**
     * shiro方言  支持shiro标签
     * <dependency>
     * 	    <groupId>com.github.theborakompanioni</groupId>
     * 		<artifactId>thymeleaf-extras-shiro</artifactId>
     * </dependency>
     *
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public ShiroHelper shiroHelper(){
        return new ShiroHelper();
    }

}