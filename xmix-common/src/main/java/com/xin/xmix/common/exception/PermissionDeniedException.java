package com.xin.xmix.common.exception;

public class PermissionDeniedException extends RuntimeException{
    public PermissionDeniedException(){
        super();
    }
    public PermissionDeniedException(String s){
        super(s);
    }
}
