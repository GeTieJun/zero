package com.tiejun.ge.zero.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @program: zero
 * @description: 系统配置类
 * @author: getiejun
 * @create: 2025-04-27 22:46
 **/
@Configuration
@Data
public class SystemConfig {

    /**
     * 用户默认密码
     */
    @Value("${system.user.initPassword:admin123}")
    private String initPassword;
}
