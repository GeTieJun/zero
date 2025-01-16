package com.tiejun.ge.zero.common.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserTypeEnum {

    SYSTEM("00", "系统用户"),
    ;

    private String value;

    private String description;
}
