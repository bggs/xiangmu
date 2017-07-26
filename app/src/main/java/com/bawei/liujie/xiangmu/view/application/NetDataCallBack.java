package com.bawei.liujie.xiangmu.view.application;

/**
 * 类的作用:
 * author: 刘婕
 * date:2017/7/20
 */

public interface NetDataCallBack<T> {
    void callback(T t);
    void error(String message,int code);
}
