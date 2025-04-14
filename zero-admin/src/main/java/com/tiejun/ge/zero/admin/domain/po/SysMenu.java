package com.tiejun.ge.zero.admin.domain.po;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tiejun.ge.zero.admin.domain.bo.SysMenuBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @program: zero
 * @description: 系统菜单基本类
 * @author: getiejun
 * @create: 2025-01-05 21:42
 **/
@Data
@TableName("sys_menu")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysMenu extends BaseEntity{

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 路由参数
     */
    private String query;

    /**
     * 路由名称
     */
    private String routeName;

    /**
     * 是否为外链（0是 1否）
     */
    private String isFrame;

    /**
     * 是否缓存（0缓存 1不缓存）
     */
    private String isCache;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    private String menuType;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private String visible;

    /**
     * 菜单状态（0正常 1停用）
     */
    private String status;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;

    public SysMenuBO toBO(){
        return BeanUtil.copyProperties(this, SysMenuBO.class);
    }

    public SysMenu (SysMenuBO sysMenuBO) {
        BeanUtil.copyProperties(sysMenuBO, this);
    }

}
