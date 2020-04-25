package com.ffl.blog.pojo.common;

import com.ffl.blog.common.constants.AppConstant;
import com.ffl.blog.common.enums.BaseEnum;
import com.ffl.blog.common.i18n.I18nKey;
import lombok.Getter;

/**
 * @author lff
 * @datetime 2020/01/05 21:07
 */
public enum StatusCode implements BaseEnum {

    SUCCESS(0, "success", "Success");

    @Getter
    private Integer code;

    @Getter
    private String key;

    @Getter
    private String msg;

    StatusCode(Integer code, String key, String msg) {
        this.code = code;
        this.key = key;
        this.msg = msg;
    }

    @Override
    public I18nKey getDesc() {
        return AppConstant.I18N_PAGE_COMMON.of("StatusCode").of(this.getKey(), this.getMsg());
    }
}
