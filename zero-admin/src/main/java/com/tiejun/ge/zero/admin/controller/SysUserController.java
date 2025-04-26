package com.tiejun.ge.zero.admin.controller;

import com.tiejun.ge.zero.admin.app.SysUserApp;
import com.tiejun.ge.zero.admin.domain.dto.SysUserDTO;
import com.tiejun.ge.zero.common.response.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: zero
 * @description: 系统用户控制层
 * @author: getiejun
 * @create: 2025-01-08 22:32
 **/
@RestController
@RequestMapping("/system/user")
public class SysUserController {

    @Resource
    private SysUserApp sysUserApp;

    @GetMapping("/page")
    public AjaxResult page(SysUserDTO sysUserDTO) {
        return AjaxResult.success(sysUserApp.page(sysUserDTO));
    }
}
