package com.tiejun.ge.zero.system.exception;

import com.tiejun.ge.zero.common.exception.BaseException;
import com.tiejun.ge.zero.common.response.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program: zero
 * @description: 系统异常处理
 * @author: getiejun
 * @create: 2024-12-07 18:59
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 处理参数校验异常
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBindException(BindException ex) {
        Map<String, String> errors = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }

    // 处理 URL 匹配异常
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", "请求的 URL 不存在: " + ex.getRequestURL());
        return error;
    }

    // 处理请求参数注入异常
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", "缺少必要的请求参数: " + ex.getParameterName());
        return error;
    }

    // 处理请求参数类型不匹配异常
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", "请求参数类型不匹配: " + ex.getName());
        return error;
    }

    // 处理请求体解析异常
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", "请求体解析失败，请检查请求体格式");
        return error;
    }

    // 处理 validation 异常
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(propertyPath, message);
        }
        return errors;
    }

    // 处理其他未明确处理的异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", "服务器内部错误: " + ex.getMessage());
        return error;
    }

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public AjaxResult BaseExceptionHandler(HttpServletRequest request, BaseException e){
        Map errorResult = new HashMap();
        errorResult.put("code", e.getCode());
        errorResult.put("message", e.getMessage());
        log.error("发生业务异常！原因是：{}", e.getMessage());
        return AjaxResult.error(errorResult);
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public AjaxResult ExceptionHandler(HttpServletRequest request, Exception e){
//        log.error("发生业务异常！原因是：{}", e.getMessage());
//        return AjaxResult.error(com.tiejun.ge.zero.common.response.HttpStatus.ERROR, "系统处理异常，请联系管理员！");
//    }

}
