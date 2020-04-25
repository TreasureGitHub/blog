package com.ffl.blog.common.i18n;

import com.ffl.blog.common.constants.CharConstant;
import com.google.common.base.Joiner;

import java.io.Serializable;

/**
 * @author lff
 * @datetime 2020/01/04 20:17
 */
public abstract class I18nGetter implements Serializable {

    private static final long serialVersionUID = 7638310150637857300L;

    /**
     * 获取当前 i18nK路径
     *
     * @return
     */
    public abstract String getKey();

    public static String joinKey(String prefix,String key){
        return Joiner.on(CharConstant.COMMA).join(prefix,key);
    }
}
