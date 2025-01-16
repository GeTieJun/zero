package com.tiejun.ge.zero.admin.domain.bo;

import lombok.Data;

/**
 * @program: zero
 * @description: 系统角色BO
 * @author: getiejun
 * @create: 2024-12-05 21:50
 **/
@Data
public class SysRoleBO {

    private Long roleId;

    private String roleName;

    private String roleKey;

    private Integer roleSort;

    private String dataScope;

    private String status;
}
