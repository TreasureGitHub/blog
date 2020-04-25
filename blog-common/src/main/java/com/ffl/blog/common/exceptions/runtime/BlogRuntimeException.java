package com.ffl.blog.common.exceptions.runtime;

import com.ffl.blog.common.i18n.I18nKey;
import lombok.Getter;

/**
 * @author lff
 * @datetime 2020/01/04 21:33
 */
public class BlogRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 2555472128679599401L;

    /**
     * 使用国际化
     */
    @Getter
    private I18nKey i18nKey;

    public BlogRuntimeException(I18nKey i18nKey) {
        super(i18nKey.getDefaultMsg());
        this.i18nKey = i18nKey;
    }

    public BlogRuntimeException(I18nKey i18nKey, Throwable cause) {
        super(i18nKey.getDefaultMsg(), cause);
        this.i18nKey = i18nKey;
    }
}
