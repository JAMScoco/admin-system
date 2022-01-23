package com.jamscoco.controller;


import com.jamscoco.entity.Role;
import com.jamscoco.service.IPermissionService;
import com.jamscoco.service.IRolePermissionService;
import com.jamscoco.service.IRoleService;
import com.jamscoco.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author JAMScoco
 * @since 2022-01-15
 */
@RestController
@RequestMapping("/jamscoco/role")
@Api(tags = "角色管理控制器")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @GetMapping("getRolesByUserId")
    @ApiOperation("获取用户角色")
    public R getRolesById(@RequestParam(value = "userId") String userId) {
        List<String> list = roleService.getRolesByUserId(userId);
        return R.ok().put("userRoles", list);
    }

    @GetMapping("getAllRoles")
    @ApiOperation("获取所有角色")
    public R getAllRoles() {
        List<Role> list = roleService.list();
        return R.ok().put("roleList", list);
    }

    @PostMapping("addRole")
    @ApiOperation("新增角色")
    public R addRole(@RequestBody Role role) {
        return roleService.addNewRole(role);
    }

    @PostMapping("editRole")
    @ApiOperation("编辑角色")
    public R editRole(@RequestBody Role role) {
        return roleService.editRole(role);
    }

    @ApiOperation("删除角色")
    @DeleteMapping("deleteRole")
    public R deleteRole(@RequestParam(value = "roleId") String roleId) {
        return roleService.deleteRole(roleId);
    }

    @ApiOperation("为角色分配权限")
    @PostMapping("AssigningPermissions")
    public R AssigningPermissions(@RequestParam(value = "roleId") String roleId, @RequestParam("permissionIds") String[] permissionIds) {
        permissionService.clearPermissions(roleId);
        roleService.doAssigningPermissions(roleId, permissionIds);
        return R.ok("分配成功！");
    }

}

