package com.jamscoco.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jamscoco.entity.SysUser;
import com.jamscoco.service.IRoleService;
import com.jamscoco.service.IUserRoleService;
import com.jamscoco.service.IUserService;
import com.jamscoco.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JAMScoco
 * @since 2022-01-15
 */
@RestController
@RequestMapping("/jamscoco/user")
@Api(tags="用户管理控制器")
public class SysUserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IRoleService roleService;

    @ApiOperation("获取当前登录用户信息")
    @GetMapping("getUserInfo")
    public R getUserInfo(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser user = userService.getInfoByUsername(username);
        if (user != null){
            return R.ok().put("userInfo",user);
        }else{
            return R.error();
        }
    }

    @ApiOperation("获取当前登录用户权限")
    @GetMapping("getUserPermission")
    public R getUserPermission(){
        List<String> list = new ArrayList<>();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            list.add(authority.getAuthority());
        }
        return R.ok().put("result",list);
    }

    @ApiOperation("获取用户列表")
    @GetMapping("getUserList")
    public R getUserList(@RequestParam(value = "name")String queryName){
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username",queryName);
        List<SysUser> userList = userService.list(queryWrapper);
        return R.ok().put("result",userList);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("deleteUser")
    public R deleteUser(@RequestParam(value = "userId")String userId){
        return userService.deleteUser(userId);
    }

    @ApiOperation("新增用户")
    @PostMapping("addUser")
    public R register(@RequestBody SysUser user){
        return userService.doRegister(user);
    }

    @ApiOperation("编辑用户")
    @PostMapping("editUser")
    public R updateUser(@RequestBody SysUser user){
        return userService.editUser(user);
    }

    @ApiOperation("为用户分配角色")
    @PostMapping("AssigningRoles")
    public R AssigningRoles(@RequestParam(value = "userId")String userId,@RequestParam("roleIds")String[] roleIds){
        roleService.clearRoles(userId);
        userRoleService.doAssigningRoles(userId,roleIds);
        return R.ok("分配成功！");
    }

}

