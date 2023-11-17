package com.xin.xmix.manager.controller;

import com.xin.xmix.common.utils.R;
import com.xin.xmix.manager.entity.Role;
import com.xin.xmix.manager.service.RoleService;
import com.xin.xmix.manager.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 登陆接口  测试成功
     * @param roleVo
     * @param errors：JSR后端校验
     * @return
     */
    //TODO 2.准备编写管理界面，以及Vue中登陆的请求的实现
    //继续测试，忘了以debug运行，RoleVo的设置
    @PostMapping("/login")
    public R Login(@Valid @RequestBody RoleVo roleVo, BindingResult errors){
        System.out.println("暂停");
        if(errors.hasErrors()){
            Map<String, String> collect = errors.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return R.error(301,"登陆失败！").put("errors",collect);
        }
        boolean res = roleService.Login(roleVo);
        if(res) return R.ok();
        return R.error(302,"账号或密码不正确！");
    }


}
