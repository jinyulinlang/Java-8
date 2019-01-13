package com.gao;

import com.gao.method.SelfCollector;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

public class TestSelfCollector {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testSelfCollector() {
//        testParralo1 ();
        testParralo2 ();
    }

    private void testParralo1() {
        Collector<String, List<String>, List<String>> collector = new SelfCollector<> ();
        String[] arrs = new String[]{"小明", "小王", "小黄", "小兰", "hello", "的点点滴滴多多"};
        List<String> collect = Arrays.stream (arrs).filter (s -> s.length () > 3).collect (collector);
        System.out.println (collect);
    }

    private void testParralo2() {
        Collector<String, List<String>, List<String>> collector = new SelfCollector<> ();
        String[] arrs = new String[]{"小明", "小王", "小黄", "小兰", "hello", "的点点滴滴多多"};
        List<String> collect = Arrays.asList (arrs).parallelStream ().filter (s -> s.length () > 3).collect (collector);
        System.out.println (collect);
    }
}
