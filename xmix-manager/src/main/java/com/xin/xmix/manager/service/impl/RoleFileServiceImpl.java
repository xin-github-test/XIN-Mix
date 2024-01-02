package com.xin.xmix.manager.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.xmix.common.exception.FileDownloadFailedException;
import com.xin.xmix.common.exception.FileUploadFailedException;
import com.xin.xmix.manager.dao.RoleFileDao;
import com.xin.xmix.manager.entity.RoleFile;
import com.xin.xmix.manager.service.RoleFileService;
import com.xin.xmix.manager.vo.RoleVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class RoleFileServiceImpl extends ServiceImpl<RoleFileDao, RoleFile> implements RoleFileService {
    @Value("${files.ip}")
    private String serverIp;
    @Value("${files.port}")
    private String serverPort;
    @Value("${files.upload.path}")
    private String prexPath;
    private final ThreadPoolExecutor executor;
    RoleFileServiceImpl(ThreadPoolExecutor executor){
        this.executor = executor;
    }
    @Transactional
    @Override
    public String upload(MultipartFile file,Integer roleId) {
        //1.获取上传的文件的信息
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        Long size = file.getSize();

        //2.创建一个文件夹对象(若是不存在则直接创建一个)
        String uploadPath = prexPath + type +"/";
        File uploadPathFile = new File(uploadPath);
        if(!uploadPathFile.exists()){
            uploadPathFile.mkdirs();
        }

        //3.创建文件的唯一标识并作为其在服务器上的文件名
        String uuid = IdUtil.fastSimpleUUID();
        String uploadFileUUID = uuid + StrUtil.DOT + type;

        //4.该文件的获取地址(通过该地址可以获取该文件)
        String url = "http://"+serverIp+":"+serverPort+"/file/"+uploadFileUUID;
        //5.创建该文件的对象(直接会在目录中创建该文件，若是最后有同样的文件，需要进行删除)
        File theUploadFile = new File(uploadPath + uploadFileUUID);
        try {
            //6.将上传的文件中的内容写入到刚创建的文件对象中
            file.transferTo(theUploadFile);
            //7.将该文件的信息保存到数据库中
            //7.1 获取该文件的MD5值
            String md5 = SecureUtil.md5(theUploadFile);
            //7.2 通过md5的值，在数据库中寻找是否已有该文件(数据库中不存在相同内容的文件)
            QueryWrapper<RoleFile> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("md5",md5);
            RoleFile roleFile = this.getOne(queryWrapper);
            RoleFile temRoleFile = new RoleFile();
            if(roleFile == null){
                //进行属性的封装
                temRoleFile.setSize(size);
                temRoleFile.setMd5(md5);
                temRoleFile.setDelete(false);
                temRoleFile.setEnable(true);
                temRoleFile.setRoleId(roleId);

            }else{
                if(new File(uploadPath + roleFile.getName()).delete()){
                    //说明该文件已有，则直接删除原有的文件，保留刚上传的文件
                    System.out.println("文件删除成功！:"+roleFile.getName());
                }else{
                    //TODO 若是删除失败，最好记录一下，后面手动删除
                    System.out.println("文件删除失败！:"+roleFile.getName());
                }
                temRoleFile.setId(roleFile.getId());
            }
            temRoleFile.setName(uploadFileUUID);
            temRoleFile.setType(type);
            temRoleFile.setUrl(url);
            //保存或更新到数据库
            if(!this.saveOrUpdate(temRoleFile)){
                throw new FileUploadFailedException();
            }
        } catch (IOException e) {
            throw new FileUploadFailedException();
        }
        return url;
    }

    @Override
    public void downloadFile(String fileUUID, HttpServletResponse response) {
        String type = FileUtil.extName(fileUUID);
        String filePath = prexPath + type +"/";
        //1.获取下载的文件对象
        File file = new File(filePath + fileUUID);
        ServletOutputStream os = null;
        try {
            //2.获取response输出流
            os = response.getOutputStream();
            //3.设置输出流的格式
            response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileUUID,"UTF-8"));
//            response.setContentType("application/octet-stream");
            //4.读取文件上传的字节流
            os.write(FileUtil.readBytes(file));
        } catch (Exception e) {
            throw new FileDownloadFailedException();
        }finally {
            //5.刷新和关闭
            try {
                if(null != os){
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public List<RoleFile> getFilesInfoByRoleId(Integer roleId) {
        QueryWrapper<RoleFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        return this.list(queryWrapper);
    }

}
