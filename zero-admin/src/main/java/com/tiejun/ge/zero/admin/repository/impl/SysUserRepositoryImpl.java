package com.tiejun.ge.zero.admin.repository.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tiejun.ge.zero.admin.domain.bo.SysUserBO;
import com.tiejun.ge.zero.admin.domain.po.SysUser;
import com.tiejun.ge.zero.admin.mapper.SysUserMapper;
import com.tiejun.ge.zero.admin.repository.SysUserRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: zero
 * @description: 系统用户持久层
 * @author: getiejun
 * @create: 2024-12-06 09:49
 **/
@Repository
public class SysUserRepositoryImpl implements SysUserRepository {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUserBO> selectList(SysUserBO sysUserBO) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysUserBO.getUserName()), SysUser::getUserName, sysUserBO.getUserName());
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysUserBO.getNickName()), SysUser::getNickName, sysUserBO.getNickName());
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysUserBO.getUserType()), SysUser::getUserType, sysUserBO.getUserType());
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysUserBO.getEmail()), SysUser::getEmail, sysUserBO.getEmail());
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysUserBO.getPhoneNumber()), SysUser::getPhoneNumber, sysUserBO.getPhoneNumber());
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysUserBO.getSex()), SysUser::getSex, sysUserBO.getSex());
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysUserBO.getAvatar()), SysUser::getAvatar, sysUserBO.getAvatar());
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysUserBO.getPassword()), SysUser::getPassword, sysUserBO.getPassword());
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysUserBO.getStatus()), SysUser::getStatus, sysUserBO.getStatus());
        List<SysUser> sysUsers = sysUserMapper.selectList(queryWrapper);
        return CollUtil.isEmpty(sysUsers) ? null : sysUsers.stream().map(SysUser::toBO).collect(Collectors.toList());
    }

    @Override
    public SysUserBO selectById(Long userId) {
        SysUser sysUser = sysUserMapper.selectById(userId);
        return ObjectUtil.isNull(sysUser) ? null : sysUser.toBO();
    }

    @Override
    public int insert(SysUserBO sysUserBO) {
        SysUser sysUser = new SysUser(sysUserBO);
        return sysUserMapper.insert(sysUser);
    }

    @Override
    public int updateById(SysUserBO sysUserBO) {
        SysUser sysUser = new SysUser(sysUserBO);
        return sysUserMapper.updateById(sysUser);
    }

    @Override
    public int deleteById(Long userId) {
        return sysUserMapper.deleteById(userId);
    }

    @Override
    public SysUserBO detailByUserName(String userName) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserName, userName);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        return ObjectUtil.isNull(sysUser) ? null : sysUser.toBO();
    }
}
