package com.jamscoco.service;

import com.jamscoco.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JAMScoco
 * @since 2022-01-15
 */
public interface IPermissionService extends IService<Permission> {

    /**
     * 获取导航菜单
     * @param username 用户名
     * @return 菜单封装
     */
    List<Map<String,Object>> getMenuByUsername(String username);

    /**
     * 清空角色所拥有的权限
     * @param roleId 角色id
     */
    void clearPermissions(String roleId);

    /**
     * 获取角色权限
     * @param roleId 角色id
     * @return 权限id列表
     */
    List<String> getPermissionsByRoleId(String roleId);

    /**
     * 获取权限树
     * @return 权限树
     */
    List<Map<String, Object>> getPermissionsTree();
}
