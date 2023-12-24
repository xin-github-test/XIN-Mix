package com.xin.xmix.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.xmix.manager.entity.RoleFile;
import com.xin.xmix.manager.vo.RoleVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface RoleFileService extends IService<RoleFile> {
    /**
     * 文件的上传（保存到服务器）
     * @param file 需要保存的文件
     * @param roleId 对应的用户的id
     */
    String upload(MultipartFile file,Integer roleId);

    /**
     * 文件的下载
     * @param fileUUID 文件UUID
     * @param response httpResponse
     */
    void downloadFile(String fileUUID, HttpServletResponse response);

    /**
     * 通过roleId获取该用户所有文件的信息
     * @param roleId roleId
     * @return List<RoleFile>
     */
    List<RoleFile> getFilesInfoByRoleId(Integer roleId);
}
