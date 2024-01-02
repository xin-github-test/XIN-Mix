package com.xin.xmix.common.exception;

public class TokenVerificationFailedException extends RuntimeException{
    public TokenVerificationFailedException(){
        super();
    }
    public TokenVerificationFailedException(String s){
        super(s);
    }
}
