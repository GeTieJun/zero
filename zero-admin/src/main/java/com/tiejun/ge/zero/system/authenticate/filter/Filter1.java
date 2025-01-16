package com.tiejun.ge.zero.system.authenticate.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: zero
 * @description: JWT认证过滤器
 * @author: getiejun
 * @create: 2024-11-17 22:42
 **/
@Slf4j
@Component
public class Filter1 extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // todo token认证filter
        log.info("filter1开始认证....");
        filterChain.doFilter(request, response);
    }
}
