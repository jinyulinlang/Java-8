package com.gao.entity;

import com.alibaba.fastjson.JSONObject;

public class Transaction {
    private Trade trade;
    private Integer year;
    private Integer value;

    public Transaction() {
    }

    public Transaction(Trade trade, Integer year, Integer value) {
        this.trade = trade;
        this.year = year;
        this.value = value;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString (this);
    }
}
