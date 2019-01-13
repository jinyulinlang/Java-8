package com.gao;

import com.alibaba.fastjson.JSONObject;
import com.gao.entity.Apple;
import com.gao.entity.Dish;
import com.gao.entity.Dog;
import org.apache.commons.lang.math.NumberUtils;
import org.junit.Before;
import org.junit.Test;

import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TestCollector {

    List<Apple> apples = null;
    List<Dog> dogs;
    List<Dish> menu;

    @Before
    public void setUp() throws Exception {
        dogs = new TestFunction ().listDog ();
        apples = Arrays.asList (new Apple ("red", 150L), new Apple ("green", 170L), new Apple ("green", 120L), new Apple ("yellow", 120L), new Apple ("yellow", 123L), new Apple ("yellow", 126L));
        menu = Arrays.asList (new Dish ("pork", false, 800, Dish.Type.MEAT), new Dish ("beef", false, 700, Dish.Type.MEAT), new Dish ("chicken", false, 400, Dish.Type.MEAT), new Dish ("french fries", true, 530, Dish.Type.OTHER), new Dish ("rice", true, 350, Dish.Type.OTHER), new Dish ("season fruit", true, 120, Dish.Type.OTHER), new Dish ("pizza", true, 550, Dish.Type.OTHER), new Dish ("prawns", false, 300, Dish.Type.FISH), new Dish ("salmon", false, 450, Dish.Type.FISH));
    }

    @Test
    public void test1() {
        List<Apple> greenApples = apples.stream ().filter (a -> a.getColor ().equals ("green")).collect (Collectors.toList ());
        Optional.ofNullable (greenApples).ifPresent (System.out::println);
        Optional.ofNullable (groupByColor (apples)).ifPresent (System.out::println);
    }

    private Map<String, List<Apple>> groupByColor(List<Apple> apples) {
        Map<String, List<Apple>> map = new HashMap<String, List<Apple>> ();
        for (Apple apple : apples) {
            List list = map.getOrDefault (apple.getColor (), new ArrayList<Apple> ());
            map.put (apple.getColor (), list);
            list.add (apple);

        }
        return map;
    }

    @Test
    public void testGroupByFunction() {
        Optional.ofNullable (groupByFunction (apples)).ifPresent (System.out::println);

    }

    private Map<String, List<Apple>> groupByFunction(List<Apple> apples) {
        Map<String, List<Apple>> map = new HashMap<> ();
        apples.forEach (a -> {
            List<Apple> colorApples = map.getOrDefault (a.getColor (), new ArrayList<Apple> ());
            colorApples.add (a);
            map.put (a.getColor (), colorApples);
        });
        return map;
    }

    @Test
    public void testGroupByCollect() {
        Optional.ofNullable (groupByCollect (apples)).ifPresent (m -> {
            System.out.println (JSONObject.toJSONString (m));
        });

    }

    private Map<String, List<Apple>> groupByCollect(List<Apple> apples) {
        Map<String, List<Apple>> m = apples.stream ().collect (Collectors.groupingBy (Apple::getColor));
        return m;
    }

    @Test
    public void testCollectByDog() {
        Map<String, List<Dog>> map = Optional.ofNullable (groupByCollectDog (dogs)).orElse (new HashMap<> ());
        System.out.println (JSONObject.toJSONString (map));
        List<Dog> dogs = map.get ("3");
        System.out.println ("===================");
        System.out.println (dogs);
    }

    private Map<String, List<Dog>> groupByCollectDog(List<Dog> dogs) {
        return dogs.stream ().collect (Collectors.groupingBy (Dog::getId));
    }

    @Test
    public void testToMapByDogId() {
        Optional.ofNullable (toMapDogById (dogs)).ifPresent (m -> System.out.println (JSONObject.toJSONString (m)));
    }

    private Map<String, Dog> toMapDogById(List<Dog> dogs) {
        Map<String, Dog> dogMap = new HashMap<> (dogs.size ());
        Optional.ofNullable (dogs).ifPresent (ds -> ds.forEach (d -> dogMap.put (d.getId (), d)));
        return dogMap;
    }

    @Test
    public void testGroupingByConcuurentWithFunction() {

        ConcurrentMap<Dish.Type, List<Dish>> collect = menu.stream ().collect (Collectors.groupingByConcurrent (Dish::getType));
        Optional.ofNullable (collect.getClass ()).ifPresent (System.out::println);
        Optional.ofNullable (collect).ifPresent (System.out::println);

    }

    @Test
    public void testGroupingByConcuurentWithFunctionAndCollect() {
        ConcurrentMap<Dish.Type, Double> collect = menu.stream ().collect (Collectors.groupingByConcurrent (Dish::getType, Collectors.averagingDouble (Dish::getCalories)));
        Optional.ofNullable (collect).ifPresent (System.out::println);
    }

    @Test
    public void testGroupingByConcuurentWithFunctionAndSuplierAndCollect() {
        Map<Dish.Type, Double> collect = menu.stream ().collect (Collectors.groupingByConcurrent (Dish::getType, ConcurrentSkipListMap::new, Collectors.averagingDouble (Dish::getCalories)));
        Optional.ofNullable (collect.getClass ()).ifPresent (System.out::println);
        Optional.ofNullable (collect).ifPresent (System.out::println);
    }

    @Test
    public void testJoining() {
        String collect = menu.stream ().map (Dish::getName).collect (Collectors.joining ());
        Optional.ofNullable (collect).ifPresent (System.out::println);

    }

    @Test
    public void testJoiningDelimiter() {
        String collect = menu.stream ().map (Dish::getName).collect (Collectors.joining (","));
        Optional.ofNullable (collect.getClass ()).ifPresent (System.out::println);
        Optional.ofNullable (collect).ifPresent (System.out::println);

    }

    @Test
    public void testJoiningDelimiterWithPrefixAndSuffix() {
        String collect = menu.stream ().map (Dish::getName).collect (Collectors.joining (",", "Names[", "]"));
        Optional.ofNullable (collect.getClass ()).ifPresent (System.out::println);
        Optional.ofNullable (collect).ifPresent (System.out::println);

    }

    @Test
    public void testMapping() {
        String collect = menu.stream ().collect (Collectors.mapping (Dish::getName, Collectors.joining (",")));
        Optional.ofNullable (collect).ifPresent (System.out::println);


    }

    @Test
    public void testMaxBy() {
        Optional<Dish> collect = menu.stream ().collect (Collectors.maxBy (Comparator.comparing (Dish::getCalories)));
        collect.ifPresent (System.out::println);
        menu.stream ().collect (Collectors.minBy (Comparator.comparing (Dish::getCalories))).ifPresent (System.out::println);
    }

    @Test
    public void testReduce() {
        Optional<Dish> collect = menu.stream ().reduce (BinaryOperator.maxBy (Comparator.comparing (Dish::getCalories)));
        menu.stream ().map (Dish::getCalories).collect (Collectors.reducing (0, (d1, d2) -> d1 + d2));
        Integer collect1 = menu.stream ().collect (Collectors.reducing (0, Dish::getCalories, (d1, d2) -> d1 + d2));
    }

    @Test
    public void testReduce2() {
        Optional<Dish> collect = menu.stream ().reduce (BinaryOperator.maxBy (Comparator.comparing (Dish::getCalories)));
        collect.ifPresent (System.out::println);
        menu.stream ().max (Comparator.comparing (Dish::getCalories)).ifPresent (System.out::println);
        menu.stream ().map (Dish::getCalories).reduce (0, (d1, d2) -> d1 + d2);
        Integer collect1 = menu.stream ().map (Dish::getCalories).reduce (0, (d1, d2) -> d1 + d2);
    }

    @Test
    public void testNumber() {
        System.out.println (NumberUtils.isNumber ("1234"));
    }

    @Test
    public void testSummarizing() {
        DoubleSummaryStatistics collect = menu.stream ().collect (Collectors.summarizingDouble (Dish::getCalories));
        IntSummaryStatistics collect1 = menu.stream ().collect (Collectors.summarizingInt (Dish::getCalories));
        LongSummaryStatistics collect2 = menu.stream ().collect (Collectors.summarizingLong (Dish::getCalories));
        Double collect3 = menu.stream ().collect (Collectors.summingDouble (Dish::getCalories));
        double sum = menu.stream ().mapToDouble (Dish::getCalories).sum ();

    }

    @Test
    public void testCollect() {
        LinkedList<Dish> collect = menu.stream ().filter (Dish::isVegetarian).collect (Collectors.toCollection (LinkedList::new));
        System.out.println (collect);
        Map<String, Dog> dogMap = dogs.stream ().collect (Collectors.toConcurrentMap (Dog::getId, dog -> dog));
        System.out.println (JSONObject.toJSONString (dogMap, true));
        System.out.println ("=============");
        Dog dog = dogMap.get ("2");
        System.out.println (JSONObject.toJSONString (dog));
    }

    @Test
    public void testConcurrentMap() {
        ConcurrentMap<Dish.Type, Long> collect = menu.stream ().collect (Collectors.toConcurrentMap (Dish::getType, v -> 1L, (a, b) -> a + b));
        System.out.println (JSONObject.toJSONString (collect, true));
        ConcurrentMap<Dish.Type, Integer> collect1 = menu.stream ().collect (Collectors.toConcurrentMap (Dish::getType, v -> 1, (a, b) -> a + b));
        System.out.println (JSONObject.toJSONString (collect1, true));

        Map<Dish.Type, Long> collect2 = menu.stream ().collect (Collectors.toConcurrentMap (Dish::getType, v -> 1L, (a, b) -> a + b, ConcurrentSkipListMap::new));
        Optional.ofNullable (collect2).ifPresent (m -> {
            System.out.println (collect2.getClass ());
            System.out.print (JSONObject.toJSONString (collect2, true));
        });
    }

    @Test
    public void toMap() {
        Map<String, Dog> collect = dogs.stream ().collect (Collectors.toMap (Dog::getId, Function.identity ()));
        Optional.ofNullable (collect).ifPresent (d -> {
            System.out.println (JSONObject.toJSONString (collect, true));
            System.out.println (JSONObject.toJSONString (collect.getClass ()));
        });


    }

    @Test
    public void toMap2() {
        Map<Dish.Type, Integer> collect1 = menu.stream ().collect (Collectors.toMap (Dish::getType, v -> 1, (d1, d2) -> d1 + d2, ConcurrentSkipListMap::new));
        Optional.ofNullable (collect1).ifPresent (m -> {
            System.out.println (JSONObject.toJSONString (m, true));
            System.out.println (m.getClass ());
        });


    }

    @Test
    public void testDogMap() {
        ConcurrentHashMap<String, Dog> collect = dogs.stream ().collect (Collectors.toMap (Dog::getId, Function.identity (), (d1, d2) -> d1, ConcurrentHashMap::new));
        Optional.ofNullable (collect).ifPresent (m -> {
            System.out.println (JSONObject.toJSONString (collect, true));
            System.out.println (collect.getClass ());
        });
    }

    @Test
    public void testCollectionAndThen() {
        Map<String, Dog> collect = dogs.stream ().collect (Collectors.collectingAndThen (Collectors.toMap (Dog::getId, d -> d), Collections::synchronizedMap));
        Optional.ofNullable (collect).ifPresent (m -> {
            System.out.println (m.getClass ());
            System.out.println (JSONObject.toJSONString (m));
        });
    }
}
