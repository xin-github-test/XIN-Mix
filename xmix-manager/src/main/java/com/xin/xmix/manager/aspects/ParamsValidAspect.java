package com.xin.xmix.manager.aspects;

import com.xin.xmix.common.exception.LoginOrRegParamInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class ParamsValidAspect {

    @Pointcut("execution(* com.xin.xmix.manager.controller.RoleController.Login(..)) || execution(* com.xin.xmix.manager.controller.RoleController.Register(..))")
    private void LogAndRespointcut(){}

    @Before(value = "LogAndRespointcut()")
    public void ParamsValid(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        log.info("JSR参数校验开始...");
        for (Object arg: args) {
            if(arg instanceof BindingResult){
                Map<String, String> validErrors = getValidErrors((BindingResult) arg);
                if(validErrors != null && validErrors.size()>0){
                    throw new LoginOrRegParamInvalidException("用户名或密码为空！");
                }
                break;
            }
        }
        log.info("JSR参数校验通过！");
    }
    private Map<String, String> getValidErrors(BindingResult result){
        Map<String, String> errorMap = null;
        if(result.hasErrors()){
            errorMap = result.getFieldErrors().stream().collect(
                    Collectors.toMap(
                            fieldError -> {
                                String field = fieldError.getField();
                                if (StringUtils.hasLength(field)) {
                                    return field;
                                }
                                //需要进行异常处理，但是正常的情况不会进行到这里
                                throw new NullPointerException();
                            },
                            fieldError -> {
                                String message = fieldError.getDefaultMessage();
                                if (StringUtils.hasLength(message)) {
                                    return message;
                                }
                                //需要进行异常处理，但是正常情况下不会进行到这一步
                                throw new NullPointerException();
                            }
                    )
            );
        }
        return errorMap;
    }
}
