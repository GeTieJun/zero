package com.tiejun.ge.zero.admin.server.impl;

import com.tiejun.ge.zero.admin.domain.bo.SysRoleBO;
import com.tiejun.ge.zero.admin.repository.SysRoleRepository;
import com.tiejun.ge.zero.admin.server.SysRoleServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: zero
 * @description: 系统角色实现层
 * @author: getiejun
 * @create: 2024-12-06 15:48
 **/
@Service
public class SysRoleServerImpl implements SysRoleServer {

    @Resource
    private SysRoleRepository roleRepository;

    @Override
    public int updateById(SysRoleBO sysRoleBO) {
        return roleRepository.updateById(sysRoleBO);
    }

    @Override
    public int insert(SysRoleBO sysRoleBO) {
        return roleRepository.insert(sysRoleBO);
    }

    @Override
    public int deleteById(Long id) {
        return roleRepository.deleteById(id);
    }

    @Override
    public List<SysRoleBO> selectList(SysRoleBO sysRoleBO) {
        return roleRepository.selectList(sysRoleBO);
    }

    @Override
    public SysRoleBO selectById(SysRoleBO sysRoleBO) {
        return roleRepository.selectById(sysRoleBO.getRoleId());
    }
}
