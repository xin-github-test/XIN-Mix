package com.xin.xmix.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("blog_skill_info")
public class SkillInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String skillName;
    private String category;
    private int proficiency;
    private Integer userId;
}
