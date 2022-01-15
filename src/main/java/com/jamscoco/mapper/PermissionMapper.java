package com.jamscoco.mapper;

import com.jamscoco.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JAMScoco
 * @since 2022-01-15
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> getPermissionsByUserId(String id);

    List<Permission> getPermissionsByUrl(String requestUrl);
}
