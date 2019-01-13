package com.gao.service;

@FunctionalInterface
public interface ThreeFunction<T, U, K, R> {
    R apply(T t, U u, K k);
}
