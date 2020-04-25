package com.ffl.blog.common.utils;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * @author lff
 * @datetime 2020/01/05 21:23
 */
public class ExceptionUtils {

    private static final Set<String> FORBIDDEN_EXCEPTION_PROP = Sets.newHashSet();

    static {
        FORBIDDEN_EXCEPTION_PROP.add("@type");
        FORBIDDEN_EXCEPTION_PROP.add("i18nKey");
        FORBIDDEN_EXCEPTION_PROP.add("stackTrace");
        FORBIDDEN_EXCEPTION_PROP.add("cause");
        FORBIDDEN_EXCEPTION_PROP.add("localizeMessage");
        FORBIDDEN_EXCEPTION_PROP.add("message");
        FORBIDDEN_EXCEPTION_PROP.add("suppressed");
        FORBIDDEN_EXCEPTION_PROP.add("exception");
    }

    public static Map<String, Object> parseData(Throwable e) {
        Map<String, Object> data = Maps.newHashMap();
        try {
            Class<?> tc = e.getClass();
            while (tc != null
                    && tc != Object.class
                    && tc != Exception.class
                    && tc != RuntimeException.class) {
                Field[] fields = tc.getDeclaredFields();
                if (fields != null) {
                    for (Field f : fields) {
                        f.setAccessible(true);
                        if (FORBIDDEN_EXCEPTION_PROP.contains(f.getName())) {
                            data.put(f.getName(), f.get(e));
                        }
                    }
                }
            }
        } catch (Exception t) {
            // do nothing
        }

        return data;
    }

    public static String toString(Throwable throwable) {
        if (throwable == null) {
            return null;
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        throwable.printStackTrace(new PrintStream(os));
        return os.toString();
    }
}
