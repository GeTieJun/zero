package com.tiejun.ge.zero.admin.server.impl;

import com.tiejun.ge.zero.admin.domain.bo.SysMenuBO;
import com.tiejun.ge.zero.admin.repository.SysMenuRepository;
import com.tiejun.ge.zero.admin.server.SysMenuServer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: zero
 * @description: 系统菜单实现类
 * @author: getiejun
 * @create: 2025-01-05 22:20
 **/
public class SysMenuServerImpl implements SysMenuServer {

    @Resource
    private SysMenuRepository sysMenuRepository;

    @Override
    public int updateById(SysMenuBO sysMenuBO) {
        return sysMenuRepository.updateById(sysMenuBO);
    }

    @Override
    public int insert(SysMenuBO sysMenuBO) {
        return sysMenuRepository.insert(sysMenuBO);
    }

    @Override
    public int deleteById(Long id) {
        return sysMenuRepository.deleteById(id);
    }

    @Override
    public List<SysMenuBO> selectList(SysMenuBO sysMenuBO) {
        return sysMenuRepository.selectList(sysMenuBO);
    }

    @Override
    public SysMenuBO selectById(SysMenuBO sysMenuBO) {
        return null;
    }
}
