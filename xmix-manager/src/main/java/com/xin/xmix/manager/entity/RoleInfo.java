package com.xin.xmix.manager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("manager_role_info")
public class RoleInfo implements Serializable {
    @TableId(type=IdType.AUTO)
    private Integer id;
    private Integer roleId;
    private String telephone;
    private String email;
    private String address;
    private String contactAddress;
}
