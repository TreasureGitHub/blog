package com.ffl.blog.common.utils;

/**
 * @author lff
 * @datetime 2020/01/04 20:54
 */
public final class Assert {

    public static void notNull(Object obj){
        if(obj == null) {
            throw new IllegalArgumentException("[Assert faild param is null;]");
        }
    }


}
