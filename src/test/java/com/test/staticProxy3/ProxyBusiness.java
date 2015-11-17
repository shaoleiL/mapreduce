package com.test.staticProxy3;

/**
 * 代理商
 * Created by shaolei on 2015/11/14 23:22.
 */
public class ProxyBusiness implements BuyCar {

    private People people;

    public void buy_mycar() {
        if (people.getVip() != null && people.getVip().equals("vip")) {
            people.buy_mycar();
            return;
        }
        if (people.getCash() >= 50000) {
            System.out.println(people.getUsername() + "买了新车，交易结束！");
        } else {
            System.out.println(people.getUsername() + "钱不够，不能买车，继续比赛！");
        }
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }
}
