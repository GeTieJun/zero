package com.tiejun.ge.zero.admin.domain.po;

import lombok.Data;

import java.util.Date;

/**
 * @program: zero
 * @description: 基础类
 * @author: getiejun
 * @create: 2024-12-05 21:43
 **/
@Data
public class BaseEntity {

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;


    /**
     * 删除标志
     */
    private String delFlag;
}
