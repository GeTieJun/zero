package com.tiejun.ge.zero.admin.domain.dto;

import lombok.Data;

/**
 * @program: zero
 * @description: 用户登录DTO
 * @author: getiejun
 * @create: 2024-12-06 21:06
 **/
@Data
public class UserLoginDTO {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;
}
