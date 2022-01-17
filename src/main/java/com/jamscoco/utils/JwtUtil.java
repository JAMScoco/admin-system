package com.jamscoco.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static final String KEY = "JAMScoco";

    public static String getToken(String username){
        Map map = new HashMap<String, String>();
        map.put("username",username);
        return JWTUtil.createToken(map, KEY.getBytes());
    }

    public static boolean verify(String token){
        return JWTUtil.verify(token,KEY.getBytes());
    }

    public static String getUsername(String rightToken){
        JWT jwt = JWTUtil.parseToken(rightToken);
        return (String) jwt.getPayload("username");
    }
}
