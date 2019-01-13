package com.gao;

import com.gao.entity.Apple;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class TestStreamFind {
    @Test
    public void testStreamFind() {
        Integer[] integers = {1, 2, 3, 4, 4, 5, 6, 7, 7};
        Stream<Integer> integerStream = Arrays.stream (integers);
        Stream<Integer> stream = integerStream.filter (i -> i % 2 == 0);

        Optional<Integer> integerOptional = stream.findAny ();
        Integer integer = integerOptional.get ();
        System.out.println (integer);
        System.out.println ("==========================");
        integerStream = Arrays.stream (integers);
        Integer integer1 = integerStream.filter (i -> i > 10).findAny ().orElse (-1);
        System.out.println (integer1);

    }

    @Test
    public void testFind2() {
        Integer[] integers = {1, 2, 3, 4, 4, 5, 6, 7, 7};
        Stream<Integer> integerStream = Arrays.stream (integers);
        Map<Integer, Apple> appleMap = new HashMap<> ();
        Apple apple = new Apple ("red", 150L);
        Optional<Integer> optional = integerStream.filter (i -> i > 3).findAny ();
        Integer integer = optional.orElse (3);
        optional.ifPresent (i -> appleMap.put (i, apple));
        System.out.println (integer);
        Apple apple1 = appleMap.get (integer);
        System.out.println (apple1);

    }
}
