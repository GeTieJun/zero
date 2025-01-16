package com.tiejun.ge.zero.system.authenticate.holder;

import org.springframework.security.core.Authentication;

/**
 * @program: zero
 * @description: 认证请求上下文holder
 * @author: getiejun
 * @create: 2024-12-07 16:33
 **/
public class AuthenticationContextHolder {

    private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal();

    public static Authentication getContextHolder() {
        return contextHolder.get();
    }

    public static void setContextHolder(Authentication context) {
        contextHolder.set(context);
    }

    public static void clearContextHolder() {
        contextHolder.remove();
    }

}
