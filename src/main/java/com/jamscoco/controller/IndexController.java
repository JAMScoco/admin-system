package com.jamscoco.controller;

import com.jamscoco.entity.SysUser;
import com.jamscoco.service.IUserService;
import com.jamscoco.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(tags="登录注册相关控制器")
public class IndexController {

    @Autowired
    private IUserService userService;

    @ApiOperation("用户注册")
    @PostMapping("register")
    public R register(@RequestBody SysUser user){
        return userService.doRegister(user);
    }
}
