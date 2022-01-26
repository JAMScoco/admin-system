package com.jamscoco.controller;


import cn.hutool.core.util.StrUtil;
import com.jamscoco.entity.Permission;
import com.jamscoco.entity.Role;
import com.jamscoco.service.IPermissionService;
import com.jamscoco.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author JAMScoco
 * @since 2022-01-15
 */
@RestController
@RequestMapping("/jamscoco/permission")
@Api(tags = "权限控制器")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @GetMapping("getMenu")
    @ApiOperation("获取导航菜单")
    public R getMenu() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Map<String, Object>> list = permissionService.getMenuByUsername(username);
        if (list != null) {
            return R.ok().put("menus", list);
        } else {
            return R.error();
        }
    }

    @GetMapping("getPermissionsByRoleId")
    @ApiOperation("获取角色权限")
    public R getPermissionsByRoleId(@RequestParam(value = "roleId") String roleId) {
        List<String> list = permissionService.getPermissionsByRoleId(roleId);
        return R.ok().put("rolePermissions", list);
    }

    @GetMapping("getPermissionsTree")
    @ApiOperation("获取权限树")
    public R getAllRoles() {
        List<Map<String, Object>> list = permissionService.getPermissionsTree();
        return R.ok().put("permissionsTree", list);
    }

    @PostMapping("addOrEdit")
    @ApiOperation("添加或编辑权限")
    public R add(@RequestBody Permission permission) {
        if (StrUtil.isEmptyIfStr(permission.getId())) {
            permissionService.save(permission);
        } else {
            permissionService.updateById(permission);
        }
        return R.ok();
    }

    @DeleteMapping("delete")
    @ApiOperation("添加或编辑权限")
    public R delete(@RequestParam(value = "permissionId") String permissionId) {
        return permissionService.removePermissionById(permissionId);
    }

}

