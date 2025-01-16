package com.tiejun.ge.zero.admin.repository;

import com.tiejun.ge.zero.admin.domain.bo.SysMenuBO;

import java.util.List;

public interface SysMenuRepository {

    List<SysMenuBO> selectList(SysMenuBO sysMenuBO);

    SysMenuBO selectById(Long id);

    int insert(SysMenuBO sysMenuBO);

    int updateById(SysMenuBO sysMenuBO);

    int deleteById(Long id);
}
