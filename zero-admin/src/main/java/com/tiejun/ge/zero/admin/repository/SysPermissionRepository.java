package com.tiejun.ge.zero.admin.repository;

import com.tiejun.ge.zero.admin.domain.bo.SysMenuBO;

import java.util.List;
import java.util.Set;

public interface SysPermissionRepository {

    Set<String> selectPermsByUserId(Long id);

    Set<String> selectSysRoleByUserId(Long id);

    List<SysMenuBO> selectAllSysMenu();

    List<SysMenuBO> selectSysMenuByUserId(Long userId);
}
