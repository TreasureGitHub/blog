package com.ffl.blog.common.exceptions.common;

import com.ffl.blog.common.i18n.I18nKey;
import lombok.Getter;

/**
 *
 *
 * @author lff
 * @datetime 2020/01/03 22:04
 */
public class BlogException extends Exception {

    private static final long serialVersionUID = -5709258416811486584L;

    /**
     * 使用国际化
     */
    @Getter
    private I18nKey i18nKey;

    public BlogException(I18nKey i18nKey) {
        super(i18nKey.getDefaultMsg());
        this.i18nKey = i18nKey;
    }

    public BlogException(I18nKey i18nKey, Throwable cause) {
        super(i18nKey.getDefaultMsg(), cause);
        this.i18nKey = i18nKey;
    }
}
