package com.tiejun.ge.zero.system.authenticate;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.tiejun.ge.zero.system.authenticate.domain.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @program: zero
 * @description: 权限控制服务层
 * @author: getiejun
 * @create: 2025-01-08 22:35
 **/
@Service("ss")
public class PermissionService {

    /**
     * 验证用户是否具备某中条件
     * @param permission 权限
     * @return true|false
     */
    public boolean hasPermit(String permission) {
        String[] permits = permission.split("\\|");
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getDetails();

        if(ObjectUtil.isNull(loginUser) || CollUtil.isEmpty(loginUser.getPermissions())) {
            return false;
        }

        if(loginUser.getPermissions().contains("*:*:*")) {
            return true;
        }

        for (String permit : permits) {
            if(loginUser.getPermissions().contains(StrUtil.trim(permit))) {
                return true;
            }
        }

        return false;
    }
}
