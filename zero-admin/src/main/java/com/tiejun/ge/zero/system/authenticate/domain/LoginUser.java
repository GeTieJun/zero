package com.tiejun.ge.zero.system.authenticate.domain;

import com.tiejun.ge.zero.admin.domain.bo.SysUserBO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: zero
 * @description: 登录用户信息
 * @author: getiejun
 * @create: 2024-12-07 16:44
 **/
@Data
public class LoginUser implements UserDetails {

    public LoginUser(){}

    public LoginUser(SysUserBO user, Set<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 系统用户
     */
    private SysUserBO user;

    /**
     * 权限列表
     */
    private Set<String> permissions = new HashSet<>();

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
