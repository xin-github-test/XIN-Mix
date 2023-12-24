package com.xin.xmix.manager.controller;

import com.xin.xmix.common.utils.R;
import com.xin.xmix.manager.entity.RoleFile;
import com.xin.xmix.manager.service.RoleFileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/file")
public class RoleFileController {
    private final RoleFileService roleFileService;
    //构造注入
    public RoleFileController(RoleFileService roleFileService){
        this.roleFileService = roleFileService;
    }
    /**
     * 上传文件  测试成功
     * @param file 用户上传的文件
     * @param roleId 用户的id
     * @return R
     */
    @PostMapping("/upload/{id}")
    public R uploadFile(@RequestParam MultipartFile file, @PathVariable("id") Integer roleId){
        String url = roleFileService.upload(file,roleId);
        return R.ok().put("data",url);
    }

    /**
     * 文件的下载 测试成功
     * @param fileUUID 文件的UUID
     * @param response HttpResponse
     * @return R
     */
    @GetMapping("/{fileUUID}")
    public R downloadFile(@PathVariable("fileUUID") String fileUUID, HttpServletResponse response){
        roleFileService.downloadFile(fileUUID,response);
        return R.ok();
    }

    /**
     * 通过roleId获取文件的信息，会有多个
     * @param roleId roleId
     * @return R
     */
    @GetMapping("/info/{roleId}")
    public R getFilesInfoByRoleId(@PathVariable("roleId") Integer roleId){
        List<RoleFile> roleFiles = roleFileService.getFilesInfoByRoleId(roleId);
        return R.ok().put("data",roleFiles);
    }
}

