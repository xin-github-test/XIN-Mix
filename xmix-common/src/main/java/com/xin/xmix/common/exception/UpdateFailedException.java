package com.xin.xmix.common.exception;

public class UpdateFailedException extends RuntimeException{
    public UpdateFailedException(){
        super();
    }
    public UpdateFailedException(String s){
        super(s);
    }
}
