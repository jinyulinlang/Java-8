package com.gao.entity;

import com.alibaba.fastjson.JSONObject;

public class Trade {
    private String name;
    private String city;

    public Trade() {
    }

    public Trade(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString (this);
    }
}
