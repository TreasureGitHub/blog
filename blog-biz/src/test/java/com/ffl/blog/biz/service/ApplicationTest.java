package com.ffl.blog.biz.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author lff
 * @datetime 2020/01/01 19:15
 */
@MapperScan(basePackages = {"com.ffl.blog.dal.dao"})
//@PropertySource(value = {"classpath:application.properties"})
@SpringBootApplication(scanBasePackages = "com.ffl.blog")
public class ApplicationTest {
}
