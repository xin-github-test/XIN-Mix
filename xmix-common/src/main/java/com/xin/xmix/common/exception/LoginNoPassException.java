package com.xin.xmix.common.exception;

public class LoginNoPassException extends RuntimeException{
    public LoginNoPassException(){
        super();
    }
    public LoginNoPassException(String s){
        super(s);
    }
}
