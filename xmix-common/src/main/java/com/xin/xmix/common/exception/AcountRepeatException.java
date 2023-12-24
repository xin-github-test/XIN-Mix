package com.xin.xmix.common.exception;

public class AcountRepeatException extends RuntimeException{

    public AcountRepeatException(){
        super();
    }
    public AcountRepeatException(String s){
        super(s);
    }
}
