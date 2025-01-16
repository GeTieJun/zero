package com.tiejun.ge.zero.common.exception.module;

import com.tiejun.ge.zero.common.exception.BaseException;
import com.tiejun.ge.zero.common.exception.enums.BaseExceptionEnum;

/**
 * @program: zero
 * @description: 用户业务异常处理
 * @author: getiejun
 * @create: 2024-12-06 19:02
 **/
public class UserException extends BaseException {

    public UserException(BaseExceptionEnum exceptionEnum) {
        super("zero.user", exceptionEnum.getCode(), exceptionEnum.getMessage());
    }
}
