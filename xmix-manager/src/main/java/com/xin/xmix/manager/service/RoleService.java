package com.xin.xmix.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.xmix.manager.entity.Role;
import com.xin.xmix.manager.vo.RoleLoginVo;
import com.xin.xmix.manager.vo.RoleVo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface RoleService extends IService<Role> {
    /**
     * 注册
     * @param roleLoginVo:用户提交的信息
     */
    RoleLoginVo Register(RoleLoginVo roleLoginVo);

    /**
     * 登陆
     * @param roleLoginVo：用户提交的信息
     * @return ：返回用户的信息
     */
    RoleLoginVo Login(RoleLoginVo roleLoginVo);

    /**
     * 更新用户信息
     * @param roleVo ： 最新的用户信息
     */
    void updateUser(RoleVo roleVo);

    /**
     * 删除用户
     * @param id ： 用户id
     */
    void deleteUser(Integer id);

    /**
     * 批量删除
     * @param ids ： 需要删除的用户的id
     */
    void batchDeleteUserByIds(String ids);

    /**
     * 导出全部数据
     * @param response httpResponse
     */
    void exportAll(HttpServletResponse response) throws IOException;
}
