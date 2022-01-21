package com.jamscoco.service.impl;

import com.jamscoco.entity.Role;
import com.jamscoco.mapper.RoleMapper;
import com.jamscoco.mapper.UserRoleMapper;
import com.jamscoco.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JAMScoco
 * @since 2022-01-15
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<String> getRolesByUserId(String userId) {
        return baseMapper.getRolesByUserId(userId);
    }

    @Override
    public void clearRoles(String userId) {
        List<String> roleIds = getRolesByUserId(userId);
        userRoleMapper.removeUserRoles(userId,roleIds);
    }
}
