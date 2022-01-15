package com.jamscoco;

import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class AdminSystemApplicationTests {

    @Test
    void contextLoads() {
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, "sdhfjshfkjshfsuf".getBytes());
        String s = aes.decryptStr("aa3161ec6eb7e335649ae682ecc44bd1d9100ea883f35e55b03d6dcb4f2e33ad02f52152d9d980c55fc47f5bc5edf5f2bd156b19e4964ddcc749a717b4188c56");
        System.out.println(s);


    }

}
