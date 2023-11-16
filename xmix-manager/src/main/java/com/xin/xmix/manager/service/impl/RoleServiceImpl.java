package com.xin.xmix.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.xmix.manager.dao.RoleDao;
import com.xin.xmix.manager.entity.Role;
import com.xin.xmix.manager.service.RoleService;
import com.xin.xmix.manager.vo.RoleVo;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service("RoleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {
    /**
     * 登陆
     * @param roleVo
     */
    @Override
    public boolean Login(RoleVo roleVo) {
        Role role = new Role();
        String username = roleVo.getUsername();
        String nickname = roleVo.getNickname();
        role.setId(roleVo.getId());
        role.setUsername(username);
        role.setPassword(roleVo.getPassword());
        role.setNickname(nickname == null?username:nickname);
        role.setPrivilege(roleVo.getPrivilege());
        role.setState(roleVo.isState());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        role.setCreateTime(simpleDateFormat.format(new Date()));
        return saveOrUpdate(role);
    }
}
