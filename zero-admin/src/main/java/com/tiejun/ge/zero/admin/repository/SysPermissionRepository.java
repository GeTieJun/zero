package com.tiejun.ge.zero.admin.repository;

import com.tiejun.ge.zero.admin.domain.bo.SysRoleBO;

import java.util.Set;

public interface SysPermissionRepository {

    Set<String> selectPermsByUserId(Long id);

    SysRoleBO selectSysRoleByUserId(Long id);
}
