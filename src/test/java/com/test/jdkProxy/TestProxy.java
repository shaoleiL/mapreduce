package com.test.jdkProxy;

/**
 * JDK动态代理的测试
 * Created by shaolei on 2015/11/15 11:17.
 */
public class TestProxy {
    public static void main(String[] args) {
        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacade bookFacade = (BookFacade) proxy.bind(new BookFacadeImpl());
        bookFacade.addBook();
    }
}
