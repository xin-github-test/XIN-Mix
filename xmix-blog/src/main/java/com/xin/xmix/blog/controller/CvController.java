package com.xin.xmix.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xin.xmix.blog.entity.feign.RoleFile;
import com.xin.xmix.blog.feign.ManagerFeignService;
import com.xin.xmix.blog.vo.CvInfoVo;
import com.xin.xmix.common.exception.FeignCallFailedException;
import com.xin.xmix.common.exception.PdfBoxLoadException;
import com.xin.xmix.common.utils.R;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class CvController {
    private final ManagerFeignService managerFeignService;
    private final String cvType = "pdf";
    @Value("${files.store.path}")
    private String storePath;
    CvController(ManagerFeignService managerFeignService){
        this.managerFeignService = managerFeignService;
    }

    /**
     * 获取默认的CV(作者本身的)
     * @return R
     */
    @GetMapping("/cv/default")
    public R getDefaultCv(HttpServletRequest request){
        //TODO 以下几个常量都应该定义在公共类中，此处耦合性太高
        Integer defaultRoleId = 1;
        return this.getCvByRoleId(defaultRoleId,request);
    }
    /**
     * 按照roleId查找对应的简历
     * @param roleId roleId
     * @return R
     */
    @GetMapping("/cv/{roleId}")
    public R getCvByRoleId(@PathVariable("roleId") Integer roleId, HttpServletRequest request){
        //直接远程调用其它服务
        R res = managerFeignService.getFilesInfoByRoleId(roleId);
        if(res.getCode() == 200){
            //调用成功但是用户没有上传过简历
            if(((List<?>)res.get("data")).size() == 0){
                return R.ok().put("data","-1");
            }
            //调用成功
            List<?> files = (List<?>)res.get("data");
            for (Object file: files) {
                RoleFile roleFile = JSON.parseObject(JSON.toJSONString(file), new TypeReference<RoleFile>() {});
                if(cvType.equals(roleFile.getType())){
                    return R.ok().put("data",resovePdfInfo(roleFile));
                }
            }
        }
        return res;
    }

    /**
     * 对pdf进行解析，获取pdf本身的一些信息（用到PDFBox库）
     * @param roleFile pdf文件
     * @return CvInfoVo
     */
    private CvInfoVo resovePdfInfo(RoleFile roleFile){
        String cvFile = storePath+cvType+"/"+roleFile.getName();
        CvInfoVo cvInfoVo = new CvInfoVo();
        PDDocument document = null;
        try {
            document = PDDocument.load(new File(cvFile));
            //获取pdf文档的总页数
            int numberOfPages = document.getNumberOfPages();
            //将数据进行封装
            cvInfoVo.setPageNum(numberOfPages);
            cvInfoVo.setName(roleFile.getName());
        } catch (Exception e) {
            throw new PdfBoxLoadException();
        }finally {
            try {
                if(null != document){
                    //这个流忘记关了，导致文件一直被占用，删除不了
                    document.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return cvInfoVo;
    }
}
