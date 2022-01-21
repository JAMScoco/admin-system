package com.jamscoco.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jamscoco.entity.Role;
import com.jamscoco.entity.SysUser;
import com.jamscoco.entity.UserRole;
import com.jamscoco.mapper.UserMapper;
import com.jamscoco.mapper.UserRoleMapper;
import com.jamscoco.service.IUserRoleService;
import com.jamscoco.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jamscoco.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author JAMScoco
 * @since 2022-01-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements IUserService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public R doRegister(SysUser user) {
        SysUser queryUser = getInfoByUsername(user.getUsername());
        if (queryUser != null) {
            return R.error("用户名已存在，请更换用户名");
        } else {
            //密码加密，设置状态
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setStatus(true);
            //插入用户
            baseMapper.insert(user);
            //赋予默认角色
            UserRole userRole = new UserRole();
            SysUser insertUser = getInfoByUsername(user.getUsername());
            userRole.setUserId(insertUser.getId());
            userRole.setRoleId("2");
            userRoleMapper.insert(userRole);
            return R.ok();
        }
    }

    @Override
    public SysUser getInfoByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<SysUser>().eq("username", username));
    }

    @Override
    public R deleteUser(String userId) {
        //TODO 删除用户 条件判断
        if (true){
            return R.error("还有未还图书，不得删除");
        }else {
            baseMapper.deleteById(userId);
            return R.ok();
        }
    }

    @Override
    public R editUser(SysUser user) {
        SysUser queryUser = getInfoByUsername(user.getUsername());
        if (queryUser != null && !user.getId().equals(queryUser.getId())) {
            return R.error("用户名已存在，请更换用户名");
        }else {
            baseMapper.updateById(user);
            return R.ok();
        }
    }
}
