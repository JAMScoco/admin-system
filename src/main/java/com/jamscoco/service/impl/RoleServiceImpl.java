package com.jamscoco.service.impl;

import com.jamscoco.entity.Role;
import com.jamscoco.mapper.RoleMapper;
import com.jamscoco.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
