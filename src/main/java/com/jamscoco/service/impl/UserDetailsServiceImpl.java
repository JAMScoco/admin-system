package com.jamscoco.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jamscoco.entity.Permission;
import com.jamscoco.entity.SysUser;
import com.jamscoco.mapper.PermissionMapper;
import com.jamscoco.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || "".equals(username)) {
            throw new RuntimeException("用户名为空");
        }
        SysUser user = userMapper.selectOne(new QueryWrapper<SysUser>().eq("username",username));
        if (user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<Permission> sysPermissions = permissionMapper.getPermissionsByUserId(user.getId());
        for (Permission permission : sysPermissions) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getPermissionValue());
            grantedAuthorities.add(grantedAuthority);
        }
        return new User(user.getUsername(),user.getPassword(),user.getStatus(),true,true,true,grantedAuthorities);
    }
}
