package com.xin.xmix.common.exception;
public class LoginOrRegParamInvalidException extends RuntimeException{
    public LoginOrRegParamInvalidException(){
        super();
    }
    public LoginOrRegParamInvalidException(String s){
        super(s);
    }

}
