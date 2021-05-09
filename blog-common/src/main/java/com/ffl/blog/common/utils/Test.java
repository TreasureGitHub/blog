package com.ffl.blog.common.utils;

/**
 * @author lff
 * @datetime 2021/05/09 17:46
 */
public class Test {

    public static void main(String[] args) throws Exception {

        Result result = HttpClientUtils.doGet("https://www.hao123.com/");

        System.out.println(result.getCode());
        System.out.println(result.getMessage());

    }
}
