package com.jamscoco.handler;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSONObject;
import com.jamscoco.utils.JwtUtil;
import com.jamscoco.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录成功处理
 */

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${redis.login.key}")
    private String REDIS_LOGIN_KEY;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        R r = R.ok();
        User user = (User)authentication.getPrincipal();
        //生成token携带用户名
        String token = JwtUtil.getToken(user.getUsername());
        //将权限信息存入redis
        List list = new ArrayList<>();
        for (GrantedAuthority authority : user.getAuthorities()) {
            list.add(authority.getAuthority());
        }
        //权限信息加密
        String encode = Base64.encode(JSONObject.toJSONString(list));
        redisTemplate.opsForHash().put(REDIS_LOGIN_KEY,token,encode);
        r.put("token",token);
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        httpServletResponse.getWriter().write(JSONObject.toJSONString(r));
    }
}
