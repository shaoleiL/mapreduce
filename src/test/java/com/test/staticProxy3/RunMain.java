package com.test.staticProxy3;

/**
 * Created by shaolei on 2015/11/14 23:26.
 */
public class RunMain {

    public static void main(String[] args) {
        People p1 =new People();
        p1.setCash(60000);
        p1.setUsername("jeck");


        People p2 =new People();
        p2.setCash(40000);
        p2.setUsername("rose");

        People p3 =new People();

        p3.setCash(0);
        p3.setUsername("tom");
        p3.setVip("vip");

        ProxyBusiness proxy_buy = new ProxyBusiness();
        proxy_buy.setPeople(p1);
        proxy_buy.buy_mycar();

        proxy_buy.setPeople(p2);
        proxy_buy.buy_mycar();

        proxy_buy.setPeople(p3);
        proxy_buy.buy_mycar();
    }
}
