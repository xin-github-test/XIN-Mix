package com.xin.xmix.common.exception;

public class DeleteFailedException extends RuntimeException{
    public DeleteFailedException(){
        super();
    }
    public DeleteFailedException(String s){
        super(s);
    }
}
