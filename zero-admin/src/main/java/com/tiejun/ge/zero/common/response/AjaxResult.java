package com.tiejun.ge.zero.common.response;

import cn.hutool.core.util.ObjectUtil;

import java.util.HashMap;

/**
 * @program: zero
 * @description: ajax响应体
 * @author: getiejun
 * @create: 2024-11-18 23:11
 **/
public class AjaxResult extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public static final String CODE = "code";

    public static final String MSG = "msg";

    public static final String DATA = "data";

    public AjaxResult () {}

    /**
     * 返回ajax对象
     * @param code 请求code
     * @param msg 请求message
     */
    public AjaxResult(int code, String msg) {
        super.put(CODE, code);
        super.put(MSG, msg);
    }

    /**
     * 返回ajax对象
     * @param code 请求 code
     * @param msg 请求 message
     * @param data 请求返回数据
     */

    public AjaxResult(int code, String msg, Object data) {
        super.put(CODE, code);
        super.put(MSG, msg);
        if (ObjectUtil.isNotNull(data)) {
            super.put(DATA, data);
        }
    }

    public static AjaxResult success () {
        return new AjaxResult(HttpStatus.SUCCESS, "操作成功");
    }

    public static AjaxResult success (Object data) {
        return new AjaxResult(HttpStatus.SUCCESS, "操作成功", data);
    }

    public static AjaxResult error (Object data) {
        return new AjaxResult(HttpStatus.ERROR, "操作失败", data);
    }

    public static AjaxResult error (int code, String msg) {
        return new AjaxResult(code, msg);
    }

}
