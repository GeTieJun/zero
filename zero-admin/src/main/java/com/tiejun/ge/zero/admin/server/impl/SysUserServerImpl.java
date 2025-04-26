package com.tiejun.ge.zero.admin.server.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tiejun.ge.zero.admin.domain.bo.SysUserBO;
import com.tiejun.ge.zero.admin.repository.SysUserRepository;
import com.tiejun.ge.zero.admin.server.SysUserServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: zero
 * @description: 系统用户服务层
 * @author: getiejun
 * @create: 2024-12-06 15:43
 **/
@Service
public class SysUserServerImpl implements SysUserServer {

    @Resource
    private SysUserRepository sysUserRepository;

    @Override
    public int updateById(SysUserBO sysUserBO) {
        return sysUserRepository.updateById(sysUserBO);
    }

    @Override
    public int insert(SysUserBO sysUserBO) {
        return sysUserRepository.insert(sysUserBO);
    }

    @Override
    public int deleteById(Long id) {
        return sysUserRepository.deleteById(id);
    }

    @Override
    public List<SysUserBO> selectList(SysUserBO sysUserBO) {
        return sysUserRepository.selectList(sysUserBO);
    }

    @Override
    public SysUserBO selectById(Long id) {
        return sysUserRepository.selectById(id);
    }

    @Override
    public SysUserBO detailByUserName(String userName) {
        return sysUserRepository.detailByUserName(userName);
    }

    @Override
    public IPage<SysUserBO> page(int pageNum, int pageSize, SysUserBO sysUserBO) {
        return sysUserRepository.page(pageNum, pageSize, sysUserBO);
    }
}
