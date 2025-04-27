package com.tiejun.ge.zero.admin.controller;

import com.tiejun.ge.zero.admin.app.SysUserApp;
import com.tiejun.ge.zero.admin.app.SysUserQueryApp;
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

    @Resource
    private SysUserQueryApp sysUserQueryApp;

    @GetMapping("/page")
    public AjaxResult page(SysUserDTO sysUserDTO) {
        return AjaxResult.success(sysUserQueryApp.page(sysUserDTO));
    }

    @PostMapping("/delete")
    public AjaxResult delete(SysUserDTO sysUserDTO) {
        sysUserApp.delete(sysUserDTO);
        return AjaxResult.success();
    }

    @PostMapping("/update")
    public AjaxResult update(SysUserDTO sysUserDTO) {
        sysUserApp.update(sysUserDTO);
        return AjaxResult.success();
    }

    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysUserDTO sysUserDTO) {
        sysUserApp.add(sysUserDTO);
        return AjaxResult.success();
    }
}
