package com.xin.xmix.manager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private String password;
    private String nickname;
    private int privilege;
    private boolean state;

    //createTime需要对其的setter方法进行增强
    private Date createTime;
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
