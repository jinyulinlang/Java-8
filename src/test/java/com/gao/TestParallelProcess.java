package com.gao;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

public class TestParallelProcess {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testParallelCpu() {
        System.out.println (Runtime.getRuntime ().availableProcessors ());

    }

    @Test
    public void testInterate() {
        Long reduce = Stream.iterate (1L, i -> i + 1).limit (6).reduce (0L, Long::sum);
        System.out.println (reduce);

    }
}
