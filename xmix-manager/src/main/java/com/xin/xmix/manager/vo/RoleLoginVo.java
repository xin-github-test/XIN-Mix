package com.xin.xmix.manager.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xin.xmix.manager.entity.Role;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class RoleLoginVo implements Serializable {
    private Integer id;
    @NotBlank(message = "用户名必须提交")
    private String username;
    @NotBlank(message = "密码不能为空")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  //设置该属性只能写
    private String password;

    private String nickname = null;

    private String avatarUrl;
    private Boolean hasBlog;
    private int privilege = 1;
    private boolean state = true;

    //createTime需要对其的setter方法进行增强
    @JsonIgnore   //直接忽略该属性
    private String createTime;

}
