package com.ffl.blog.pojo.enums;

import com.ffl.blog.common.constants.AppConstant;
import com.ffl.blog.common.constants.CharConstant;
import com.ffl.blog.common.enums.BaseEnum;
import com.ffl.blog.common.i18n.I18nKey;
import com.google.common.base.Strings;
import lombok.Getter;

import java.util.UUID;

/**
 * @author lff
 * @datetime 2020/01/05 10:43
 */
public enum DeleteType implements BaseEnum {

    EXISTS(0, "exists"){
        @Override
        public String getDataMark() {
            return getCode().toString();
        }

        @Override
        protected boolean matchDataMark(String dataMark) {
            return getCode().toString().equals(dataMark);
        }
    },

    DELETE(1, "delete"){
        @Override
        public String getDataMark() {
            return DeleteType.DELETE.getCode() + CharConstant.MINUS + UUID.randomUUID();
        }

        @Override
        protected boolean matchDataMark(String dataMark) {
            return ! Strings.isNullOrEmpty(dataMark) && dataMark.startsWith(getCode().toString());
        }
    };

    @Getter
    private Integer code;

    private String desc;

    DeleteType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public I18nKey getDesc() {
        return AppConstant.I18N_PAGE_ENUM.of(this.getClass().getSimpleName()).of(name(), desc);
    }

    /**
     * 存储到数据库中的标志
     *
     * @return
     */
    public abstract String getDataMark();


    /**
     * 是否删除
     *
     * @return
     */
    protected abstract boolean matchDataMark(String dataMark);

    //public static DeleteType of(Integer code) {
    //    if (code == null) {
    //        return null;
    //    }
    //
    //    for(DeleteType deleteType:values()){
    //        if(deleteType.getCode().equals(code)){
    //            return deleteType;
    //        }
    //    }
    //
    //    return null;
    //}

    /**
     * 根据删除标志 得到 删除枚举类
     *
     * @param dataMark
     * @return
     */
    public static DeleteType dataMarkToType(String dataMark){
        for(DeleteType deleteType:values()){
            if(deleteType.matchDataMark(dataMark)){
                return deleteType;
            }
        }

        return DeleteType.EXISTS;
    }

}
