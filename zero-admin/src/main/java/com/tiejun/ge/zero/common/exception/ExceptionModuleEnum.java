package com.tiejun.ge.zero.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionModuleEnum {

    USER("user"),
    ;

    private String module;
}
