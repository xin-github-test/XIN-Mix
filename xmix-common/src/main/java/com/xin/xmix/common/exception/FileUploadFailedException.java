package com.xin.xmix.common.exception;

public class FileUploadFailedException extends RuntimeException {
    public FileUploadFailedException(){
        super();
    }
    public FileUploadFailedException(String s){
        super(s);
    }
}
