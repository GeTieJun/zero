package com.tiejun.ge.zero.admin.repository.impl;

import com.tiejun.ge.zero.admin.domain.bo.SysRoleBO;
import com.tiejun.ge.zero.admin.mapper.SysPermissionMapper;
import com.tiejun.ge.zero.admin.repository.SysPermissionRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @program: zero
 * @description: 系统权限持久层
 * @author: getiejun
 * @create: 2025-01-05 22:39
 **/
@Repository
public class SysPermissionRepositoryImpl implements SysPermissionRepository {

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public Set<String> selectPermsByUserId(Long userId) {
        return sysPermissionMapper.selectPermsByUserId(userId);
    }

    @Override
    public SysRoleBO selectSysRoleByUserId(Long userId) {
        return sysPermissionMapper.selectSysRoleByUserId(userId);
    }
}
