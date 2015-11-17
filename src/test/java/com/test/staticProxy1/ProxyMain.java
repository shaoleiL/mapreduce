package com.test.staticProxy1;

/**
 * Created by shaolei on 2015/11/14.
 */
public class ProxyMain {

    public static void main(String[] args) {
        Subject subject = new ProxySubject();
        subject.request();
    }
}
