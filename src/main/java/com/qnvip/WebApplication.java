package com.qnvip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author EricLin
 * @Date 2019-09-25 20:24
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan({"com.qnvip.commons.mybatis.mapper", "com.qnvip.luck.dao"})
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
