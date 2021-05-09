package com.ffl.blog.common.utils;


import org.apache.commons.text.StringSubstitutor;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lff
 * @datetime 2021/05/09 17:46
 */
public class Test {

    public static void main(String[] args) {

        String format = MessageFormat.format("我是{0},我要{1},{1},{2}", "中国人", "拼搏奋斗");

        System.out.println(format);

        String name = "张三";
        int age = 16;
        String str1 = "我叫%s，年龄%s";
        String context = String.format(str1, name, age);
        System.out.println("context: " + context);

        System.out.println("-----------------------");

        String st2 = "我叫{0}，年龄{1}";
        String context2 = MessageFormat.format(st2, name, age);
        System.out.println("context2: " + context2);

        System.out.println("-----------------------");

        Map<String, String> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", "16");
        StringSubstitutor strSubstitutor = new StringSubstitutor(map);
        String str3 = "我叫${name}，年龄${age}";
        String context3 = strSubstitutor.replace(str3);
        System.out.println("context3: " + context3);
    }
}
