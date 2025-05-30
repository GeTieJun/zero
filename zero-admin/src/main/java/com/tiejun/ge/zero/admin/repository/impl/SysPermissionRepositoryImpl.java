package com.tiejun.ge.zero.admin.repository.impl;

import com.tiejun.ge.zero.admin.domain.bo.SysMenuBO;
import com.tiejun.ge.zero.admin.mapper.SysPermissionMapper;
import com.tiejun.ge.zero.admin.repository.SysPermissionRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
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
    public Set<String> selectSysRoleByUserId(Long userId) {
        return sysPermissionMapper.selectSysRoleByUserId(userId);
    }

    @Override
    public List<SysMenuBO> selectAllSysMenu() {
        return sysPermissionMapper.selectAllSysMenu();
    }

    @Override
    public List<SysMenuBO> selectSysMenuByUserId(Long userId) {
        return sysPermissionMapper.selectSysMenuByUserId(userId);
    }
}
