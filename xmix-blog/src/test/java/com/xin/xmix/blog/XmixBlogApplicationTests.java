package com.xin.xmix.blog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xin.xmix.blog.dao.SkillInfoDao;
import com.xin.xmix.blog.entity.SkillInfo;
import org.apache.tomcat.jni.Time;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class XmixBlogApplicationTests {
    @Autowired
    SkillInfoDao skillInfoDao;
    @Autowired
    ThreadPoolExecutor executor;
    @Test
    void contextLoads() {
        CompletableFuture<Void> 另起的线程 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("线程1");
        }, executor);
        System.out.println("主线程！");
    }

}
