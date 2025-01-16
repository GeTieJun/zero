package com.tiejun.ge.zero.admin.domain.po;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tiejun.ge.zero.admin.domain.bo.SysRoleBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @program: zero
 * @description: 系统角色
 * @author: getiejun
 * @create: 2024-12-05 21:50
 **/
@Data
@TableName("sys_role")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseEntity{

    public SysRole(SysRoleBO sysRoleBO) {
        BeanUtil.copyProperties(sysRoleBO, this);
    }

    private Long roleId;

    private String roleName;

    private String roleKey;

    private Integer roleSort;

    private String dataScope;

    private String status;

    public SysRoleBO toBO(){
        return BeanUtil.copyProperties(this, SysRoleBO.class);
    }

}
