package com.tiejun.ge.zero.admin.controller;

import com.tiejun.ge.zero.admin.app.SysUserApp;
import com.tiejun.ge.zero.common.response.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: zero
 * @description: 系统用户控制层
 * @author: getiejun
 * @create: 2025-01-08 22:32
 **/
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Resource
    private SysUserApp sysUserApp;

//    @PreAuthorize("ss.hasPermit('system:user:list')")
    @PostMapping("/list")
    public AjaxResult list() {
        return AjaxResult.success(sysUserApp.list());
    }
}
