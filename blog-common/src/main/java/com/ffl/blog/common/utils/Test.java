package com.ffl.blog.common.utils;

/**
 * @author lff
 * @datetime 2021/05/09 17:46
 */
public class Test {

    public static void main(String[] args) throws Exception {

        Object obj = null;
        System.out.println(obj);
        initObj(obj);
        System.out.println(obj);
    }

    public static void initObj(Object obj) {
        obj = new Object();
    }
}
