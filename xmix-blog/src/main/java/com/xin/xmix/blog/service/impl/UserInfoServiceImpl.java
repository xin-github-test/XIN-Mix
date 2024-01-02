package com.xin.xmix.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.xmix.blog.dao.UserInfoDao;
import com.xin.xmix.blog.entity.Education;
import com.xin.xmix.blog.entity.UserInfo;
import com.xin.xmix.blog.service.EducationService;
import com.xin.xmix.blog.service.SkillInfoService;
import com.xin.xmix.blog.service.UserInfoService;
import com.xin.xmix.blog.vo.BlogInfoVo;
import com.xin.xmix.common.exception.GetInfoFailedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements UserInfoService {
    private final ThreadPoolExecutor executor;
    private final EducationService educationService;
    private final SkillInfoService skillInfoService;
    UserInfoServiceImpl(ThreadPoolExecutor executor,EducationService educationService,SkillInfoService skillInfoService){
        this.executor = executor;
        this.educationService = educationService;
        this.skillInfoService = skillInfoService;
    }
    @Override
    public BlogInfoVo getBlogInfoByRoleId(Integer roleId) {
        BlogInfoVo blogInfoVo = new BlogInfoVo();
        //1.获取博主的基本信息
        CompletableFuture<UserInfo> userInfoFuture = CompletableFuture.supplyAsync(() -> {
            QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("role_id",roleId);
            UserInfo userInfo = this.getOne(queryWrapper);
            //属性的封装
            blogInfoVo.setId(userInfo.getId());
            blogInfoVo.setName(userInfo.getName());
            blogInfoVo.setAddress(userInfo.getAddress());
            blogInfoVo.setProfile(userInfo.getProfile());
            blogInfoVo.setJobIntention(userInfo.getJobIntention());
            blogInfoVo.setCodeAge(userInfo.getCodeAge());
            return userInfo;
        }, executor);
        //2.获取博主的学历信息
        CompletableFuture<Void> eduInfoFuture = userInfoFuture.thenAcceptAsync((res) -> {
            QueryWrapper<Education> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", res.getId())
                        .orderByDesc("sort");
            List<Education> educations = educationService.list(queryWrapper);
            blogInfoVo.setEducations(educations);
        }, executor);
        //3.获取博主的技能信息
        CompletableFuture<Void> skillInfoFuture = userInfoFuture.thenAcceptAsync((res) -> {
            //复杂sql的执行，定义mapper.xml
            List<Map<String, List<String>>> skillsInfo = skillInfoService.getSkillInfoByUserId(res.getId());
            blogInfoVo.setSkills(skillsInfo);
        }, executor);
        //4.最后等待所有的线程都执行完毕
        try {
            CompletableFuture.allOf(userInfoFuture,eduInfoFuture,skillInfoFuture).get();
        } catch (Exception e) {
            throw new GetInfoFailedException();
        }
        return blogInfoVo;
    }
}
