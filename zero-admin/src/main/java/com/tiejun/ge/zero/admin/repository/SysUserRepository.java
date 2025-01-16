package com.tiejun.ge.zero.admin.repository;

import com.tiejun.ge.zero.admin.domain.bo.SysUserBO;

import java.util.List;

public interface SysUserRepository {

    List<SysUserBO> selectList(SysUserBO sysUserBO);

    SysUserBO selectById(Long id);

    int insert(SysUserBO sysUserBO);

    int updateById(SysUserBO sysUserBO);

    int deleteById(Long id);

    SysUserBO detailByUserName(String userName);
}
