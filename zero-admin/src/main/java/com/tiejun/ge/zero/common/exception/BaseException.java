package com.tiejun.ge.zero.common.exception;

/**
 * @program: zero
 * @description: 基础异常信息
 * @author: getiejun
 * @create: 2024-12-06 16:51
 **/
public class BaseException extends RuntimeException{

    private String module;

    private String code;

    private String message;

    public BaseException(String module, String code, String message) {
        this.module = module;
        this.code = code;
        this.message = message;
    }

    public BaseException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getModule() {
        return module;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
