package com.jamscoco.service;

import com.jamscoco.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jamscoco.utils.R;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JAMScoco
 * @since 2022-01-15
 */
public interface IRoleService extends IService<Role> {

    /**
     * 获取用户角色
     * @param userId 用户id
     * @return 角色id列表
     */
    List<String> getRolesByUserId(String userId);

    /**
     * 清除用户的角色
     * @param userId 用户ID
     */
    void clearRoles(String userId);

    /**
     * 添加新角色
     * @param role 待添加的角色实体
     * @return 添加结果
     */
    R addNewRole(Role role);


    /**
     * 编辑角色
     * @param role 待编辑的角色实体
     * @return 编辑结果
     */
    R editRole(Role role);

    /**
     * 删除角色
     * @param roleId 待删除的角色id
     * @return 删除结果
     */
    R deleteRole(String roleId);

    /**
     * 为角色分配权限
     * @param roleId 角色ID
     * @param permissionIds 权限ID数组
     */
    void doAssigningPermissions(String roleId, String[] permissionIds);
}
