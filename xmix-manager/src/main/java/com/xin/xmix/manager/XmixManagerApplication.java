package com.xin.xmix.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient  //服务注册发现
@SpringBootApplication
public class XmixManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmixManagerApplication.class, args);
    }

}
