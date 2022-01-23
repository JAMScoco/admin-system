package com.jamscoco.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jamscoco.entity.Role;
import com.jamscoco.entity.RolePermission;
import com.jamscoco.mapper.RoleMapper;
import com.jamscoco.mapper.RolePermissionMapper;
import com.jamscoco.mapper.UserRoleMapper;
import com.jamscoco.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jamscoco.utils.R;
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

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<String> getRolesByUserId(String userId) {
        return baseMapper.getRolesByUserId(userId);
    }

    @Override
    public void clearRoles(String userId) {
        List<String> roleIds = getRolesByUserId(userId);
        if (roleIds.size()>0){
            userRoleMapper.removeUserRoles(userId,roleIds);
        }
    }

    @Override
    public R addNewRole(Role role) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name",role.getRoleName());
        Role query = baseMapper.selectOne(queryWrapper);
        if (query == null){
            baseMapper.insert(role);
            return R.ok();
        }else {
            return R.error("角色名已存在，请更换");
        }
    }

    @Override
    public R editRole(Role role) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name",role.getRoleName());
        Role query = baseMapper.selectOne(queryWrapper);
        if(query!=null && !query.getId().equals(role.getId())){
            return R.error("用户名已存在，请更换用户名");
        }else {
            baseMapper.updateById(role);
            return R.ok();
        }
    }

    @Override
    public R deleteRole(String roleId) {
        //TODO 删除角色 条件判断
        if (true){
            return R.error("不允许删除！");
        }else {
            baseMapper.deleteById(roleId);
            return R.ok();
        }
    }

    @Override
    public void doAssigningPermissions(String roleId, String[] permissionIds) {
        for (String id : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(id);
            rolePermissionMapper.insert(rolePermission);
        }
    }
}
