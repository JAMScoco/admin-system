package com.jamscoco.handler;

import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.alibaba.fastjson.JSONObject;
import com.jamscoco.util.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 登录成功处理
 */

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Value("${jwt.key}")
    private byte[] key;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        R r = R.ok();
        Map<String, Object> info = new HashMap<>();
        User user = (User)authentication.getPrincipal();
        info.put("username",user.getUsername());
        List list = new ArrayList();
        for (GrantedAuthority authority : user.getAuthorities()) {
            list.add(authority.getAuthority());
        }
        info.put("authorities",list);
        //生成token携带用户名和权限列表
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        String json = JSONObject.toJSONString(info);
        String token = aes.encryptHex(json);
        r.put("token",token);
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        httpServletResponse.getWriter().write(JSONObject.toJSONString(r));
    }
}
