package com.tiejun.ge.zero.common.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.tiejun.ge.zero.admin.domain.bo.SysUserBO;
import com.tiejun.ge.zero.admin.domain.po.BaseEntity;
import com.tiejun.ge.zero.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

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
            if (metaObject.getOriginalObject() instanceof BaseEntity) {
                // 获取当前用户
                SysUserBO sysUserBO = SecurityUtils.getLoginUser().getUser();
                metaObject.setValue("createBy", sysUserBO.getId());
                metaObject.setValue("createTime", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
                metaObject.setValue("updateBy", sysUserBO.getId());
                metaObject.setValue("updateTime", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            }
        } catch (Exception e) {
            log.error(">>MyMetaObjectHandler insertFill method. insert fill error.", e);
//            todo throw new BaseException()
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            if (metaObject.getOriginalObject() instanceof BaseEntity) {
                // 获取当前用户
                SysUserBO sysUserBO = SecurityUtils.getLoginUser().getUser();
                metaObject.setValue("updateBy", sysUserBO.getId());
                metaObject.setValue("updateTime", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            }
        } catch (Exception e) {
            log.error(">>MyMetaObjectHandler updateFill method. update fill error.", e);
            //            todo throw new BaseException()
        }
    }
}
