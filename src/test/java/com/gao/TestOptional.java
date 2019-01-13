package com.gao;

import com.alibaba.fastjson.JSONObject;
import com.gao.entity.Car;
import com.gao.entity.Dog;
import com.gao.entity.Insurance;
import com.gao.entity.Person;
import org.junit.Test;

import javax.management.RuntimeErrorException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class TestOptional {
    List<Dog> dogs;

    public void setUp() throws Exception {
        dogs = new TestFunction ().listDog ();
    }

    private String getDogName(Dog dog) {
        if (null == dog) {
            return "unknown";
        } else {
            return dog.getName ();
        }
    }

    private String getDogNameByOptional(Dog dog) {
        return Optional.ofNullable (dog).map (d -> dog.getName ()).orElse ("unknown");
    }

    @Test
    public void testDogName() {
        System.out.println (getDogName (null));
        System.out.println (getDogNameByOptional (null));
    }

    private String getInusuranceByOptional(Person person) {
/*
        return Optional.ofNullable (person).flatMap (Person::getCar).flatMap (Car::getInsurance).map (Insurance::getName).orElse ("unknown");*/

        return Optional.ofNullable (person).flatMap (Person::getCar).flatMap (Car::getInsurance).map (Insurance::getName).orElseThrow (() -> new NullPointerException ("空指针异常！"));


    }

    @Test
    public void testInsurance() {
        System.out.println (getInusuranceByOptional (null));
    }
}
