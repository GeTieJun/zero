package com.tiejun.ge.zero.admin.app;

import com.tiejun.ge.zero.admin.domain.dto.UserLoginDTO;
import com.tiejun.ge.zero.common.exception.enums.UserExceptionEnum;
import com.tiejun.ge.zero.common.exception.module.UserException;
import com.tiejun.ge.zero.system.authenticate.domain.LoginUser;
import com.tiejun.ge.zero.system.authenticate.holder.AuthenticationContextHolder;
import com.tiejun.ge.zero.system.authenticate.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: zero
 * @description: 系统登陆应用层
 * @author: getiejun
 * @create: 2024-12-06 21:09
 **/
@Component
@Slf4j
public class SysLoginApp {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private TokenService tokenService;

    public String login(UserLoginDTO userLoginDTO) {
        // TODO 验证码校验，用户名校验
        Authentication authenticate;
        try{
            // 使用springSecurity认证
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDTO.getUserName(),
                    userLoginDTO.getPassword());
            // 设置context holder，在UserDetailsServiceImpl.loadUserByUsername验证用户名密码
            AuthenticationContextHolder.setContextHolder(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authenticate = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        } catch (Exception e) {
            log.error(">>SysLoginApp login method error", e);
            throw new UserException(UserExceptionEnum.USER_LOGIN_ERROR);
        } finally {
            AuthenticationContextHolder.clearContextHolder();
        }

        // 记录操作日志
        // 生成token 返回前端
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        return tokenService.createToken(loginUser);
    }
}

