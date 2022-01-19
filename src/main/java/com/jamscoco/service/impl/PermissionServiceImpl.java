package com.jamscoco.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jamscoco.entity.Permission;
import com.jamscoco.entity.SysUser;
import com.jamscoco.mapper.PermissionMapper;
import com.jamscoco.mapper.UserMapper;
import com.jamscoco.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JAMScoco
 * @since 2022-01-15
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Map<String,Object>> getMenuByUsername(String username) {
        List<Map<String,Object>> result = new ArrayList<>();
        try {
            SysUser user = userMapper.selectOne(new QueryWrapper<SysUser>().eq("username", username));
            List<Permission> parents = baseMapper.getParentMenuByUserId(user.getId());
            for (Permission parent : parents) {
                Map<String, Object> pMap = new HashMap<>();
                pMap.put("path", "/");
                pMap.put("component", "Home");
                pMap.put("icon", parent.getIcon());
                List<Permission> childrenList = baseMapper.getChildPermission(parent.getId());
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
        }catch (Exception e){
            return null;
        }
        return result;
    }
}
