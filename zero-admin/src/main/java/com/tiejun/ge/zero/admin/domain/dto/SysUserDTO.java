package com.tiejun.ge.zero.admin.domain.dto;

import cn.hutool.core.bean.BeanUtil;
import com.tiejun.ge.zero.admin.domain.bo.SysUserBO;
import com.tiejun.ge.zero.common.domain.FacadeDTO;
import lombok.Data;

import java.util.Date;

/**
 * @program: zero
 * @description: SysUserDTO
 * @author: getiejun
 * @create: 2025-04-21 22:15
 **/
@Data
public class SysUserDTO implements FacadeDTO {

    /**
     * 页数
     */
    private int pageNum;

    /**
     * 页大小
     */
    private int pageSize;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 用户状态
     */
    private String status;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 起始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户性别(0:男|1:女|2:未知)
     */
    private String sex;

    @Override
    public SysUserBO toBO() {
        return BeanUtil.copyProperties(this, SysUserBO.class);
    }
}
