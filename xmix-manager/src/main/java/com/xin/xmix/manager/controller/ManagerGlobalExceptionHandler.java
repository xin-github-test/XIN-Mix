package com.xin.xmix.manager.controller;

import com.xin.xmix.common.exception.*;
import com.xin.xmix.common.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理controller
 * 使用@RestControllerAdvice和@ExceptionHandler
 */
@RestControllerAdvice
public class ManagerGlobalExceptionHandler {
    /**
     * 处理token验证失败的异常
     * @param e 异常信息
     * @return R
     */
    @ExceptionHandler(TokenVerificationFailedException.class)
    public R handleTokenVerificationFailedException(TokenVerificationFailedException e){
        System.out.println(e.getMessage());
        return R.error(BizCodeEnum.TOKEN_VERIFICATION_FAILED.getCode(), BizCodeEnum.TOKEN_VERIFICATION_FAILED.getMsg());
    }
    /**
     * 处理LoginOrRegParamInvalidException异常
     * @param e ：异常类
     * @return ： R 全局统一返回
     */
    @ExceptionHandler(LoginOrRegParamInvalidException.class)
    public R handleLoginOrRegParamInvalidException(LoginOrRegParamInvalidException e){
        return R.error(e.getMessage());
    }

    /**
     * 处理 AcountRepeatException 异常
     * @param e 异常类
     * @return  全局统一返回 R
     */
    @ExceptionHandler(AcountRepeatException.class)
    public R handleAcountRepeatException(AcountRepeatException e){
        System.out.println(e.getMessage());
        return R.error(BizCodeEnum.MANAGER_ACCOUNT_REPEAT.getCode(), BizCodeEnum.MANAGER_ACCOUNT_REPEAT.getMsg());
    }

    /**
     * 处理 LoginNoPassException 异常
     * @param e  异常信息
     * @return  全局统一返回R
     */
    @ExceptionHandler(LoginNoPassException.class)
    public R handleLoginNoPassException(LoginNoPassException e){
        System.out.println(e.getMessage());
        return R.error(BizCodeEnum.MANAGER_ACCOUNT_WRONG.getCode(), BizCodeEnum.MANAGER_ACCOUNT_WRONG.getMsg());
    }

    /**
     * 处理UpdateFailedException异常
     * @param e 异常信息
     * @return R
     */
    @ExceptionHandler(UpdateFailedException.class)
    public R handleUpdateFailedException(UpdateFailedException e){
        System.out.println(e.getMessage());
        return R.error(BizCodeEnum.MANAGER_UPDATE_FAILED.getCode(),BizCodeEnum.MANAGER_UPDATE_FAILED.getMsg());
    }

    /**
     * 处理DeleteFailedException异常
     * @param e 异常信息
     * @return R
     */
    @ExceptionHandler(DeleteFailedException.class)
    public R handleDeleteFailedException(DeleteFailedException e){
        System.out.println(e.getMessage());
        return R.error(BizCodeEnum.MANAGER_DELETE_FAILED.getCode(), BizCodeEnum.MANAGER_DELETE_FAILED.getMsg());
    }

    /**
     * 处理ExportFailedException异常
     * @param e 异常信息
     * @return R
     */
    @ExceptionHandler(ExportFailedException.class)
    public R handleExportFailedException(ExportFailedException e){
        System.out.println(e.getMessage());
        return R.error(BizCodeEnum.MANAGER_EXPORT_FAILED.getCode(),BizCodeEnum.MANAGER_EXPORT_FAILED.getMsg());
    }
    /**
     * 处理GetInfoFailedException异常
     * @param e 异常信息
     * @return R
     */
    @ExceptionHandler(GetInfoFailedException.class)
    public R handleGetInfoFailedException(GetInfoFailedException e){
        System.out.println(e.getMessage());
        return R.error(BizCodeEnum.MANAGER_GET_INFO_FAILED.getCode(), BizCodeEnum.MANAGER_GET_INFO_FAILED.getMsg());
    }

    /**
     * 处理FileUploadFailedException异常
     * @param e 异常信息
     * @return R
     */
    @ExceptionHandler(FileUploadFailedException.class)
    public R handleFileUploadFailedException(FileUploadFailedException e){
        System.out.println(e.getMessage());
        return R.error(BizCodeEnum.MANAGER_FILE_UPLOAD_FAILED.getCode(), BizCodeEnum.MANAGER_FILE_UPLOAD_FAILED.getMsg());
    }
    @ExceptionHandler(NoSuchUserException.class)
    public R handleNoSuchUserException(NoSuchUserException e){
        System.out.println(e.getMessage());
        return R.error(BizCodeEnum.NO_SUCH_USER.getCode(), BizCodeEnum.NO_SUCH_USER.getMsg());
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public R handlePermissionDeniedException(PermissionDeniedException e){
        System.out.println(e.getMessage());
        return R.error(BizCodeEnum.PERMISSION_DENIED.getCode(), BizCodeEnum.PERMISSION_DENIED.getMsg());
    }
}
