package com.xin.xmix.common.exception;

public class FileDownloadFailedException extends RuntimeException{
    public FileDownloadFailedException(){
        super();;
    }
    private FileDownloadFailedException(String s){
        super(s);
    }
}
