package com.hly.march2.config;

import com.hly.march2.interceptor.MyInterceptor1;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc   不要接管SpringMVC
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    /**
     * 此方法用来专门注册一个Interceptor，如HandlerInterceptorAdapter
     * 当spring boot版本升级为2.x时，访问静态资源就会被HandlerInterceptor拦截,网上有很多处理办法都是如下写法
     * "/js/**","/css/**","/images/**"
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**")
                .excludePathPatterns("/editormd/**","/jquery/**","/marked/**","/res/**","/toastr/**","/xss/**");
    }

    /**
     * 以前要访问一个页面需要先创建个Controller控制类，再写方法跳转到页面
     * 在这里配置后就不需要那么麻烦了，直接访问http://localhost:8080/toLogin就跳转到login.jsp页面了
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
//        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/home").setViewName("home");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将url为 /static/** 的请求映射到 /static/ 路径下进行查找
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
