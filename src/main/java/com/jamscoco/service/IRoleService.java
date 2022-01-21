package com.jamscoco.service;

import com.jamscoco.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

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

}
