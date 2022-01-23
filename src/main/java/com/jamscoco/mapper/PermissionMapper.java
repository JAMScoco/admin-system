package com.jamscoco.mapper;

import com.jamscoco.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JAMScoco
 * @since 2022-01-15
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据用户ID获取可访问的接口
     * @param id 用户ID
     * @return 可访问的接口
     */
    List<Permission> getPermissionsByUserId(String id);

    /**
     * 获取接口所需要的权限
     * @param requestUrl 接口链接
     * @return 所需要的权限
     */
    List<Permission> getPermissionsByUrl(String requestUrl);

    /**
     * 根据用户ID获取父级菜单
     * @param id 用户ID
     * @return 父级菜单列表
     */
    List<Permission> getParentMenuByUserId(String id);

    /**
     * 根据父级菜单id获取子菜单
     * @param pid 父级菜单id
     * @param userId 用户id
     * @return 子菜单列表
     */
    List<Permission> getChildPermission(String userId,String pid);

    /**
     * 根据角色ID获取权限ID
     * @param roleId 角色ID
     * @return 权限ID列表
     */
    List<String> getPermissionsByRoleId(String roleId);
}
