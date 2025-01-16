package com.tiejun.ge.zero.admin.server;

import com.tiejun.ge.zero.admin.domain.bo.SysUserBO;

import java.util.List;

public interface SysUserServer {

    int updateById(SysUserBO sysUserBO);

    int insert(SysUserBO sysUserBO);

    int deleteById(Long id);

    List<SysUserBO> selectList(SysUserBO sysUserBO);

    SysUserBO selectById(Long id);
    
    SysUserBO detailByUserName(String userName);
}
