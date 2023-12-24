package com.xin.xmix.blog.entity.feign;

import lombok.Data;
@Data
public class RoleFile {

    private Integer id;
    private String name;
    private String type;
    private Long size;
    private String url;
    private String md5;
    private boolean isDelete;
    private boolean enable;
    private Integer roleId;
}
