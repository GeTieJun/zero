package com.tiejun.ge.zero.admin.server.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tiejun.ge.zero.admin.domain.bo.SysMenuBO;
import com.tiejun.ge.zero.admin.repository.SysPermissionRepository;
import com.tiejun.ge.zero.admin.server.SysPermissionServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: zero
 * @description: 系统权限服务
 * @author: getiejun
 * @create: 2025-01-05 22:34
 **/
@Service
public class SysPermissionServerImpl implements SysPermissionServer {

    @Resource
    private SysPermissionRepository sysPermissionRepository;

    @Override
    public Set<String> selectPermsByUserId(Long userId) {
        // 查询用户对应的权限信息
        Set<String> roles = sysPermissionRepository.selectSysRoleByUserId(userId);
        if(ObjectUtil.isNull(roles)) {
            return new HashSet<>();
        }

        // 如果是admin 则赋所有权限
        if(roles.contains("admin")) {
            return Arrays.asList("*:*:*").stream().collect(Collectors.toSet());
        }

        // 其他情况则查询所有权限
        return sysPermissionRepository.selectPermsByUserId(userId);
    }

    @Override
    public Set<String> selectRolesByUserId(Long userId) {
        return sysPermissionRepository.selectSysRoleByUserId(userId);
    }

    @Override
    public List<SysMenuBO> selectAllSysMenu() {
        return sysPermissionRepository.selectAllSysMenu();
    }

    @Override
    public List<SysMenuBO> selectSysMenuByUserId(Long userId) {
        return sysPermissionRepository.selectSysMenuByUserId(userId);
    }

}
