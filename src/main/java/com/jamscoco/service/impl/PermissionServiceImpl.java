package com.jamscoco.service.impl;

import com.jamscoco.entity.Permission;
import com.jamscoco.mapper.PermissionMapper;
import com.jamscoco.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
