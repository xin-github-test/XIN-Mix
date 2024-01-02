package com.xin.xmix.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xin.xmix.blog.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoDao extends BaseMapper<UserInfo> {
}
