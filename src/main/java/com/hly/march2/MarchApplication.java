package com.hly.march2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = "com.hly.march2.dao")
@EnableScheduling
@EnableCaching
@EnableTransactionManagement
public class MarchApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarchApplication.class, args);
    }

}
