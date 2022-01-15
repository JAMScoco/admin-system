package com.jamscoco.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jamscoco.entity.Permission;
import com.jamscoco.entity.User;
import com.jamscoco.mapper.PermissionMapper;
import com.jamscoco.mapper.UserMapper;
import com.jamscoco.service.IPermissionService;
import com.jamscoco.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || "".equals(username)) {
            throw new RuntimeException("用户不能为空");
        }
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username",username));
        if (user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<Permission> sysPermissions = permissionMapper.getPermissionsByUserId(user.getId());
        for (Permission permission : sysPermissions) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getPermissionValue());
            grantedAuthorities.add(grantedAuthority);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),user.getStatus(),true,true,true,grantedAuthorities);
    }
}
