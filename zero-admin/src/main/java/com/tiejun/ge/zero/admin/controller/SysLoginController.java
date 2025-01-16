package com.tiejun.ge.zero.admin.controller;

import com.tiejun.ge.zero.admin.app.SysLoginApp;
import com.tiejun.ge.zero.admin.domain.dto.UserLoginDTO;
import com.tiejun.ge.zero.common.response.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: zero
 * @description: 系统登录控制层
 * @author: getiejun
 * @create: 2024-12-06 21:05
 **/
@RestController
public class SysLoginController {


    @Resource
    private SysLoginApp sysLoginApp;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody UserLoginDTO userLoginDTO) {
        AjaxResult ajaxResult = AjaxResult.success();
        String token = sysLoginApp.login(userLoginDTO);
        ajaxResult.put("token", token);
        return ajaxResult;
    }

}
