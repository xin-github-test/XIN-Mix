package com.xin.xmix.common.exception;

/**
 * 错误码和错误信息的定义类
 * 1.错误码的长度规定为 5 位；
 * 2.错误的前俩位表示业务的场景，最后三位表示错误码
 * 3.维护错误码后需要维护错误描述，将他们定义为枚举类
 * 错误码列表：
 * 00：表示系统未知异常
 * 10：manager服务的异常
 *  301："登陆失败！"
 *  302："账号或密码不正确！"
 *  501: "后台数据更新失败"
 *
 */
public enum BizCodeEnum {
    MANAGER_ACCOUNT_INVALID(10301,"登陆失败！"),
    MANAGER_ACCOUNT_WRONG(10302,"账号或密码不正确！"),
    MANAGER_ACCOUNT_REPEAT(10302,"账号重复！"),
    MANAGER_UPDATE_FAILED(10501,"数据更新失败！"),
    MANAGER_DELETE_FAILED(10502,"数据删除失败！"),
    MANAGER_EXPORT_FAILED(10503,"数据导出失败！"),
    MANAGER_GET_INFO_FAILED(10601,"获取用户信息失败！"),
    MANAGER_FILE_UPLOAD_FAILED(10701,"文件上传失败！"),
    MANAGER_FILE_DOWNLOAD_FAILED(10702,"文件下载失败！"),
    BLOG_FEIGN_CALL_FAILED(30003,"Feign远程调用失败！"),
    BLOG_PDFBOX_LOAD_FAILED(40001,"PDFBox加载pdf失败！"),
    BLOG_GET_INFO_FAILED(40601,"获取用户数据失败！"),
    TOKEN_VERIFICATION_FAILED(400,"token校验不通过，请重新登陆！"),
    NO_SUCH_USER(10303,"没有该用户！"),
    PERMISSION_DENIED(20001,"权限不足！");

    private final int code;
    private final String msg;

    BizCodeEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return this.code;
    }
    public String getMsg(){
        return this.msg;
    }
}
