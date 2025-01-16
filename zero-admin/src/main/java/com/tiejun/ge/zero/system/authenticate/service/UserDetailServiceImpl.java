package com.tiejun.ge.zero.system.authenticate.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.tiejun.ge.zero.admin.domain.bo.SysUserBO;
import com.tiejun.ge.zero.admin.domain.po.SysUser;
import com.tiejun.ge.zero.admin.server.SysPermissionServer;
import com.tiejun.ge.zero.admin.server.SysUserServer;
import com.tiejun.ge.zero.common.enums.common.DelFlagEnum;
import com.tiejun.ge.zero.common.enums.user.UserStatusEnum;
import com.tiejun.ge.zero.common.exception.enums.UserExceptionEnum;
import com.tiejun.ge.zero.common.exception.module.UserException;
import com.tiejun.ge.zero.system.authenticate.domain.LoginUser;
import com.tiejun.ge.zero.system.authenticate.holder.AuthenticationContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: zero
 * @description: UserDetaillService实现类
 * @author: getiejun
 * @create: 2024-11-24 22:15
 **/
@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private SysUserServer sysUserServer;

    @Resource
    private SysPermissionServer sysPermissionServer;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名称在数据库查询用户信息
        SysUserBO sysUserBO = sysUserServer.detailByUserName(username);

        if(ObjectUtil.isNull(sysUserBO)) {
            log.error(">>UserDetailServiceImpl loadUserByUsername method. username not exist. username is [{}]", username);
            throw new UserException(UserExceptionEnum.USER_NOT_FOUND);
        }

        // 校验用户
        checkUser(sysUserBO);
        // 校验用户密码
        validatePassword(sysUserBO);

        return createUser(sysUserBO);
    }

    /**
     * 创建用户 返回UserDetails
     * @param sysUserBO
     * @return UserDetails
     */
    private UserDetails createUser(SysUserBO sysUserBO) {
        SysUser sysUser = BeanUtil.copyProperties(sysUserBO, SysUser.class);
        return new LoginUser(sysUser, sysPermissionServer.selectPermsByUserId(sysUserBO.getUserId()));
    }

    private void checkUser(SysUserBO sysUserDB) {
        // 判断用户状态是否被锁定
        if (sysUserDB.getStatus() == UserStatusEnum.DEACTIVATE.getValue()) {
            log.error(">>UserDetailServiceImpl checkUser method. user status is stop. username is [{}]", sysUserDB.getUserName());
            throw new UserException(UserExceptionEnum.USER_STATUS_NOT_USE);
        }

        // 判断用户删除状态
        if(sysUserDB.getDelFlag() == DelFlagEnum.TRUE.getValue()) {
            log.error(">>UserDetailServiceImpl checkUser method. user had deleted", sysUserDB.getUserName());
            throw new UserException(UserExceptionEnum.USER_HAS_DELETED);
        }
    }

    /**
     * 校验用户密码
     * @param sysUserDB
     */
    private void validatePassword(SysUserBO sysUserDB) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) AuthenticationContextHolder.getContextHolder();
        String password = (String) authenticationToken.getCredentials();

        if (!matchesPassword(password, sysUserDB.getPassword())) {
            throw new UserException(UserExceptionEnum.USER_PASSWORD_ERROR);
        }
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword 真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
