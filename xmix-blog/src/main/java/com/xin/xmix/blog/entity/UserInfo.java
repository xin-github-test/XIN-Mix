package com.xin.xmix.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("blog_user_info")
public class UserInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private int codeAge;
    private String address;
    private String profile;
    private String jobIntention;
    private Integer roleId;
}
