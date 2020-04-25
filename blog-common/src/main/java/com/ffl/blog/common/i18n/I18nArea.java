package com.ffl.blog.common.i18n;

/**
 * @author lff
 * @datetime 2020/01/04 20:34
 */
public class I18nArea extends I18nGetter {

    private static final long serialVersionUID = 3000005507910919115L;

    private I18nPage page;

    private String area;

    public I18nArea(I18nPage page,String area) {
        if(page == null){
            throw new IllegalArgumentException("page must not be null");
        }

        if(area == null){
            throw new IllegalArgumentException("area must not be null");
        }
        this.page = page;
        this.area = area;
    }

    public I18nKey of(String key,String value){
        return new I18nKey(this,key,value);
    }

    @Override
    public String getKey() {
        return I18nGetter.joinKey(page.getKey(),area);
    }
}
