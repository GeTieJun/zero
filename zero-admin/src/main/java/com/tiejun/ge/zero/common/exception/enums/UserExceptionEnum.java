package com.tiejun.ge.zero.common.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: zero
 * @description: 用户异常枚举
 * @author: getiejun
 * @create: 2024-12-06 19:06
 **/
@AllArgsConstructor
@Getter
public enum UserExceptionEnum implements BaseExceptionEnum {

    USER_NOT_FOUND("0001100", "用户不存在"),

    USER_STATUS_NOT_USE("0001101", "用户状态不可用"),

    USER_HAS_DELETED("0001102", "用户已被删除"),

    USER_PASSWORD_ERROR("0001103", "用户密码错误"),

    USER_LOGIN_ERROR("0001104", "用户登录失败"),

    USER_INFO_ERROR("0001105", "获取用户账户异常")
    ;

    private String code;

    private String message;
}
