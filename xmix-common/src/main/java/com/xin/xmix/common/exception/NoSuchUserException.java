package com.xin.xmix.common.exception;

public class NoSuchUserException extends RuntimeException{
    public NoSuchUserException(){
        super();
    }
    public NoSuchUserException(String s){
        super(s);
    }
}
