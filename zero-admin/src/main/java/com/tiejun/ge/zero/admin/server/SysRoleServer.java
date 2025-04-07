package com.tiejun.ge.zero.admin.server;

import com.tiejun.ge.zero.admin.domain.bo.SysRoleBO;

import java.util.List;

public interface SysRoleServer {

    int updateById(SysRoleBO sysRoleBO);

    int insert(SysRoleBO sysRoleBO);

    int deleteById(Long id);

    List<SysRoleBO> selectList(SysRoleBO sysRoleBO);

    SysRoleBO selectById(Long sysRoleId);
}
