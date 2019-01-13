package com.gao;

import com.gao.entity.Apple;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.*;

public class TestApple {

    List<Apple> apples = null;

    @Before
    public void setUp() throws Exception {
        apples = Arrays.asList (new Apple ("red", 150L), new Apple ("green", 170L), new Apple ("yellow", 120L));
    }

    @Test
    public void test1() {
        List<Apple> filterApples = filterApple (apples, a -> a.getWeight () > 130);
        System.out.println (filterApples);
        System.out.println ("========================");
        List<Apple> apples = filterApples (this.apples, w -> w < 150);
        System.out.println (apples);
    }

    private List<Apple> filterApple(List<Apple> apples, Predicate<Apple> predicate) {
        List<Apple> filterApples = new ArrayList<> (apples.size ());
        apples.forEach (apple -> {
            if (predicate.test (apple)) {
                filterApples.add (apple);
            }
        });
        return filterApples;
    }

    private List<Apple> filterApples(List<Apple> apples, LongPredicate longPredicate) {
        List<Apple> filterApples = new ArrayList<> (apples.size ());
        apples.forEach (apple -> {
            if (longPredicate.test (apple.getWeight ())) {
                filterApples.add (apple);
            }
        });
        return filterApples;
    }

    @Test
    public void test2() {
        List<Apple> apples = filterByBiPredicate (this.apples, (c, w) -> c.equals ("green") && w > 150);
        System.out.println (apples);

    }

    private List<Apple> filterByBiPredicate(List<Apple> apples, BiPredicate<String, Long> biPredicate) {
        List<Apple> filterApples = new ArrayList<> (apples.size ());
        apples.forEach (apple -> {
            if (biPredicate.test (apple.getColor (), apple.getWeight ())) {
                filterApples.add (apple);
            }
        });
        return filterApples;
    }

    @Test
    public void testConsumer() {
        Consumer<Apple> consumerApplers = a -> {
            a.setColor (a.getColor ().replace (a.getColor ().charAt (0), '*'));
            a.setWeight (a.getWeight () + 5);
        };
        List<Apple> apples = consumerApple (this.apples, consumerApplers);
        System.out.println (apples);
    }


    private List<Apple> consumerApple(List<Apple> apples, Consumer<Apple> consumer) {
        List<Apple> consumerApples = new ArrayList<> (apples.size ());
        apples.forEach (apple -> {
            consumer.accept (apple);
        });
        return apples;
    }

    @Test
    public void testBiConsumer() {
        Map<String, Object> map = new HashMap<String, Object> ();
        BiConsumer<List<Apple>, String> biConsumers = (a, s) -> map.put (s, a);
        List<Apple> apples = biconsumerApple (this.apples, "type", biConsumers);
        System.out.println (map.get ("type"));
    }

    private List<Apple> biconsumerApple(List<Apple> apples, String s, BiConsumer<List<Apple>, String> biConsumer) {
        biConsumer.accept (apples, s);
        return apples;
    }

    @Test
    public void testListToMap() {
        System.out.println (listToMap ());

    }

    private Map<String, Apple> listToMap() {
        Map<String, Apple> map = new HashMap<> ();
        BiConsumer<Apple, String> biConsumer = (a, s) -> map.put (s, a);
        listToMap (apples, biConsumer);
        return map;
    }

    private void listToMap(List<Apple> list, BiConsumer<Apple, String> biConsumer) {
        list.forEach (l -> biConsumer.accept (l, l.getColor ()));

    }


}





