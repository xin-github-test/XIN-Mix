package com.xin.xmix.manager.aspects;

import com.xin.xmix.common.exception.PermissionDeniedException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限校验
 */
@Aspect
@Component
public class PrivilegeValidationAspect {
    /**
     * 除了查询，其它操作只能管理员进行
     */
    @Pointcut("execution(* com.xin.xmix.manager.controller.RoleController.updateUser(..))" +
            "|| execution(* com.xin.xmix.manager.controller.RoleController.deleteUser(..))" +
            "|| execution(* com.xin.xmix.manager.controller.RoleController.batchDeleteUser(..))")
    private void PrivilegeValidPoint(){}
    @Before(value = "PrivilegeValidPoint()")
    public void PrivilegeValidate(JoinPoint joinPoint){
        //1.拿到当前的request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //2.取出之前存的权限参数
        int privilege = (int)request.getAttribute("privilege");
        //3.权限校验
        if(privilege != 0){
            throw new PermissionDeniedException();
        }
        System.out.println("管理员正在修改数据！"+joinPoint.getSignature().toString());
    }
}
