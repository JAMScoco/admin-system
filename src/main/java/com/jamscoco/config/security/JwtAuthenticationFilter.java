package com.jamscoco.config.security;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.alibaba.fastjson.JSONObject;
import com.jamscoco.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

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

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${redis.login.key}")
    private String REDIS_LOGIN_KEY;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("H_token");
        //如果请求头中没有Authorization信息则直接放行了
        if (token == null) {
            chain.doFilter(request, response);
            return;
        }
        //如果请求头中有token且合法,则进行解析，并且设置认证信息
        if (JwtUtil.verify(token)) {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
            //设置上下文
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    //获取用户信息
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        String json = "";
        try {
            String username = JwtUtil.getUsername(token);
            String encode = (String) redisTemplate.opsForHash().get(REDIS_LOGIN_KEY, token);
            String authoritiesStr = Base64.decodeStr(encode);
            List list = JSONObject.parseObject(authoritiesStr, List.class);
            // 获得权限 添加到权限上去
            List<GrantedAuthority> roles = new ArrayList<>();
            for (Object o : list) {
                roles.add(new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return (String) o;
                    }
                });
            }
            if (username != null) {
                return new UsernamePasswordAuthenticationToken(username, null, roles);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}

