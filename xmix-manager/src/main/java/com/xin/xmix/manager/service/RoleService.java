package com.xin.xmix.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.xmix.manager.entity.Role;
import com.xin.xmix.manager.vo.RoleVo;

public interface RoleService extends IService<Role> {
    /**
     * 登陆
     * @param roleVo
     */
    boolean Login(RoleVo roleVo);
}
