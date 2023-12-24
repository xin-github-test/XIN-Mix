package com.xin.xmix.blog.controller;

import com.xin.xmix.common.exception.BizCodeEnum;
import com.xin.xmix.common.exception.FeignCallFailedException;
import com.xin.xmix.common.exception.PdfBoxLoadException;
import com.xin.xmix.common.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * xmix-blog服务的全局异常处理
 */
@RestControllerAdvice
public class BlogGlobalExceptionHandler {
    /**
     * 处理FeignCallFailedException异常
     * @param e 异常信息
     * @return R
     */
    @ExceptionHandler(FeignCallFailedException.class)
    public R handleFeignCallFailedException(FeignCallFailedException e){
        System.out.println(e.getMessage());
        return R.error(BizCodeEnum.BLOG_FEIGN_CALL_FAILED.getCode(), BizCodeEnum.BLOG_FEIGN_CALL_FAILED.getMsg());
    }

    /**
     * 处理PdfBoxLoadException异常
     * @param e 异常信息
     * @return R
     */
    @ExceptionHandler(PdfBoxLoadException.class)
    public R handlePdfBoxLoadException(PdfBoxLoadException e){
        System.out.println(e.getMessage());
        return R.error(BizCodeEnum.BLOG_PDFBOX_LOAD_FAILED.getCode(), BizCodeEnum.BLOG_PDFBOX_LOAD_FAILED.getMsg());
    }
}
