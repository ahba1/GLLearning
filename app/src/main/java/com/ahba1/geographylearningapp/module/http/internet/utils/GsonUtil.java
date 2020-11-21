package com.ahba1.geographylearningapp.module.http.internet.utils;

import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class GsonUtil {
    private GsonUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("GsonUtil cannot be instantiated !");
    }

    private static final Gson GSON = new Gson();

    /**
     * 把json字符串转换为JavaBean
     *
     * @param json      json字符串
     * @param beanClass JavaBean的Class
     * @return 解析成功返回JavaBean类，失败返回null
     */
    public static <T> T json2Bean(String json, Class<T> beanClass) {
        T bean = null;
        try {
            bean = GSON.fromJson(json, beanClass);
        } catch (Exception e) {
            Log.i("GsonUtil", "解析json数据时出现异常\njson = " + json, e);
        }
        return bean;
    }

    /**
     * 把json字符串转换为JavaBean，如果json的根节点就是一个集合，则使用此方法<p>
     * type参数的获取方式为：<p> Type type = new TypeToken<集合泛型>(){}.getType();
     *
     * @param json json字符串
     * @return 解析成功返回指定的数据类型，失败返回null
     */
    public static <T> T json2Bean(String json, Type type) {
        T bean = null;
        try {
            bean = GSON.fromJson(json, type);
        } catch (Exception e) {
            Log.i("GsonUtil", "解析json数据时出现异常\njson = " + json, e);
        }
        return bean;
    }

    /**
     * Bean to Json
     *
     * @param type Class Type
     * @return JSON
     */
    public static String bean2Json(Type type) {
        return GSON.toJson(type);
    }

    /**
     * Bean to Json
     *
     * @param o Object
     * @return Json
     */
    public static String bean2Json(Object o) {
        return GSON.toJson(o);
    }
}
