package com.gao;

import com.alibaba.fastjson.JSONObject;
import com.gao.entity.Apple;
import com.gao.entity.ComplexApple;
import com.gao.entity.Dog;
import com.gao.service.ThreeFunction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;

public class TestFunction {
    List<Dog> dogs = new ArrayList<> ();

    @Before
    public void setUp() throws Exception {
        listDog ();
    }

    public List<Dog> listDog() {
        Dog dog;
        for (int i = 0; i < 3; i++) {
            dog = new Dog ();
            dog.setAge (i + 1);
            dog.setBirthday (new Date ());
            dog.setHomeAddr ("宝安路" + (i + 70) + "號");
            dog.setName ("泰迪" + (i + 1));
            dog.setId ((i + 1) + "");
            dog.setSex ((i % 2 == 0) ? "男" : "女");
            dog.setTelephone ("1523616300" + i);
            dogs.add (dog);
        }
        return dogs;
    }

    @Test
    public void test1() {
        Apple apple = new Apple ();
        apple.setColor ("green");
        apple.setWeight (12L);
        Function<Apple, String> function = a -> JSONObject.toJSONString (a);
        String s = testFunction (apple, function);

        System.out.println (s);
    }

    private String testFunction(Apple apple, Function<Apple, String> function) {
        return function.apply (apple);
    }

    @Test
    public void test2() {
        Double aDouble = testDoubleFunction (3);
        System.out.println (aDouble);
    }

    private Double testDoubleFunction(Integer d) {
        IntFunction<Double> f = i -> i * 100D;
        return f.apply (d);
    }

    @Test
    public void test3() {
        Apple redApple = getAtomicApple ("red", 170L);
        System.out.println (JSONObject.toJSONString (redApple, true));
    }

    private Apple getAtomicApple(String color, Long weight) {
        AtomicReference<Apple> atomicApple = new AtomicReference<> ();
        BiFunction<String, Long, Apple> biFunction = (c, w) -> {
            atomicApple.set (new Apple ());
            atomicApple.get ().setColor (c);
            atomicApple.get ().setWeight (w);
            return atomicApple.get ();
        };
        return testBiFunction (color, weight, biFunction);
    }


    private Apple testBiFunction(String color, Long weight, BiFunction<String, Long, Apple> biFunction) {
        return biFunction.apply (color, weight);
    }

    @Test
    public void testThreeFunction() {
        ThreeFunction<String, Long, String, ComplexApple> threeFunction = ComplexApple::new;
        ComplexApple complexApple = threeFunction.apply ("red", 130L, "Fushi");
        System.out.println (JSONObject.toJSONString (complexApple));
    }

    @Test
    public void testSort() {
        dogs.forEach (System.out::println);
        dogs.sort ((d1, d2) -> d1.getTelephone ().compareTo (d2.getHomeAddr ()));
        System.out.println ("==========");
        dogs.forEach (System.out::println);
        System.out.println ("-----------------------");
        dogs.sort (Comparator.comparing (Dog::getAge).reversed ());
        dogs.forEach (System.out::println);
        System.out.println ("8888888888888888888888888");
        Dog dog = dogs.stream ().max (Comparator.comparing (Dog::getAge)).get ();
        System.out.println (dog);
        System.out.println ("拿出最小的一个");
        System.out.println (dogs.stream ().min (Comparator.comparing (Dog::getAge)).get ());

    }


}
