package com.xin.xmix.manager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Data
@TableName("manager_role")
public class Role {
    @TableId(type=IdType.AUTO)
    private Integer id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String nickname;
    private int privilege;
    private boolean state;
    private String avatarUrl;

    //createTime需要对其的setter方法进行增强
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date createTime;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)  //该属性只能读，不能修改
    private Date updateTime;

    /**
     * 属性createTime的setter方法增强，用于日期格式的转换
     * @param createTime: "yyyy-MM-dd"
     */
    public void setCreateTime(String createTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date time;
        try {
             time = format.parse(createTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.createTime = time;
    }
}
