package com.xin.xmix.blog.feign;

import com.xin.xmix.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("xmix-manager")
public interface ManagerFeignService {
    @GetMapping("/file/info/{roleId}")
    R getFilesInfoByRoleId(@PathVariable("roleId") Integer roleId);
}
