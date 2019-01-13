package com.gao;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

public class TestStreamMatch {

    @Test
    public void testIntegerAllMatch() {
        Integer[] integers = {1, 2, 3, 4, 4, 5, 6, 7, 7};
        Stream<Integer> integerStream = Arrays.stream (integers);
//  數組中的所有元素都必須大於3，此時才為真
        boolean match = integerStream.allMatch (i -> i > 3);
        assert match : "some elements not matched";
        System.out.println (match);

    }

    @Test
    public void testAnyMatch() {
        Integer[] integers = {1, 2, 3, 4, 4, 5, 6, 7, 7};
        Stream<Integer> integerStream = Arrays.stream (integers);
        boolean match = integerStream.anyMatch (i -> i < 0);
        System.out.println (match);
    }

    @Test
    public void testNoMatch() {
        Integer[] integers = {1, 2, 3, 4, 4, 5, 6, 7, 7};
        Stream<Integer> integerStream = Arrays.stream (integers);
        boolean match = integerStream.noneMatch (i -> i < 0);
        System.out.println (match);
    }
}
