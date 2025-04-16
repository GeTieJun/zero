package com.tiejun.ge.zero.admin.controller;

import cn.hutool.core.map.MapUtil;
import com.tiejun.ge.zero.admin.app.SysLoginApp;
import com.tiejun.ge.zero.admin.app.SysMenuApp;
import com.tiejun.ge.zero.admin.app.SysUserApp;
import com.tiejun.ge.zero.admin.domain.dto.UserLoginDTO;
import com.tiejun.ge.zero.common.response.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

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

    @Resource
    private SysUserApp sysUserApp;

    @Resource
    private SysMenuApp sysMenuApp;

    /**
     * 登录接口
     * @param userLoginDTO
     * @return AjaxResult
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody UserLoginDTO userLoginDTO) {
        String token = sysLoginApp.login(userLoginDTO);
        return AjaxResult.success(MapUtil.createMap(HashMap.class).put("token", token));
    }

    /**
     * 获取用户 角色 权限信息
     * @return AjaxResult
     */
    @GetMapping("/getInfo")
    public AjaxResult getInfo() {
        return AjaxResult.success(sysLoginApp.getUserInfo());
    }

    /**
     * 获取用户对应的权限菜单信息
     * @return AjaxResult
     */
    @GetMapping("/getRouters")
    public AjaxResult getRouters() {
        return AjaxResult.success(sysLoginApp.getRouters());
    }

}
