package com.tiejun.ge.zero.admin.domain.po;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tiejun.ge.zero.admin.domain.bo.SysUserBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * @program: zero
 * @description: 系统用户
 * @author: getiejun
 * @create: 2024-12-05 20:26
 **/
@Data
@TableName("sys_user")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity{

    public SysUser(SysUserBO sysUserBO) {
        BeanUtil.copyProperties(sysUserBO, this);
    }

    public SysUser(){}

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户类型 ("00:系统用户")
     */
    private String userType;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 用户性别(0:男|1:女|2:未知)
     */
    private String sex;

    /**
     * 用户头像地址
     */
    private String avatar;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户账户状态(0:正常|1:停用)
     */
    private String status;

    /**
     * 用户最后登录IP
     */
    private String loginIp;

    /**
     * 用户最后登录时间
     */
    private Date loginDate;

    /**
     * 备注
     */
    private String remark;

    public SysUserBO toBO() {
        return BeanUtil.copyProperties(this, SysUserBO.class);
    }
}
