package com.jamscoco.service;

import com.jamscoco.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JAMScoco
 * @since 2022-01-15
 */
public interface IPermissionService extends IService<Permission> {

    List<Map<String,Object>> getMenuByUsername(String username);
}
