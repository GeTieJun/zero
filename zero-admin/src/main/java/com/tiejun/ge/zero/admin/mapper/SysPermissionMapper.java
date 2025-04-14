package com.tiejun.ge.zero.admin.mapper;

import com.tiejun.ge.zero.admin.domain.bo.SysMenuBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface SysPermissionMapper {

    Set<String> selectPermsByUserId(@Param("userId") Long id);

    Set<String> selectSysRoleByUserId(@Param("userId") Long userId);

    List<SysMenuBO> selectAllSysMenu();

    List<SysMenuBO> selectSysMenuByUserId(@Param("userId") Long userId);
}
