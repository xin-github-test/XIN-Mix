package com.xin.xmix.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xin.xmix.blog.config.SkillInfoResult;
import com.xin.xmix.blog.entity.SkillInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;

import java.util.List;

@Mapper
public interface SkillInfoDao extends BaseMapper<SkillInfo> {
    void getSkillInfoByUserId(@Param("userId") Integer userId,ResultHandler<SkillInfoResult> handler);
    List<SkillInfo> getByUserId(@Param("userId") Integer userId);
}
