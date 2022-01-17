package com.jamscoco.handler;


import com.alibaba.fastjson.JSONObject;
import com.jamscoco.utils.R;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功处理
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        R r;
        if (e instanceof BadCredentialsException){
           r =  R.error("密码错误");
        }
        else if (e instanceof InternalAuthenticationServiceException){
            r =  R.error("用户不存在");
        }
        else if (e instanceof DisabledException){
            r =  R.error("账号不可用");
        }else {
            r = R.error("未知错误");
        }
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        httpServletResponse.getWriter().write(JSONObject.toJSONString(r));
    }
}
