package com.gao.method;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class SelfCollector<T> implements Collector<T, List<T>, List<T>> {
    /**
     * 提供者
     *
     * @return
     */
    @Override
    public Supplier<List<T>> supplier() {
        System.out.println (Thread.currentThread ().getName () + "执行supplier");
        return ArrayList::new;
    }

    /**
     * 累加器
     *
     * @return
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        System.out.println (Thread.currentThread ().getName () + "执行 accumulator");
        return List::add;
    }

    /**
     * 合成仪
     *
     * @return
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        System.out.println (Thread.currentThread ().getName () + "执行 combiner");
        return (list1, list2) -> {
            list1.addAll (list2);
            return list1;
        };
    }

    /**
     * 终结者
     *
     * @return
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        System.out.println (Thread.currentThread ().getName () + "执行 finisher");
        return Function.identity ();
    }

    /**
     * 特征值
     *
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        System.out.println (Thread.currentThread ().getName () + "执行 characteristics");
        return Collections.unmodifiableSet (EnumSet.of (Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }
}
