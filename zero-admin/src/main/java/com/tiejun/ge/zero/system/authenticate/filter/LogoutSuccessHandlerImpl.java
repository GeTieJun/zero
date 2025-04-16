package com.tiejun.ge.zero.system.authenticate.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.ContentType;
import com.tiejun.ge.zero.common.response.AjaxResult;
import com.tiejun.ge.zero.system.authenticate.domain.LoginUser;
import com.tiejun.ge.zero.system.authenticate.service.TokenService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @program: zero
 * @description: LogoutSuccessHandlerImpl
 * @author: getiejun
 * @create: 2025-04-16 21:33
 **/
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Resource
    private TokenService tokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (ObjectUtil.isNotEmpty(loginUser)) {
            tokenService.delLoginUser(loginUser.getToken());
        }
        response.setContentType(ContentType.JSON.getValue());
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        response.getWriter().println(AjaxResult.success());
    }
}