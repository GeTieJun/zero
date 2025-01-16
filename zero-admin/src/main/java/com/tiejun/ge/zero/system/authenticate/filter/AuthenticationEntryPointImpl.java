package com.tiejun.ge.zero.system.authenticate.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import com.tiejun.ge.zero.common.response.AjaxResult;
import com.tiejun.ge.zero.common.response.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * @program: zero
 * @description: 认证失败过滤器
 * @author: getiejun
 * @create: 2024-11-17 22:34
 **/
@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.error(">>AuthenticationEntryPointImpl commence method. the request authentication failed. the uri is:[{}]", request.getRequestURI());
        String responseMsg = StrUtil.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        response.setContentType(ContentType.JSON.getValue());
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        response.getWriter().println(AjaxResult.error(HttpStatus.UNAUTHORIZED, responseMsg));
    }
}
