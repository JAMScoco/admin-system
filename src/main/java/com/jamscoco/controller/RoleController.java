package com.jamscoco.controller;


import com.jamscoco.entity.Role;
import com.jamscoco.service.IRoleService;
import com.jamscoco.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/jamscoco/role")
@Api(tags="角色管理控制器")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("getRolesByUserId")
    @ApiOperation("获取用户角色")
    public R getRolesById(@RequestParam(value = "userId")String userId){
        List<String> list = roleService.getRolesByUserId(userId);

        return R.ok().put("userRoles",list);
    }

    @GetMapping("getAllRoles")
    @ApiOperation("获取所有角色")
    public R getAllRoles(){
        List<Role> list = roleService.list();
        return R.ok().put("roleList",list);
    }
}

