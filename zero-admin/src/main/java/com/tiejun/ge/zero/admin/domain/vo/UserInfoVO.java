package com.tiejun.ge.zero.admin.domain.vo;

import com.tiejun.ge.zero.admin.domain.po.SysUser;
import lombok.Data;

import java.util.Set;

/**
 * @program: zero
 * @description: 用户信息
 * @author: getiejun
 * @create: 2025-02-09 22:54
 **/
@Data
public class UserInfoVO {

    /**
     * 用户信息
     */
    private SysUser user;

    /**
     * 用户角色
     */
    private Set<String> roles;

    /**
     * 用户权限
     */
    private Set<String> permissions;
}
