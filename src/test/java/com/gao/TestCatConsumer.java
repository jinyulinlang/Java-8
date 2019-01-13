package com.gao;

import com.alibaba.fastjson.JSONObject;
import com.gao.entity.Cat;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TestCatConsumer {
    List<Cat> cats = new ArrayList<> (10);

    @Before
    public void setUp() throws Exception {
        Cat cat = null;
        forEachCat (cats);
    }

    private void forEachCat(List<Cat> cats) {
        Cat cat = null;
        for (int i = 0; i < 10; i++) {
            cat = new Cat ();
            cat.setAge (i + 1);
            cat.setBirthday (new Date ());
            cat.setHomeAddr ("長安路" + (i + 70) + "號");
            cat.setName ("貓咪" + (i + 1));
            cat.setId ((i + 1) + "");
            cat.setSex ((i % 2 == 0) ? "男" : "女");
            cat.setTelephone ("1523616300" + i);
            cats.add (cat);
        }
    }

    @Test
    public void test2() {
        cats.forEach (System.out::println);
    }

    @Test
    public void test1() {
        Map<String, Object> map = listToMap (cats);
        Cat cat = (Cat) map.getOrDefault ("8", "1");
        System.out.println (JSONObject.toJSON (cat));
    }

    /**
     * 把list转成map
     *
     * @param cats
     * @return
     */
    private Map<String, Object> listToMap(List<Cat> cats) {
        Map<String, Object> map = new HashMap<> ();
        Consumer<Cat> catConsumer = cat -> map.put (cat.getId (), cat);
        consumerCat (cats, catConsumer);
        return map;
    }

    private void consumerCat(List<Cat> cats, Consumer<Cat> consumer) {
        cats.forEach (cat -> consumer.accept (cat));
    }

    @Test
    public void test3() {
        Map<String, Cat> map = listToMap (cats, new HashMap<String, Cat> (cats.size ()));
        Cat cat = map.get ("2");
        System.out.println (JSONObject.toJSON (cat));
    }

    @Test
    public void test() {
        String s = "3";
        Cat cat = queryCat (s);
        System.out.println (JSONObject.toJSONString (cat, true));

    }

    private Cat queryCat(String s) {
        return listToMap (cats, new HashMap<> (cats.size ())).get (s);
    }

    private Map<String, Cat> listToMap(List<Cat> cats, Map<String, Cat> map) {
        return biconsumerCat (cats, map, (c, m) -> m.put (c.getId (), c));
    }

    /**
     * 兩個參數的Consumer
     *
     * @param cats
     * @param map
     * @param biConsumer
     */
    private Map<String, Cat> biconsumerCat(List<Cat> cats, Map<String, Cat> map, BiConsumer<Cat, Map<String, Cat>> biConsumer) {
        cats.forEach (cat -> biConsumer.accept (cat, map));
        return map;
    }


    @Test
    public void test4() {
        Consumer<Cat> catConsumer = cat -> System.out.print (JSONObject.toJSONString (cat));
        Cat cat = this.cats.get (0);
        useConsumer (catConsumer, cat);
    }

    private <T> void useConsumer(Consumer<T> consumer, T t) {
        consumer.accept (t);
    }

    @Test
    public void testCollect() {

    }
}
