package com.jamscoco.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jamscoco.entity.UserRole;
import com.jamscoco.mapper.UserRoleMapper;
import com.jamscoco.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JAMScoco
 * @since 2022-01-15
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public void doAssigningRoles(String userId, String[] roleIds) {
        for (String roleId : roleIds) {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                baseMapper.insert(userRole);
        }
    }


}
