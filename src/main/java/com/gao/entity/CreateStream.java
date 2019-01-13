package com.gao.entity;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CreateStream {
    public static void main(String[] args) {
        createObjStreamFromGenerate ().limit (10).forEach (System.out::println);
        System.out.println ("==============");
    }

    public static Stream<Obj> createObjStreamFromGenerate() {
        return Stream.generate (new ObjSupplier ());
    }

    /**
     * 内部靜態類
     */
    static class ObjSupplier implements Supplier<Obj> {
        private int index = 0;
        private Random random = new Random (System.currentTimeMillis ());

        @Override
        public Obj get() {
            index = random.nextInt (100);
            return new Obj (index, "Name->" + index);
        }
    }

    /**
     * 内部靜態實體類
     */
    public static class Obj {
        private int id;
        private String name;

        public Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Obj{" + "id=" + id + ", name='" + name + '\'' + '}';
        }
    }
}
