package com.jamscoco.mapper;

import com.jamscoco.entity.Role;
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
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id获取所有角色id
     * @param userId 用户id
     * @return 角色id列表
     */
    List<String> getRolesByUserId(String userId);
}
