package com.xin.xmix.blog.controller;

import com.xin.xmix.blog.service.UserInfoService;
import com.xin.xmix.blog.vo.BlogInfoVo;
import com.xin.xmix.common.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class UserInfoController {
    private final UserInfoService userInfoService;
    UserInfoController(UserInfoService userInfoService){
        this.userInfoService = userInfoService;
    }

    /**
     * 通过roleId获取博客的展示信息 测试成功
     * @param roleId 用户的roleId
     * @return R
     */
    @GetMapping("/{roleId}")
    public R displayUserInfo(@PathVariable("roleId")Integer roleId){
        BlogInfoVo blogInfoVo = userInfoService.getBlogInfoByRoleId(roleId);
        return R.ok().put("data",blogInfoVo);
    }
}
