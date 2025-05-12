package com.tiejun.ge.zero.common.domain;

/**
 * 参数校验组
 */
public interface ValidaGroup {

    interface Common {

        /**
         * 分页
         */
        interface Page {}

        /**
         * 插入
         */
        interface Insert {}

        /**
         * 更新
         */
        interface Update {}

        /**
         * 删除
         */
        interface Delete {}

        /**
         * 详情
         */
        interface Detail {}
    }
}
