package com.jamscoco.mapper;

import com.jamscoco.entity.RolePermission;
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
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    /**
     * 删除角色权限关系
     * @param roleId 角色id
     * @param permissionIds 权限id列表
     */
    void removeRolePermissions(String roleId, List<String> permissionIds);
}
