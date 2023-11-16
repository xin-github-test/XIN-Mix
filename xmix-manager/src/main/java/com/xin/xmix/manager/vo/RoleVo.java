package com.xin.xmix.manager.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

import java.util.Date;
@Data
public class RoleVo implements Serializable {
    private Integer id;
    @NotBlank(message = "用户名必须提交")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    private String nickname = null;

    private int privilege = 1;

    private boolean state = true;

    //createTime需要对其的setter方法进行增强

    private String createTime;

    private Date updateTime;

}
