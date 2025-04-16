package com.tiejun.ge.zero.admin.app;

import com.tiejun.ge.zero.admin.domain.bo.SysUserBO;
import com.tiejun.ge.zero.admin.server.SysRoleServer;
import com.tiejun.ge.zero.admin.server.SysUserServer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

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

    public List<SysUserBO> list() {
        return sysUserServer.selectList(new SysUserBO());
    }

}
