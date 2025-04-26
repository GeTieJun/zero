package com.tiejun.ge.zero.admin.repository.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tiejun.ge.zero.admin.domain.bo.SysUserBO;
import com.tiejun.ge.zero.admin.domain.po.SysUserPO;
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
        LambdaQueryWrapper<SysUserPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysUserBO.getUserName()), SysUserPO::getUserName, sysUserBO.getUserName());
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysUserBO.getNickName()), SysUserPO::getNickName, sysUserBO.getNickName());
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysUserBO.getUserType()), SysUserPO::getUserType, sysUserBO.getUserType());
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysUserBO.getEmail()), SysUserPO::getEmail, sysUserBO.getEmail());
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysUserBO.getPhoneNumber()), SysUserPO::getPhoneNumber, sysUserBO.getPhoneNumber());
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysUserBO.getSex()), SysUserPO::getSex, sysUserBO.getSex());
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysUserBO.getAvatar()), SysUserPO::getAvatar, sysUserBO.getAvatar());
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysUserBO.getPassword()), SysUserPO::getPassword, sysUserBO.getPassword());
        queryWrapper.eq(ObjectUtil.isNotEmpty(sysUserBO.getStatus()), SysUserPO::getStatus, sysUserBO.getStatus());
        List<SysUserPO> sysUserPOS = sysUserMapper.selectList(queryWrapper);
        return CollUtil.isEmpty(sysUserPOS) ? null : sysUserPOS.stream().map(SysUserPO::toBO).collect(Collectors.toList());
    }

    @Override
    public SysUserBO selectById(Long userId) {
        SysUserPO sysUserPO = sysUserMapper.selectById(userId);
        return ObjectUtil.isNull(sysUserPO) ? null : sysUserPO.toBO();
    }

    @Override
    public int insert(SysUserBO sysUserBO) {
        SysUserPO sysUserPO = new SysUserPO(sysUserBO);
        return sysUserMapper.insert(sysUserPO);
    }

    @Override
    public int updateById(SysUserBO sysUserBO) {
        SysUserPO sysUserPO = new SysUserPO(sysUserBO);
        return sysUserMapper.updateById(sysUserPO);
    }

    @Override
    public int deleteById(Long userId) {
        return sysUserMapper.deleteById(userId);
    }

    @Override
    public SysUserBO detailByUserName(String userName) {
        LambdaQueryWrapper<SysUserPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserPO::getUserName, userName);
        SysUserPO sysUserPO = sysUserMapper.selectOne(queryWrapper);
        return ObjectUtil.isNull(sysUserPO) ? null : sysUserPO.toBO();
    }

    @Override
    public IPage<SysUserBO> page(int pageNum, int pageSize, SysUserBO sysUserBO) {
        LambdaQueryWrapper<SysUserPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(ObjectUtil.isNotEmpty(sysUserBO.getUserName()), SysUserPO::getUserName, sysUserBO.getUserName());
        queryWrapper.likeLeft(ObjectUtil.isNotEmpty(sysUserBO.getPhoneNumber()), SysUserPO::getPhoneNumber, sysUserBO.getPhoneNumber());
        queryWrapper.ge(ObjectUtil.isNotEmpty(sysUserBO.getStartTime()) && ObjectUtil.isNotEmpty(sysUserBO.getEndTime()), SysUserPO::getCreateTime, sysUserBO.getStartTime());
        queryWrapper.le(ObjectUtil.isNotEmpty(sysUserBO.getStartTime()) && ObjectUtil.isNotEmpty(sysUserBO.getEndTime()), SysUserPO::getCreateTime, sysUserBO.getEndTime());
        IPage<SysUserPO> sysUserPOPage = sysUserMapper.selectPage(Page.of(pageNum, pageSize), queryWrapper);
        return sysUserPOPage.convert(SysUserPO::toBO);
    }
}
