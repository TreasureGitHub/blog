package com.ffl.blog.common.i18n;

import com.ffl.blog.common.utils.Assert;
import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;

/**
 * @author lff
 * @datetime 2020/01/04 20:38
 */
@Data
public class I18nKey extends I18nGetter {

    private static final long serialVersionUID = -1000829119529201205L;

    private String key;

    private String defaultMsg;

    /**
     * 中英文前台展示的文本信息
     */
    private String displayMsg;

    private Map<String,String> placeHolder = Maps.newHashMap();

    public I18nKey(I18nArea area,String key, String defaultMsg) {
        if(area == null){
            throw new IllegalArgumentException("area must not be null");
        }

        if(key == null){
            throw new IllegalArgumentException("key must not be null");
        }

        this.key = I18nGetter.joinKey(area.getKey(),key);
        this.defaultMsg = defaultMsg;
    }

    public I18nKey putPlaceHolder(String key,String value){
        Assert.notNull(key);
        this.placeHolder.put(key,value);
        return this;
    }
}
