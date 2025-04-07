package com.tiejun.ge.zero.common.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemExceptionEnum implements BaseExceptionEnum {

    PARAM_ERROR("0000100", "参数错误！"),
    ;

    private String code;

    private String message;
}
