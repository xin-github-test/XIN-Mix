package com.xin.xmix.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.xmix.common.exception.GetInfoFailedException;
import com.xin.xmix.common.exception.UpdateFailedException;
import com.xin.xmix.manager.dao.RoleInfoDao;
import com.xin.xmix.manager.entity.Role;
import com.xin.xmix.manager.entity.RoleInfo;
import com.xin.xmix.manager.service.RoleFileService;
import com.xin.xmix.manager.service.RoleInfoService;
import com.xin.xmix.manager.service.RoleService;
import com.xin.xmix.manager.vo.RoleInfoVo;
import com.xin.xmix.manager.vo.RoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoDao, RoleInfo> implements RoleInfoService {
    private final RoleService roleService;
    RoleInfoServiceImpl(RoleService roleService){
        this.roleService = roleService;
    }
    @Transactional
    @Override
    public RoleInfoVo getUserInfoById(Integer roleId) {
        //1.通过roleId查询到role的信息
        Role role = roleService.getById(roleId);
        //2.查询roleInfo的信息
        QueryWrapper<RoleInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        RoleInfo roleInfo = this.getOne(queryWrapper);
        //3.进行数据的封装
        RoleInfoVo roleInfoVo = new RoleInfoVo();
        if(role != null && roleInfo != null){
            BeanUtils.copyProperties(roleInfo,roleInfoVo);
            RoleVo roleVo = new RoleVo();
            BeanUtils.copyProperties(role,roleVo);
            roleInfoVo.setRoleVo(roleVo);
            return roleInfoVo;
        }
        throw new GetInfoFailedException();
    }

    @Override
    public void updateUserInfo(RoleInfo roleInfo) {
        if(!this.updateById(roleInfo)){
            throw new UpdateFailedException();
        }
    }
}
