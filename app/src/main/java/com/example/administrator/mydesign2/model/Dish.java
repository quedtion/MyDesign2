package com.example.administrator.mydesign2.model;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/15.
 */

public class Dish {

    /**
     * id : 1
     * canteenid : 1
     * name : 测试菜品1
     * price : 3.5
     * introduce : 菜品1
     * createtime : Jan 5, 2018 9:10:27 AM
     * deleted : 0
     */
    //id
    private int id;
    private int canteenid;
    private String name;
    private double price;
    private String introduce;
    private Date createtime;
    private int deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCanteenid() {
        return canteenid;
    }

    public void setCanteenid(int canteenid) {
        this.canteenid = canteenid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
