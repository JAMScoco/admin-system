package com.jamscoco.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jamscoco.entity.Permission;
import com.jamscoco.entity.SysUser;
import com.jamscoco.mapper.PermissionMapper;
import com.jamscoco.mapper.RolePermissionMapper;
import com.jamscoco.mapper.UserMapper;
import com.jamscoco.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jamscoco.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author JAMScoco
 * @since 2022-01-15
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Map<String, Object>> getMenuByUsername(String username) {
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            SysUser user = userMapper.selectOne(new QueryWrapper<SysUser>().eq("username", username));
            List<Permission> parents = baseMapper.getParentMenuByUserId(user.getId());
            for (Permission parent : parents) {
                Map<String, Object> pMap = new HashMap<>();
                pMap.put("path", "/");
                pMap.put("component", "Home");
                pMap.put("icon", parent.getIcon());
                List<Permission> childrenList = baseMapper.getChildPermission(user.getId(),parent.getId());
                List<Map<String, String>> cList = new ArrayList<>();
                //叶子结点，无子菜单
                if (childrenList.size() == 1) {
                    pMap.put("leaf", true);
                    pMap.put("name", "");
                } else {
                    pMap.put("name", parent.getName());
                }
                for (Permission child : childrenList) {
                    Map<String, String> cMap = new HashMap<>();
                    cMap.put("path", child.getPath());
                    cMap.put("component", child.getComponent());
                    cMap.put("name", child.getName());
                    cMap.put("icon", child.getIcon());
                    cList.add(cMap);
                }
                pMap.put("children", cList);
                result.add(pMap);
            }
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    @Override
    public void clearPermissions(String roleId) {
        List<String> permissionIds = getPermissionsByRoleId(roleId);
        if (permissionIds.size()>0){
            rolePermissionMapper.removeRolePermissions(roleId, permissionIds);
        }
    }

    @Override
    public List<String> getPermissionsByRoleId(String roleId) {
        return baseMapper.getPermissionsByRoleId(roleId);
    }

    @Override
    public List<Map<String, Object>> getPermissionsTree() {
        List<Map<String, Object>> result = new ArrayList<>();
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("parent_id");
        //一级菜单
        List<Permission> permissionsOne = baseMapper.selectList(queryWrapper);
        for (Permission permission : permissionsOne) {
            Map<String, Object> map = new HashMap<>();
            map.put("id",permission.getId());
            map.put("parentId",permission.getParentId());
            map.put("name",permission.getName());
            map.put("type",permission.getType());
            map.put("permissionValue",permission.getPermissionValue());
            map.put("path",permission.getPath());
            map.put("component",permission.getComponent());
            map.put("icon",permission.getIcon());
            List<Map<String, Object>> child = getChildrenTree(permission.getId(),2);
            if (child!=null){
                map.put("children",child);
            }
            result.add(map);
        }
        return result;
    }

    @Override
    public R removePermissionById(String permissionId) {
        //TODO 条件判断 删除权限
        if(true){
            return R.error("不允许删除");
        }else {
            baseMapper.deleteById(permissionId);
            return R.ok();
        }
    }

    /**
     * 得到子树
     * @param pid 父权限id
     * @param deep 递归深度
     * @return
     */
    private List<Map<String, Object>> getChildrenTree(String pid, int deep) {
        if (deep == 0){
            return null;
        }else {
            List<Map<String, Object>> list = new ArrayList<>();
            QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id",pid);
            List<Permission> permissions = baseMapper.selectList(queryWrapper);
            for (Permission permission : permissions) {
                Map<String, Object> map = new HashMap<>();
                map.put("id",permission.getId());
                map.put("parentId",permission.getParentId());
                map.put("name",permission.getName());
                map.put("type",permission.getType());
                map.put("permissionValue",permission.getPermissionValue());
                map.put("path",permission.getPath());
                map.put("component",permission.getComponent());
                map.put("icon",permission.getIcon());
                List<Map<String, Object>> child = getChildrenTree(permission.getId(),deep-1);
                if (child!=null){
                    map.put("children",child);
                }
                list.add(map);
            }
            return list;
        }
    }
}
