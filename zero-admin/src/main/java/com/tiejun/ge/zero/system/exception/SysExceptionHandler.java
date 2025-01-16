package com.tiejun.ge.zero.system.exception;

import com.tiejun.ge.zero.common.exception.BaseException;
import com.tiejun.ge.zero.common.response.AjaxResult;
import com.tiejun.ge.zero.common.response.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: zero
 * @description: 系统异常处理
 * @author: getiejun
 * @create: 2024-12-07 18:59
 **/
@ControllerAdvice
@Slf4j
public class SysExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public AjaxResult BaseExceptionHandler(HttpServletRequest request, BaseException e){
        Map errorResult = new HashMap();
        errorResult.put("code", e.getCode());
        errorResult.put("message", e.getMessage());
        log.error("发生业务异常！原因是：{}", e.getMessage());
        return AjaxResult.error(errorResult);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public AjaxResult ExceptionHandler(HttpServletRequest request, Exception e){
        return AjaxResult.error(HttpStatus.ERROR, "系统处理异常，请联系管理员！");
    }

}
