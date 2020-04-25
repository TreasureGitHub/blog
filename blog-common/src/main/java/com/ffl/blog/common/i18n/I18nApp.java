package com.ffl.blog.common.i18n;

/**
 * app -> page -> area -> key
 *
 * @author lff
 * @datetime 2020/01/04 20:29
 */
public class I18nApp extends I18nGetter {

    private static final long serialVersionUID = -5584017660631702188L;

    private String app;

    public I18nApp(String app) {
        if (app == null) {
            throw new IllegalArgumentException("app must not be null !");
        }

        this.app = app;
    }

    public static I18nApp init(String app) {
        return new I18nApp(app);
    }

    public I18nPage of(String page){
        return new I18nPage(this,page);
    }

    @Override
    public String getKey() {
        return this.app;
    }
}
