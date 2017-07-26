package com.bawei.liujie.xiangmu.modle.bean;

/**
 * 类的作用:
 * author: 刘婕
 * date:2017/7/24
 */

public class Test {
    private String id;
    private String color;
    private String type;
    private String integral;
    private int num;//商品数量
    private int sumIntegral;
    private boolean isChoosed;

    public Test(String id, String color, String type, String integral) {
        this.id = id;
        this.color = color;
        this.type = type;
        this.integral = integral;
    }

    public Test() {
    }

    public Test(String id, String color, String type, String integral, int num, int sumIntegral, boolean isChoosed) {
        this.id = id;
        this.color = color;
        this.type = type;
        this.integral = integral;
        this.num = num;
        this.sumIntegral = sumIntegral;
        this.isChoosed = isChoosed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSumIntegral() {
        return sumIntegral;
    }

    public void setSumIntegral(int sumIntegral) {
        this.sumIntegral = sumIntegral;
    }

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

}
