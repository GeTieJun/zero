package com.tiejun.ge.zero.common.exception.module;

import com.tiejun.ge.zero.common.exception.BaseException;
import com.tiejun.ge.zero.common.exception.enums.SystemExceptionEnum;

/**
 * @program: zero
 * @description: 系统异常
 * @author: getiejun
 * @create: 2025-04-06 21:10
 **/
public class SystemException extends BaseException {

    public SystemException(SystemExceptionEnum systemExceptionEnum) {
        super("zero.system", systemExceptionEnum.getCode(), systemExceptionEnum.getMessage());
    }
}
