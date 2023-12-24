package com.xin.xmix.manager.controller;

import com.xin.xmix.common.utils.R;
import com.xin.xmix.manager.entity.RoleInfo;
import com.xin.xmix.manager.service.RoleInfoService;
import com.xin.xmix.manager.vo.RoleInfoVo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role/info")
public class RoleInfoController {
    private final RoleInfoService roleInfoService;
    //构造注入
    RoleInfoController(RoleInfoService roleInfoService){
        this.roleInfoService = roleInfoService;
    }
    /**
     * 查询当前用户的信息 测试成功
     * @param roleId 需要查询的用户的id
     * @return R
     */
    @GetMapping("/{id}")
    public R getUserInfo(@PathVariable("id") Integer roleId){
        RoleInfoVo roleInfoVo = roleInfoService.getUserInfoById(roleId);
        return R.ok().put("data",roleInfoVo);
    }

    /**
     * 修改用户信息  测试成功
     * @param roleInfo 修改的信息id
     * @return R
     */
    @PostMapping("/")
    public R updateUserInfo(@RequestBody RoleInfo roleInfo){
        roleInfoService.updateUserInfo(roleInfo);
        return R.ok();
    }

}
