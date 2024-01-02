package com.xin.xmix.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.xmix.blog.config.MyBatisResultHandler;
import com.xin.xmix.blog.dao.SkillInfoDao;
import com.xin.xmix.blog.entity.SkillInfo;
import com.xin.xmix.blog.service.SkillInfoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SkillInfoServiceImpl extends ServiceImpl<SkillInfoDao, SkillInfo> implements SkillInfoService {
    private final SkillInfoDao skillInfoDao;
    SkillInfoServiceImpl(SkillInfoDao skillInfoDao){
        this.skillInfoDao = skillInfoDao;
    }
    @Override
    public List<Map<String, List<String>>> getSkillInfoByUserId(Integer userId) {
        MyBatisResultHandler handler = new MyBatisResultHandler();
        skillInfoDao.getSkillInfoByUserId(userId,handler);
        return handler.getResult();
    }
}
