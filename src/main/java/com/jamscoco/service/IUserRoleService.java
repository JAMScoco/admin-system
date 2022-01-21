package com.jamscoco.service;

import com.jamscoco.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JAMScoco
 * @since 2022-01-15
 */
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 分配角色
     * @param userId 用户id
     * @param roleIds 角色id数组
     */
    void doAssigningRoles(String userId, String[] roleIds);

}
