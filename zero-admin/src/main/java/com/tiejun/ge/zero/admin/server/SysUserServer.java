package com.tiejun.ge.zero.admin.server;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tiejun.ge.zero.admin.domain.bo.SysUserBO;

import java.util.List;

public interface SysUserServer {

    /**
     * 根据id进行更新数据
     * @param sysUserBO
     * @return 更新行数
     */
    int updateById(SysUserBO sysUserBO);

    /**
     * 插入数据
     * @param sysUserBO
     * @return 插入行数
     */
    int insert(SysUserBO sysUserBO);

    /**
     * 根据id进行删除
     * @param id id
     * @return 删除行数
     */
    int deleteById(Long id);

    /**
     * 根据条件查询，返回list集合
     * @param sysUserBO
     * @return List<SysUserBO>
     */
    List<SysUserBO> selectList(SysUserBO sysUserBO);

    /**
     * 根据id进行查询
     * @param id id
     * @return SysUserBO
     */
    SysUserBO selectById(Long id);

    /**
     * 根据用户名查询用户详细信息
     * @param userName 用户名
     * @return SysUserBO
     */
    SysUserBO detailByUserName(String userName);

    /**
     * 分页查询
     * @param pageNum 页数
     * @param pageSize 页大小
     * @param sysUserBO 条件BO
     * @return IPage
     */
    IPage<SysUserBO> page(int pageNum, int pageSize, SysUserBO sysUserBO);
}
