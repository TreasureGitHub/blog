package com.ffl.blog.common.utils;

import com.google.common.base.Strings;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author lff
 * @datetime 2020/01/03 19:52
 */
public class ReflectUtils {

    private static final String TYPE_NAME_STRING = "class";

    /**
     * 获取类定义的某个泛型Class
     *
     * @param clazz
     * @param genericClass
     * @param <T>
     * @return
     * @throws ClassNotFoundException
     */
    public static <T> Class<? extends T> getGenericClass(Class<?> clazz,Class<T> genericClass) throws ClassNotFoundException {
        Type type = clazz.getGenericSuperclass();
        Type[] typeArr = ((ParameterizedType)type).getActualTypeArguments();
        Class<? extends T> res = null;

        for(Type t:typeArr){
            Class<?> classOpt = getClass(t);
            if(genericClass.isAssignableFrom(classOpt)){
                res = (Class<? extends T>) classOpt;
            }
        }

        if(res == null){
            throw new ClassNotFoundException("Can not find class by：" + clazz.getName() + " generic " + genericClass.getName());
        }

        return res;
    }

    /**
     * 将type 转换成 class 对象
     *
     * @param type
     * @return
     * @throws ClassNotFoundException
     */
    public static Class<?> getClass(Type type) throws ClassNotFoundException {
        String className = getClassName(type);
        if(Strings.isNullOrEmpty(className)){
            throw new ClassNotFoundException("Can not find class by: " + type);
        }

        return Class.forName(className);
    }

    /**
     * 得到class 的名称
     *
     * @param type
     * @return
     */
    private static String getClassName(Type type){
        if(type == null){
            return null;
        }

        String className = type.toString();
        if(className.startsWith(TYPE_NAME_STRING)){
            className = className.substring(TYPE_NAME_STRING.length() + 1);
        }
        return className;
    }

    /**
     * 根据 Class 的无参构造器构造 得到对象
     *
     * @param clazz
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static <T> T generateObject(Class<T> clazz) throws InstantiationException, IllegalAccessException {
        try {
            return clazz.newInstance();
        } catch (IllegalAccessException e) {
            throw new IllegalAccessException(("Failed to find default constructor by: " + clazz.getName()));
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Type type = String.class;
        System.out.println(getClassName(type));
    }
}
