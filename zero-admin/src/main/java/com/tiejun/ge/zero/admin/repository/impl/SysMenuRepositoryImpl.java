package com.tiejun.ge.zero.admin.repository.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tiejun.ge.zero.admin.domain.bo.SysMenuBO;
import com.tiejun.ge.zero.admin.domain.po.SysMenu;
import com.tiejun.ge.zero.admin.mapper.SysMenuMapper;
import com.tiejun.ge.zero.admin.repository.SysMenuRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: zero
 * @description: 系统菜单持久化层
 * @author: getiejun
 * @create: 2025-01-05 22:01
 **/
@Repository
public class SysMenuRepositoryImpl implements SysMenuRepository {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenuBO> selectList(SysMenuBO sysMenuBO) {
        LambdaQueryWrapper queryWrapper = new LambdaQueryWrapper();
        return sysMenuMapper.selectList(queryWrapper);
    }

    @Override
    public SysMenuBO selectById(Long id) {
        SysMenu sysMenu = sysMenuMapper.selectById(id);
        return ObjectUtil.isNull(sysMenu) ? null : sysMenu.toBO();
    }

    @Override
    public int insert(SysMenuBO sysMenuBO) {
        return sysMenuMapper.insert(new SysMenu(sysMenuBO));
    }

    @Override
    public int updateById(SysMenuBO sysMenuBO) {
        return sysMenuMapper.updateById(new SysMenu(sysMenuBO));
    }

    @Override
    public int deleteById(Long id) {
        return sysMenuMapper.deleteById(id);
    }
}
