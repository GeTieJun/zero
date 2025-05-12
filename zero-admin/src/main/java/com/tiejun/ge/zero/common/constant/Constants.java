package com.tiejun.ge.zero.common.constant;

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

    public class Common {
        /**
         * UTF-8 字符集
         */
        public static final String UTF8 = "UTF-8";

        /**
         * GBK 字符集
         */
        public static final String GBK = "GBK";


        /**
         * www主域
         */
        public static final String WWW = "www.";

        /**
         * http请求
         */
        public static final String HTTP = "http://";

        /**
         * https请求
         */
        public static final String HTTPS = "https://";
    }

    public class DataBase {

        public static final String NOT_DELETE = "0";
    }

    public class Log {
        public static final String LOG_MDC_ID = "trace_id";
    }
}
