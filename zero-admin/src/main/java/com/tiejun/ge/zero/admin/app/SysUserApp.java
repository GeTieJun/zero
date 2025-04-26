package com.tiejun.ge.zero.admin.app;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tiejun.ge.zero.admin.domain.bo.SysUserBO;
import com.tiejun.ge.zero.admin.domain.dto.SysUserDTO;
import com.tiejun.ge.zero.admin.server.SysRoleServer;
import com.tiejun.ge.zero.admin.server.SysUserServer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: zero
 * @description: 系统用户应用层
 * @author: getiejun
 * @create: 2025-01-04 22:24
 **/
@Component
public class SysUserApp {

    @Resource
    private SysUserServer sysUserServer;

    @Resource
    private SysRoleServer sysRoleServer;

    public IPage<SysUserBO> page(SysUserDTO sysUserDTO) {
        return sysUserServer.page(sysUserDTO.getPageNum(), sysUserDTO.getPageSize() , sysUserDTO.toBO());
    }
}
