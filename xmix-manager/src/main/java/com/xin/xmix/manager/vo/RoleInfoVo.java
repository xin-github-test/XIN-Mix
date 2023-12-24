package com.xin.xmix.manager.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleInfoVo implements Serializable {
    private Integer id;
    private String telephone;
    private String email;
    private String address;
    private String contactAddress;
    private RoleVo roleVo;
}
