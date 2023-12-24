package com.xin.xmix.manager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xin.xmix.common.exception.ExportFailedException;
import com.xin.xmix.common.utils.R;

import com.xin.xmix.manager.entity.Role;
import com.xin.xmix.manager.service.RoleService;
import com.xin.xmix.manager.vo.RoleLoginVo;


import com.xin.xmix.manager.vo.RoleVo;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;


@RestController
@RequestMapping("/role")
public class RoleController {
    
    private final RoleService roleService;
    //构造注入
    RoleController(RoleService roleService){
        this.roleService = roleService;
    }
    /**
     * 注册接口  测试成功
     * @param roleLoginVo :账号和密码
     * @param result :JSR后端校验
     * @return R
     */
    //TODO 注册和登陆需要捋顺逻辑
    @PostMapping("/register")
    public R Register(@Valid @RequestBody RoleLoginVo roleLoginVo, BindingResult result){
        RoleLoginVo roleInfo = roleService.Register(roleLoginVo);
        return R.ok().put("roleInfo",roleInfo);
    }
    /**
     * 登陆接口  测试成功
     * @param roleLoginVo :账号和密码
     * @param result :JSR后端校验
     * @return R
     */
    @PostMapping("/login")
    public R Login(@Valid @RequestBody RoleLoginVo roleLoginVo, BindingResult result){
        RoleLoginVo roleInfo = roleService.Login(roleLoginVo);
        return R.ok().put("roleInfo",roleInfo);
    }

    /**
     * 分页数据 接口测试成功
     * @param pageNum ：当前页
     * @param pageSize：一页大小
     * @param searchItem：搜索项
     * @return ：R
     */
    @GetMapping("/page")
    public R findPage(@RequestParam Integer pageNum,
                      @RequestParam Integer pageSize,
                      @RequestParam(defaultValue = "") String searchItem){
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if(!"".equals(searchItem)){
            //搜索条件的拼接
            queryWrapper.like("username",searchItem).or()
                        .like("nickname",searchItem);
        }
        Page<Role> page = roleService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return R.ok().put("data",page);
    }

    /**
     * 更新用户信息 测试成功
     * @param roleVo：最新的用户信息
     * @return R
     */
    @PostMapping("/update")
    public R updateUser(@RequestBody RoleVo roleVo){
        roleService.updateUser(roleVo);
        return R.ok();
    }

    /**
     * 删除用户 测试成功
     * @param id ：用户id
     * @return R
     */
    @DeleteMapping("/delete/{id}")
    public R deleteUser(@PathVariable("id") Integer id){
        roleService.deleteUser(id);
        return R.ok();
    }

    /**
     * 批量删除 测试成功
     * @param ids：需要的删除的用户的id
     * @return R
     */
    @DeleteMapping("/batch/delete")
    public R batchDeleteUser(@RequestParam("ids") String ids){
        roleService.batchDeleteUserByIds(ids);
        return R.ok();
    }

    @GetMapping("/export")
    public R exportData(HttpServletResponse response){
        try {
            roleService.exportAll(response);
            return R.ok();
        } catch (IOException e) {
            throw new ExportFailedException();
        }
    }

}
