package com.ffl.blog.common.i18n;

/**
 * @author lff
 * @datetime 2020/01/04 20:33
 */
public class I18nPage extends I18nGetter {

    private static final long serialVersionUID = 9184515595565276897L;

    private I18nApp app;

    private String page;

    public I18nPage(I18nApp app,String page) {
        if(app == null){
            throw new IllegalArgumentException("app must not be null");
        }

        if(page == null){
            throw new IllegalArgumentException("page must not be null");
        }
        this.app = app;
        this.page = page;
    }

    public I18nArea of(String area){
        return new I18nArea(this,area);
    }

    @Override
    public String getKey() {
        return I18nGetter.joinKey(app.getKey(),page);
    }
}
