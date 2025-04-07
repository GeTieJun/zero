package com.tiejun.ge.zero.admin.app;

import com.tiejun.ge.zero.admin.domain.bo.SysUserBO;
import com.tiejun.ge.zero.admin.domain.po.SysUser;
import com.tiejun.ge.zero.admin.domain.vo.UserInfoVO;
import com.tiejun.ge.zero.admin.server.SysPermissionServer;
import com.tiejun.ge.zero.admin.server.SysRoleServer;
import com.tiejun.ge.zero.admin.server.SysUserServer;
import com.tiejun.ge.zero.common.utils.BeanBuilder;
import com.tiejun.ge.zero.common.utils.SecurityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @program: zero
 * @description: 系统用户应用层
 * @author: getiejun
 * @create: 2025-01-04 22:24
 **/
@Component
public class SysUserApp {

    @Resource
    private SysUserServer sysUserServer;

    @Resource
    private SysRoleServer sysRoleServer;

    @Resource
    private SysPermissionServer sysPermissionServer;

    public List<SysUserBO> list() {
        return sysUserServer.selectList(new SysUserBO());
    }

    public UserInfoVO getUserInfo() {
        // 获取当前用户
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 获取当前用户权限
        Set<String> permissions = sysPermissionServer.selectPermsByUserId(user.getUserId());
        // 获取当前用户角色
        Set<String> roles = sysPermissionServer.selectRolesByUserId(user.getUserId());

        UserInfoVO userInfoVO = BeanBuilder.of(UserInfoVO.class)
                .with(UserInfoVO::setUser, user)
                .with(UserInfoVO::setPermissions, permissions)
                .with(UserInfoVO::setRoles, roles)
                .build();

        return userInfoVO;
    }
}
