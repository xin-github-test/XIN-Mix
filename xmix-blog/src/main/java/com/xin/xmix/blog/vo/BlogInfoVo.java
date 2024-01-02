package com.xin.xmix.blog.vo;

import com.xin.xmix.blog.entity.Education;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class BlogInfoVo implements Serializable {
    private Integer id;
    private String name;
    private int codeAge;
    private String address;
    private String profile;
    private String jobIntention;
    private List<Education> educations;
    private List<Map<String,List<String>>> skills;
}
