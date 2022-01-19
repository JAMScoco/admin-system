package com.jamscoco.controller;


import com.jamscoco.service.IPermissionService;
import com.jamscoco.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JAMScoco
 * @since 2022-01-15
 */
@RestController
@RequestMapping("/jamscoco/permission")
@Api(tags="权限控制器")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @GetMapping("getMenu")
    @ApiOperation("获取导航菜单")
    public R getMenu(){
        String username = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Map<String,Object>> list = permissionService.getMenuByUsername(username);
        if (list != null){
            return R.ok().put("menus",list);
        }else {
            return R.error();
        }
    }
}

