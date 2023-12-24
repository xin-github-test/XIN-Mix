package com.xin.xmix.manager.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class RoleVo implements Serializable {
    private Integer id;

    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  //设置该属性只能写
    private String password;
    private String avatarUrl;
    private String nickname;
    private int privilege;
    private boolean state;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)  //该属性只能读，不能修改
    private Date updateTime;

}
