package com.xin.xmix.common.exception;

public class FeignCallFailedException extends RuntimeException{
    public FeignCallFailedException(){
        super();
    }
    public FeignCallFailedException(String s){
        super(s);
    }
}
