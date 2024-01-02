package com.xin.xmix.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.xmix.blog.entity.UserInfo;
import com.xin.xmix.blog.vo.BlogInfoVo;

public interface UserInfoService extends IService<UserInfo> {
    /**
     * 获取博客的展示信息
     * @param roleId roleId
     * @return BlogInfoVo
     */
    BlogInfoVo getBlogInfoByRoleId(Integer roleId);
}
