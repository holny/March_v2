# March_v2 博客
## 关于项目

   此项目的 V1版本是SSM版[March](https://github.com/holny/March_blog)博客，当前V2版本对V1版改为了Springboot，业务逻辑代码没有做较大修改，而且springboot的配置是参照目前2020-05最新的配置进行修改，所以对于初学者学SSHM和springboot工程方面有对比作用。

   本人也是3月份开始初学spring，因为本人兼顾学习作用，项目代码有些冗余，所以**本项目主要仅用作于学习并且可作为个人博客小站，但无法用作于生产**。   

  [在线预览](http://47.103.194.19/home) (注册邀请码: AAAA)

## 使用技术

* Springboot  
* 持久化框架 Mybatis 
* 数据库连接池 Redis
* 缓存 Redis
* 数据校验 JSR303 validation
* 认证授权安全框架 Shiro
* 模板渲染引擎 Thymeleaf
* 日志框架 Slf4j+log4j2
* 前端框架 [Titan](https://themewagon.com/themes/titan/) - bootstrap
* 前端Mark down插件 [editor.md](https://github.com/pandao/editor.md)
* 分页插件 [pagehelper](https://mvnrepository.com/artifact/com.github.pagehelper/pagehelper-spring-boot-starter)
* 验证码插件 [easy-captcha](https://mvnrepository.com/artifact/com.github.whvcse/easy-captcha)
* 前端时间选择插件 [bootstrap-datetimepicker](https://github.com/Eonasdan/bootstrap-datetimepicker)
* 前端图片文件上传插件 [bootstrap-fileinput](https://github.com/kartik-v/bootstrap-fileinput)

## 页面预览

![首页](https://github.com/holny/Resource/blob/master/March_v2/1.gif)

![过滤](https://github.com/holny/Resource/blob/master/March_v2/2.gif)

![注册登录](https://github.com/holny/Resource/blob/master/March_v2/3.gif)

![新建博客,博客浏览](https://github.com/holny/Resource/blob/master/March_v2/4.gif)

![个人信息修改](https://github.com/holny/Resource/blob/master/March_v2/5.gif)

![admin](https://github.com/holny/Resource/blob/master/March_v2/6.gif)

[在线预览](http://47.103.194.19/home) (注册邀请码: AAAA)

## 功能介绍

1. **博客功能**。博客分为了发布箱和草稿箱(两张表,blog和blog_draft)，博客通用7个状态(具体看BlogStatusEnum)，通过不同状态表现在存储在发布箱或者草稿箱。也通过不同状态，可以隐藏或展示博客。

   1.1.  发布箱。

   1.2.  草稿箱。

   1.3.  博客搜索过滤。

   1.4.  个人博客管理：修改、再次编辑、删除。

   1.5.  浏览量、点赞计数统计。

   1.6.  根据发布时间、浏览量首页推荐。

   1.7.  在线Mark down博客编辑、预览、发布、图片插入和浏览器本地缓存。

2. **安全管理**。依托shiro认证授权安全框架，实现认证(注册、登录功能)，授权(每个用户具有不同权限，粗分为admin和普通用户)。shiro也实现了自定义Session DAO(Mysql和缓存皆有)，Ehcache(authorizationCache和authenticationCache)。

   2.1.  认证：注册、登录。

   2.2.  授权。

3. **个人信息管理**：

   3.1.  个人信息修改。

   3.2.  个人头像、博客背景修改。

   3.3.  个人博客右侧推荐信息修改：个人社交站外链接修改、Motto修改。

4. **管理员**。用户如果具有管理员权限就具有如下功能：

   4.1.  统计全员博客。对全员博客(包括发布箱和草稿箱)具有修改基础信息和删除功能。

   4.2.  统计全员信息。对全员的具有修改信息和删除功能。

5. **全站统计**。

   5.1.  全站总运行时间统计，显示在footer。

   5.2.  全站浏览量PV统计，显示在footer。

   5.3.  可以对首页右侧sidebar修改站外链接和通知Motto。

## 如何使用

   我这是Idea工具项目，如果想打包部署到服务器，请参照网上已有教程。

   我的工程环境：JDK1.8、Mysql8.0.20，redis 6

   项目 pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.0.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.hly</groupId>
    <artifactId>march</artifactId>
    <version>1.0</version>
    <name>March_V2</name>
    <description>Hly blog</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>ch.qos.logback</groupId>
                    <artifactId>logback-classic</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <!-- 去掉springboot默认配置 -->
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <!--开启 cache 缓存-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>
      
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <!-- redis lettuce pool依赖commons-pool -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
        </dependency>
        <!--添加以下依赖在yml文件中编写字段有提示功能-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>compile</scope>
        </dependency>
        <!-- Shiro -->
        <!-- https://mvnrepository.com/artifact/org.apache.shiro/shiro-spring-boot-web-starter -->
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-spring-boot-web-starter</artifactId>
            <version>1.5.3</version>
        </dependency>
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-ehcache</artifactId>
            <version>1.5.3</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.github.theborakompanioni/thymeleaf-extras-shiro -->
        <dependency>
            <groupId>com.github.theborakompanioni</groupId>
            <artifactId>thymeleaf-extras-shiro</artifactId>
            <version>2.0.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.12</version>
            <scope>provided</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/com.alibaba/druid-spring-boot-starter -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.22</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.mybatis.spring.boot/mybatis-spring-boot-starter -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.2</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.mybatis.generator/mybatis-generator-core -->
        <dependency>
            <groupId>org.mybatis.generator</groupId>
            <artifactId>mybatis-generator-core</artifactId>
            <version>1.3.7</version>
        </dependency>
        <!-- Google guava工具包-->
        <!-- https://mvnrepository.com/artifact/com.google.guava/guava -->
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>27.0.1-jre</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.esotericsoftware/reflectasm -->
        <dependency>
            <groupId>com.esotericsoftware</groupId>
            <artifactId>reflectasm</artifactId>
            <version>1.11.3</version>
        </dependency>
        <!-- 验证码 Jar -->
        <!-- https://mvnrepository.com/artifact/com.github.whvcse/easy-captcha -->
        <dependency>
            <groupId>com.github.whvcse</groupId>
            <artifactId>easy-captcha</artifactId>
            <version>1.6.2</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/com.github.pagehelper/pagehelper-spring-boot-starter -->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>1.2.13</version>
            <!-- 去掉springboot默认配置 -->
            <exclusions>
                <exclusion>
                    <groupId>org.mybatis.spring.boot</groupId>
                    <artifactId>mybatis-spring-boot-starter</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.github.pagehelper/pagehelper-spring-boot-autoconfigure -->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-autoconfigure</artifactId>
            <version>1.2.13</version>
        </dependency>
        <!-- 引入log4j2依赖 -->
        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-log4j2 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j2</artifactId>
        </dependency>
        <!-- 若Log4j2使用了AsyncLogger, 就需要disruptor-->
        <!-- https://mvnrepository.com/artifact/com.lmax/disruptor -->
        <dependency>
            <groupId>com.lmax</groupId>
            <artifactId>disruptor</artifactId>
            <version>3.4.2</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.1</version>
                <configuration>
                    <skipTests>true</skipTests>
                </configuration>
            </plugin>
        </plugins>

    </build>
</project>
```



## 数据库

​    我是使用的Mysql，我的数据库schema和数据都导出为.sql文件在项目根目录，你可以直接新建一个表然后导入.sql，别忘了再修改application.yml中datasource.druid.url的链接中表名称为你新建的表名称。

   mapper是我用mybatsi generator逆向工程生成的，所以数据库具体架构可以从mapper(xxxMapper)和entity看出，生成代码可以参照SSM版[March](https://github.com/holny/March_blog)里[mbg.xml](https://github.com/holny/March_blog/blob/master/March/mbg.xml)和[mbg.java](https://github.com/holny/March_blog/blob/master/March/src/test/java/com/hly/march/test/MBGTest.java)。V2目前已经做好，如无特殊需要，无需修改。

## 关于开源

​    本站用作于个人学习使用，请不要用作于商用(当然也不适合)。

   _关于前端: 我是使用的bootsrap基础上修改的Titan前端组件，也是相当于bootsrap那种搭积木形式一块块做的，这个[Titan框架](https://themewagon.com/themes/titan/)挺不错的，缺点只是在Bootstrap3上，没在Bootsrap4上，_ 