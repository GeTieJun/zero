package com.tiejun.ge.zero.admin.server;

import com.tiejun.ge.zero.admin.domain.bo.SysMenuBO;

import java.util.List;

public interface SysMenuServer {

    int updateById(SysMenuBO sysMenuBO);

    int insert(SysMenuBO sysMenuBO);

    int deleteById(Long id);

    List<SysMenuBO> selectList(SysMenuBO sysMenuBO);

    SysMenuBO selectById(SysMenuBO sysMenuBO);

}
