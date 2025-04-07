package com.tiejun.ge.zero.common.utils;

import java.util.function.BiConsumer;

/**
 * @program: zero
 * @description: BeanBuilder
 * @author: getiejun
 * @create: 2025-04-05 22:26
 **/
public class BeanBuilder<T> {

    private final T bean;

    public BeanBuilder(Class<T> beanClass) {
        try {
            this.bean = beanClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate bean", e);
        }
    }

    public static <T> BeanBuilder<T> of(Class<T> beanClass) {
        return new BeanBuilder<>(beanClass);
    }

    public <V> BeanBuilder<T> with(BiConsumer<T, V> setter, V value) {
        setter.accept(bean, value);
        return this;
    }

    public T build() {
        return bean;
    }
}
