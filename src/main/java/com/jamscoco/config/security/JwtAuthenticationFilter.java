package com.jamscoco.config.security;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Bxsheng
 * @blogAddress www.kdream.cn
 * @createTIme 2020/9/16
 * since JDK 1.8
 */

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Value("${jwt.key}")
    private byte[] key;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("H_token");
        //如果请求头中没有Authorization信息则直接放行了
        if (token == null) {
            chain.doFilter(request, response);
            return;
        }
        //如果请求头中有token,则进行解析，并且设置认证信息
        UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
        //设置上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        super.doFilterInternal(request, response, chain);
    }

    //获取用户信息
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, "sdhfjshfkjshfsuf".getBytes());
        String json = aes.decryptStr(token, CharsetUtil.CHARSET_UTF_8);
        Map info = JSONObject.parseObject(json, Map.class);
        String username = (String) info.get("username");
        // 获得权限 添加到权限上去
        String authorities =  info.get("authorities").toString();
        List list = JSONObject.parseObject(authorities, List.class);
        List<GrantedAuthority> roles = new ArrayList<>();
        for (Object o : list) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(o.toString());
            roles.add(grantedAuthority);
        }
        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, null, roles);
        }
        return null;
    }

}

