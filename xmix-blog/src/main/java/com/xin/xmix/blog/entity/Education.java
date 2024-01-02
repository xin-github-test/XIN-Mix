package com.xin.xmix.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("blog_education")
public class Education {
    @TableId(type=IdType.AUTO)
    private Integer id;
    private String education;
    private String schoolName;
    private String major;
    private String time;
    private Integer userId;
    private int sort;
}
