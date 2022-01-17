package com.jamscoco.handler;

import com.alibaba.fastjson.JSONObject;
import com.jamscoco.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功处理逻辑
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Value("${redis.login.key}")
    private String REDIS_LOGIN_KEY;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String token = httpServletRequest.getHeader("H_token");
        if (token != null) {
            //删除redis中的用户信息
            redisTemplate.opsForHash().delete(REDIS_LOGIN_KEY,token);
        }
        R r = R.ok("注销成功");
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSONObject.toJSONString(r));
    }
}

