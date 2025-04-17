package com.tiejun.ge.zero.common.config.mybatis;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.springframework.stereotype.Component;

/**
 * @program: zero
 * @description: mybatis自定义id
 * @author: getiejun
 * @create: 2025-04-17 22:27
 **/
@Component
public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public Long nextId(Object entity) {
        // 返回雪花id
        return IdUtil.getSnowflakeNextId();
    }
}
