package com.tiejun.ge.zero.admin.repository;

import com.tiejun.ge.zero.admin.domain.bo.SysRoleBO;

import java.util.List;

public interface SysRoleRepository {

    List<SysRoleBO> selectList(SysRoleBO sysRoleBO);

    SysRoleBO selectById(Long id);

    int insert(SysRoleBO sysRoleBO);

    int updateById(SysRoleBO sysRoleBO);

    int deleteById(Long id);
}
