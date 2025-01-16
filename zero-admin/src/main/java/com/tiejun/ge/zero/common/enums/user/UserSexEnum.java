package com.tiejun.ge.zero.common.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserSexEnum {

    MAN("0", "男"),

    FEMALE("1", "女"),

    UNKNOWN("2", "未知")

    ;

    private String value;

    private String description;
}
