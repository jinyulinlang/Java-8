package com.gao.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Dog {
    private String id;
    private String name;
    private String sex;
    private String homeAddr;
    private Integer age;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    private String telephone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHomeAddr() {
        return homeAddr;
    }

    public void setHomeAddr(String homeAddr) {
        this.homeAddr = homeAddr;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Cat{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", sex='" + sex + '\'' + ", homeAddr='" + homeAddr + '\'' + ", age=" + age + ", birthday=" + birthday + ", telephone='" + telephone + '\'' + '}';
    }



}
