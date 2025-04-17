package com.tiejun.ge.zero.common.config.mybatis;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.tiejun.ge.zero.admin.domain.bo.SysUserBO;
import com.tiejun.ge.zero.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @program: zero
 * @description: 自动填充字段
 * @author: getiejun
 * @create: 2025-04-17 21:44
 **/
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            // 获取当前用户
            SysUserBO sysUserBO = SecurityUtils.getLoginUser().getUser();
            if (ObjectUtil.isNotNull(metaObject) && ObjectUtil.isNotNull(sysUserBO)) {
                this.strictInsertFill(metaObject, "create_by", Long.class, sysUserBO.getId());
                this.strictInsertFill(metaObject, "create_time", LocalDateTime.class, LocalDateTime.now());
                this.strictInsertFill(metaObject, "update_by", Long.class, sysUserBO.getId());
                this.strictInsertFill(metaObject, "update_time", LocalDateTime.class, LocalDateTime.now());
            }
        } catch (Exception e) {
            log.error(">>MyMetaObjectHandler insertFill method. insert fill error.", e);
//            throw new BaseException()
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            // 获取当前用户
            SysUserBO sysUserBO = SecurityUtils.getLoginUser().getUser();
            if (ObjectUtil.isNotNull(metaObject) && ObjectUtil.isNotNull(sysUserBO)) {
                this.strictInsertFill(metaObject, "update_by", Long.class, sysUserBO.getId());
                this.strictInsertFill(metaObject, "update_time", LocalDateTime.class, LocalDateTime.now());
            }
        } catch (Exception e) {
            log.error(">>MyMetaObjectHandler updateFill method. update fill error.", e);
        }
    }
}
