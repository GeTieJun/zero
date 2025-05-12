package com.tiejun.ge.zero.common.config.mybatis;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.tiejun.ge.zero.admin.domain.bo.SysUserBO;
import com.tiejun.ge.zero.admin.domain.po.BaseEntity;
import com.tiejun.ge.zero.common.constant.Constants;
import com.tiejun.ge.zero.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

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
            if (ObjectUtil.isNotNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {

                BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
                // 获取当前用户
                SysUserBO sysUserBO = SecurityUtils.getLoginUser().getUser();
                if(ObjectUtil.isNotNull(sysUserBO)) {
                    baseEntity.setCreateBy(sysUserBO.getId());
                    baseEntity.setUpdateBy(sysUserBO.getId());
                }
                baseEntity.setCreateTime(new Date());
                baseEntity.setUpdateTime(new Date());
                baseEntity.setDelFlag(Constants.DataBase.NOT_DELETE);
            }
        } catch (Exception e) {
            log.error(">>MyMetaObjectHandler insertFill method. insert fill error.", e);
//            todo throw new BaseException()
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            if (ObjectUtil.isNotNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {

                BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
                // 获取当前用户
                SysUserBO sysUserBO = SecurityUtils.getLoginUser().getUser();
                if(ObjectUtil.isNotNull(sysUserBO)) {
                    baseEntity.setUpdateBy(sysUserBO.getId());
                }
                baseEntity.setUpdateTime(new Date());
            }
        } catch (Exception e) {
            log.error(">>MyMetaObjectHandler updateFill method. update fill error.", e);
            //            todo throw new BaseException()
        }
    }
}
