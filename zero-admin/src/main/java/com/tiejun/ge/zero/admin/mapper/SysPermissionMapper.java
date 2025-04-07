package com.tiejun.ge.zero.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

@Mapper
public interface SysPermissionMapper {

    Set<String> selectPermsByUserId(@Param("userId") Long id);

    Set<String> selectSysRoleByUserId(@Param("userId") Long userId);
}
