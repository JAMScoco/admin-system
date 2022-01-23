package com.jamscoco.service;

import com.jamscoco.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jamscoco.utils.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JAMScoco
 * @since 2022-01-15
 */
public interface IUserService extends IService<SysUser> {

    /**
     * 注册用户
     * @param user 待注册用户实体
     * @return 统一返回对象
     */
    R doRegister(SysUser user);

    /**
     * 获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    SysUser getInfoByUsername(String username);

    /**
     * 删除用户
     * @param userId 用户id
     * @return 删除结果
     */
    R deleteUser(String userId);

    /**
     * 编辑用户
     * @param user 待编辑用户实体
     * @return 编辑结果
     */
    R editUser(SysUser user);
}
