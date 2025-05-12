package com.tiejun.ge.zero.system.interceptor;

import com.tiejun.ge.zero.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @program: zero
 * @description: 日志拦截器
 * @author: getiejun
 * @create: 2025-05-12 22:10
 **/
@Slf4j
@Component
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在请求处理之前执行的逻辑
        //添加MDC值
        MDC.put(Constants.Log.LOG_MDC_ID, UUID.randomUUID().toString().replace("-", ""));
        //打印接口请求信息
        String method = request.getMethod();
        String uri = request.getRequestURI();
        log.info("[请求接口] : {} : {}", method, uri);
        //打印请求参数
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info(">>>Post-handle logic");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //删除MDC值
        MDC.remove(Constants.Log.LOG_MDC_ID);
    }
}
