package com.tiejun.ge.zero.admin.server;

import com.tiejun.ge.zero.admin.domain.bo.SysMenuBO;

import java.util.List;
import java.util.Set;

public interface SysPermissionServer {

    Set<String> selectPermsByUserId(Long userId);

    Set<String> selectRolesByUserId(Long userId);

    List<SysMenuBO> selectAllSysMenu();

    List<SysMenuBO> selectSysMenuByUserId(Long userId);
}
