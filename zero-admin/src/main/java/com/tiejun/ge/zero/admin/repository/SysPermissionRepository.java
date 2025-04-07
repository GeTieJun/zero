package com.tiejun.ge.zero.admin.repository;

import java.util.Set;

public interface SysPermissionRepository {

    Set<String> selectPermsByUserId(Long id);

    Set<String> selectSysRoleByUserId(Long id);
}
