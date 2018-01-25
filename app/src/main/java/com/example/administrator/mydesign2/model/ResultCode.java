package com.example.administrator.mydesign2.model;

/**
 * Created by Administrator on 2018/1/5.
 */

public class ResultCode<T> {

    private int rs;
    private String msg;
    private T value;

    public int getRs() {
        return rs;
    }

    public void setRs(int rs) {
        this.rs = rs;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
