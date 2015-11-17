package com.test.staticProxy1;

/**
 * 真实角色：实现了Subject的request()方法。
 * Created by shaolei on 2015/11/14.
 */
public class RealSubject extends Subject {

    public RealSubject() {}

    @Override
    public void request() {
        System.out.println("真实角色.");
    }
}
