package com.test.jdkProxy;

/**
 * 真实对象的实现
 * Created by shaolei on 2015/11/15 11:09.
 */
public class BookFacadeImpl implements BookFacade {
    public void addBook() {
        System.out.println("增加图书方法。。。");
    }
}
