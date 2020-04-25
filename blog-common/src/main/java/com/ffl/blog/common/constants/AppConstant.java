package com.ffl.blog.common.constants;

import com.ffl.blog.common.i18n.I18nApp;
import com.ffl.blog.common.i18n.I18nPage;

/**
 * @author lff
 * @datetime 2020/01/04 21:24
 */
public interface AppConstant {

    String APP = "blog";

    String P_SERVICE = "service";

    String P_ENUM = "enum";

    String P_COMMON = "common";

    I18nApp I18N_APP = I18nApp.init(APP);

    I18nPage I18N_PAGE_SERVICE = I18N_APP.of(P_SERVICE);

    I18nPage I18N_PAGE_COMMON = I18N_APP.of(P_COMMON);

    I18nPage I18N_PAGE_ENUM = I18N_APP.of(P_ENUM);
}
