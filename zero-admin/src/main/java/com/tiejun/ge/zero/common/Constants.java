package com.tiejun.ge.zero.common;

/**
 * @program: zero
 * @description: 常量
 * @author: getiejun
 * @create: 2024-12-16 22:06
 **/
public class Constants {

    /**
     *
     * 认证
     */
    public class Authentication {

        /**
         * 令牌前缀
         */
        public static final String TOKEN_PREFIX = "Bearer";

        /**
         * 令牌
         */
        public static final String TOKEN = "token";

        /**
         * 令牌前缀
         */
        public static final String LOGIN_USER_KEY = "login_user_key";
    }

    public class CacheConstants {
        /**
         * 登录用户 redis key
         */
        public static final String LOGIN_TOKEN_KEY = "login_tokens:";
    }
}
