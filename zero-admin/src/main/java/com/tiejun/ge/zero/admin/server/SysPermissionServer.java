package com.tiejun.ge.zero.admin.server;

import java.util.Set;

public interface SysPermissionServer {

    Set<String> selectPermsByUserId(Long id);
}
