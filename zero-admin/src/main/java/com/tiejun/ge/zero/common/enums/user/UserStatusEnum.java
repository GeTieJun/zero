package com.tiejun.ge.zero.common.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatusEnum {

    ACTIVATE("0", "正常"),

    DEACTIVATE("1", "停用")
    ;
    private String value;

    private String description;
}
