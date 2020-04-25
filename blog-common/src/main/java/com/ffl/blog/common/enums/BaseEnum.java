package com.ffl.blog.common.enums;

import com.ffl.blog.common.i18n.I18nKey;

/**
 * 基础枚举类
 *
 * @author lff
 * @datetime 2020/01/05 10:40
 */
public interface BaseEnum<T> {

    /**
     * 得到编码
     *
     * @return
     */
    Integer getCode();

    /**
     * 得到 国际化 描述
     *
     * @return
     */
    I18nKey getDesc();

}
