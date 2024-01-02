package com.xin.xmix.manager.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.xin.xmix.common.exception.TokenVerificationFailedException;
import com.xin.xmix.manager.entity.Role;
import com.xin.xmix.manager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private RoleService roleService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取request中的 token
        String token = request.getHeader("token");
        //2.不是映射到方法的直接放行
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        //3.开始验证
        if(!StrUtil.isBlank(token)){
            //从token获取用户的id
            String roleId = JWT.decode(token).getAudience().get(0);
            //通过id获取用户的密码
            Role role = roleService.getById(roleId);
            if(null != role){
                String password = role.getPassword();
                //用户密码加签验证
                JWTVerifier verifier = JWT.require(Algorithm.HMAC256(password)).build();
                try {
                    verifier.verify(token);
                    //同时将权限参数设置到request中，方便后续进行权限校验
                    request.setAttribute("privilege",role.getPrivilege());
                    return true;
                } catch (JWTVerificationException e) {
                    throw new TokenVerificationFailedException();
                }
            }
        }
        throw new TokenVerificationFailedException();
    }
}
