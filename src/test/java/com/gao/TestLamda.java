package com.gao;

import com.gao.entity.Apple;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TestLamda {
    public static List<Apple> apples = null;

    private void testByTicket(AtomicInteger j) throws InterruptedException {
        new Thread (new Runnable () {
            @Override
            public void run() {
                byTicket (j);
            }

        }).start ();

        new Thread (() -> {
            byTicket (j);
        }).start ();
        Thread.currentThread ().join ();
    }

    private void byTicket(AtomicInteger j) {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep (1L);
                int i = j.get ();
                System.out.println (Thread.currentThread ().getName () + "正在打印票，打印第：" + (i) + "張票");
                i--;
                j.set (i);
                if (i <= 1) {
                    System.out.println (Thread.currentThread ().getName () + "票已售罄！");
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
        }
    }

    @Test
    public void test1() throws InterruptedException {
        AtomicInteger j = new AtomicInteger (10);
        testByTicket (j);
    }

    @Test
    public void test2() {
        Function<String, Object> stringConsumer = (String s) -> s.toString ();
        Object object = stringConsumer.apply ("你你好");
        System.out.println (object);
        Map<String, Object> map = new HashMap<> ();
        BiFunction<String, String, Map<String, Object>> stringStringObjectBiFunction = (k, v) -> {
            map.put (k, v);
            return map;
        };
        stringStringObjectBiFunction.apply ("name", "小狗");
        stringStringObjectBiFunction.apply ("age", "3");
        System.out.println (map);
    }

    @Test
    public void test3() {
        Runnable runnable = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println ("for循環" + i + "次！");
            }
        };

    }

    @Before
    public void setUp() throws Exception {
        apples = Arrays.asList (new Apple ("red", 170L), new Apple ("green", 120L), new Apple ("yellow", 150L));
    }
}
