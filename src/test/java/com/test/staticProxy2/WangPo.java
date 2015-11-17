package com.test.staticProxy2;

/**
 * 王婆
 * Created by shaolei on 2015/11/14.
 */
public class WangPo implements KindWomen {
    private KindWomen kindWomen;

    public WangPo() {
        this.kindWomen = new Panjinlian();
    }

    public WangPo(KindWomen kindWomen) {
        this.kindWomen = kindWomen;
    }

    public void makeEyesWithMan() {
        this.kindWomen.makeEyesWithMan(); //王婆这么大年龄了，谁看她抛媚眼？！
    }

    public void happyWithMan() {
        this.kindWomen.happyWithMan(); //自己老了，干不了，可以让年轻的代替
    }
}
