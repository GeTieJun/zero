package com.tiejun.ge.zero.common.enums.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DelFlagEnum {

    TRUE("1", "已删除"),

    FALSE("0", "未删除"),
    ;

    private String value;

    private String description;
}
