package com.tiejun.ge.zero.admin.repository.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tiejun.ge.zero.admin.domain.bo.SysRoleBO;
import com.tiejun.ge.zero.admin.domain.po.SysRole;
import com.tiejun.ge.zero.admin.mapper.SysRoleMapper;
import com.tiejun.ge.zero.admin.repository.SysRoleRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: zero
 * @description: 系统角色持久层
 * @author: getiejun
 * @create: 2024-12-06 11:07
 **/
@Repository
public class SysRoleRepositoryImpl implements SysRoleRepository {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRoleBO> selectList(SysRoleBO sysRoleBO) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysRoleBO.getRoleName()), SysRole::getRoleName, sysRoleBO.getRoleName());
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysRoleBO.getRoleKey()), SysRole::getRoleKey, sysRoleBO.getRoleKey());
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysRoleBO.getRoleSort()), SysRole::getRoleSort, sysRoleBO.getRoleSort());
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysRoleBO.getDataScope()), SysRole::getDataScope, sysRoleBO.getDataScope());
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysRoleBO.getStatus()), SysRole::getStatus, sysRoleBO.getStatus());
        List<SysRole> sysRoles = sysRoleMapper.selectList(queryWrapper);
        return CollUtil.isEmpty(sysRoles) ? null : sysRoles.stream().map(SysRole::toBO).collect(Collectors.toList());
    }

    @Override
    public SysRoleBO selectById(Long id) {
        SysRole sysRole = sysRoleMapper.selectById(id);
        return ObjectUtil.isNull(sysRole) ? null : sysRole.toBO();
    }

    @Override
    public int insert(SysRoleBO sysRoleBO) {
        SysRole sysRole = new SysRole(sysRoleBO);
        return sysRoleMapper.insert(sysRole);
    }

    @Override
    public int updateById(SysRoleBO sysRoleBO) {
        SysRole sysRole = new SysRole(sysRoleBO);
        return sysRoleMapper.updateById(sysRole);
    }

    @Override
    public int deleteById(Long id) {
        return sysRoleMapper.deleteById(id);
    }
}
