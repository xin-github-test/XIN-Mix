package com.xin.xmix.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.xmix.manager.entity.RoleInfo;
import com.xin.xmix.manager.vo.RoleInfoVo;
import org.springframework.stereotype.Service;

public interface RoleInfoService extends IService<RoleInfo> {
    /**
     * 查询用户的信息
     * @param roleId 用户的id
     * @return RoleInfoVo
     */
    RoleInfoVo getUserInfoById(Integer roleId);

    /**
     * 修改用户信息
     * @param roleInfo  需要修改的信息的id
     */
    void updateUserInfo(RoleInfo roleInfo);
}
