package com.xin.xmix.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.xmix.blog.entity.SkillInfo;

import java.util.List;
import java.util.Map;

public interface SkillInfoService extends IService<SkillInfo> {
    /**
     * 通过user_id获取博主的技能信息并进行封装
     * @param userId user_id
     */
    List<Map<String, List<String>>> getSkillInfoByUserId(Integer userId);
}
