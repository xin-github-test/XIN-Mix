package com.xin.xmix.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.xin.xmix.blog.feign")
@SpringBootApplication
public class XmixBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(XmixBlogApplication.class, args);
    }

}
