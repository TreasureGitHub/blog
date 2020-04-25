package com.ffl.blog.pojo.enums;

import com.ffl.blog.common.enums.BaseEnum;
import com.ffl.blog.common.i18n.I18nKey;
import lombok.Getter;

import static com.ffl.blog.common.constants.AppConstant.I18N_PAGE_ENUM;

/**
 * @author lff
 * @datetime 2020/01/05 16:47
 */
public enum RoleEnum implements BaseEnum {

    ADMIN(1,"admin"),

    NORMAL(2,"normal");

    @Getter
    private Integer code;

    private String desc;

    RoleEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public I18nKey getDesc() {
        return I18N_PAGE_ENUM.of(this.getClass().getSimpleName().toLowerCase()).of(name(),this.desc);
    }

    public static RoleEnum of(Integer code){
        if(code == null){
            return null;
        }

        for(RoleEnum roleEnum:values()){
            if(roleEnum.getCode().equals(code)){
                return roleEnum;
            }
        }

        return null;
    }
}