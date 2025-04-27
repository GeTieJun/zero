package com.tiejun.ge.zero.admin.app;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tiejun.ge.zero.admin.domain.bo.SysUserBO;
import com.tiejun.ge.zero.admin.domain.dto.SysUserDTO;
import com.tiejun.ge.zero.admin.server.SysUserServer;
import com.tiejun.ge.zero.common.config.SystemConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private SystemConfig systemConfig;

    public IPage<SysUserBO> page(SysUserDTO sysUserDTO) {
        return sysUserServer.page(sysUserDTO.getPageNum(), sysUserDTO.getPageSize() , sysUserDTO.toBO());
    }

    public void delete(SysUserDTO sysUserDTO) {
        SysUserBO sysUserBO = sysUserDTO.toBO();
        sysUserServer.deleteById(sysUserBO.getId());
    }

    public void update(SysUserDTO sysUserDTO) {
        sysUserServer.updateById(sysUserDTO.toBO());
    }

    public void add(SysUserDTO sysUserDTO) {
        SysUserBO sysUserBO = sysUserDTO.toBO();
        sysUserBO.setPassword(getInitEncoderPassword());
        sysUserServer.insert(sysUserBO);
    }

    /**
     * 获取初始化加密密码
     * @return 返回加密密码
     */
    private String getInitEncoderPassword() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(systemConfig.getInitPassword());
    }
}
