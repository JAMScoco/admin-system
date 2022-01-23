package com.jamscoco.mapper;

import com.jamscoco.entity.UserRole;
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
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 删除用户角色关系
     * @param userId 用户id
     * @param roleIds 角色id列表
     */
    void removeUserRoles(String userId, List<String> roleIds);
}
