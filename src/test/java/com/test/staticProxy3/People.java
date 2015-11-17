package com.test.staticProxy3;

/**
 * 人具有买车的行为
 * Created by shaolei on 2015/11/14 23:18.
 */
public class People implements BuyCar {
    private int cash;   //现金
    private String vip;
    private String username;

    public void buy_mycar() {
        System.out.print(username+"是vip 客户，可以直接购买新车！");
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
