package com.gao;

import com.gao.entity.CreateStream;
import com.gao.entity.Dish;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {

    List<Dish> menu = null;

    @Before
    public void setUp() throws Exception {
        menu = Arrays.asList (new Dish ("pork", false, 800, Dish.Type.MEAT), new Dish ("beef", false, 700, Dish.Type.MEAT), new Dish ("chicken", false, 400, Dish.Type.MEAT), new Dish ("french fries", true, 530, Dish.Type.OTHER), new Dish ("rice", true, 350, Dish.Type.OTHER), new Dish ("season fruit", true, 120, Dish.Type.OTHER), new Dish ("pizza", true, 550, Dish.Type.OTHER), new Dish ("prawns", false, 300, Dish.Type.FISH), new Dish ("salmon", false, 450, Dish.Type.FISH));
    }


    @Test
    public void collectNames() {
        List<String> names = menu.stream ().filter (m -> m.getCalories () < 400).map (Dish::getName).collect (Collectors.toList ());
        names.forEach (System.out::println);
    }

    @Test
    public void testSortByCarolies() {
        System.out.println ("==================");
        List<Dish> dishList = menu.stream ().filter (m -> m.getCalories () < 400).sorted (Comparator.comparing (Dish::getCalories)).collect (Collectors.toList ());
        dishList.forEach (System.out::println);
    }

    @Test
    public void testMadeStream() {
        Integer[] integers = new Integer[]{1, 2, 19, 3, 5, 9, 30};
        Stream<Integer> stream = Stream.of (integers);
        List<Integer> collect = stream.sorted (Comparator.comparing (Integer::byteValue)).collect (Collectors.toList ());
        System.out.println (collect);
        System.out.println ("==============");
        Integer max = collect.stream ().max (Integer::compareTo).get ();

        System.out.println (max);
        System.out.println ("========================");
        List<Integer> orderByDicList = collect.stream ().sorted ().collect (Collectors.toList ());
        System.out.println (orderByDicList);
        List<Integer> reverseList = collect.stream ().sorted (Comparator.comparing (Integer::byteValue).reversed ()).collect (Collectors.toList ());
        System.out.println (reverseList);

    }

    @Test
    public void testSupplierStream() {
        Stream<CreateStream.Obj> objStreamFromGenerate = CreateStream.createObjStreamFromGenerate ();
        System.out.println (objStreamFromGenerate.limit (10 ));
    }
}
