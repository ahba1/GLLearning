package com.ahba1.geographylearningapp.tools;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SpUtils {
    public SpUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public final static String FILE_NAME = "share_data";

    public final static String CHECK_TAG = "check_tag";

    /**
     * the method to save the data
     * @param context
     * @param key
     * @param object
     */
    public static void put(Context context, String key, Object object){

        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }

        SharedPreferencesCompat.apply(editor);
    }

    public static void put(Context context, String key, String value){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        SharedPreferencesCompat.apply(editor);
    }

    public static void put(Context context, String key, float value){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, value);
        SharedPreferencesCompat.apply(editor);
    }

    public static void put(Context context, String key, int value){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        SharedPreferencesCompat.apply(editor);
    }

    public static void put(Context context, String key, Long value){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        SharedPreferencesCompat.apply(editor);
    }

    public static void put(Context context, String key, boolean value){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        SharedPreferencesCompat.apply(editor);
    }

    public static boolean get(Context context, String key, boolean defaultValue){

        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    public static int get(Context context, String key, int defaultValue){

        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
    }

    public static float get(Context context, String key, float defaultValue){

        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getFloat(key, defaultValue);
    }

    public static long get(Context context, String key, long defaultValue){

        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key, defaultValue);
    }

    public static String get(Context context, String key, String defaultValue){

        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    /**
     * get the stored object
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object get(Context context, String key, Object defaultValue){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        if (defaultValue instanceof String) {
            return sp.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return sp.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Float) {
            return sp.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Long) {
            return sp.getLong(key, (Long) defaultValue);
        }

        return null;
    }

    /**
     * put hashMap
     * @param context
     * @param map
     * @param key
     */
    public static void putMap(Context context, Map<?, ?> map, String key) {
        JSONArray jsonArray = new JSONArray();
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        for (int i = 0; i < map.size(); i++) {
            Map.Entry mapEntry = (Map.Entry) iterator.next();
            try {
                JSONObject object = new JSONObject();
                object.put("key", mapEntry.getKey());
                object.put("value", mapEntry.getValue());
                jsonArray.put(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        put(context, key, jsonArray.toString());
    }

    /**
     * get the stored hashMap
     * @param context
     * @param key
     * @return map
     */
    public static Map getMap(Context context, String key) {
        Map map = new HashMap();
        try {
            JSONArray jsonArray = new JSONArray((String) get(context, key, ""));
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject object = jsonArray.getJSONObject(i);
                    map.put(object.getString("key"), object.getString("value"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * remove the value corresponding to a key
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * clear all data
     * @param context
     */
    public static void clear(Context context){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * check if the key is existed
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * get all key-value pairs
     *
     * @param context
     * @return map
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * the class to solve the conflict of the method "apply()" in different API version
     *
     * @author AhBa1
     */
    private static class SharedPreferencesCompat{

        private static final Method sApplyMethod = findApplyMethod();

        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod(){
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            }catch (NoSuchMethodException e){
                e.printStackTrace();
            }
            return null;
        }

        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            editor.commit();
        }
    }

    public static String formatKey(int secondId, int id){
        return secondId+"."+id;
    }

    public static String formatValue(String path, String name, int type){
        return path+":"+name+":"+type;
    }

    public static int parseSecondId(String key){
        return Integer.parseInt(key.split(".")[0]);
    }

    public static int parseId(String key){
        return Integer.parseInt(key.split(".")[1]);
    }

    public static String parsePath(String value){
        return value.split(":")[0];
    }

    public static String parseName(String value){
        return value.split(":")[1];
    }

    public static int parseType(String value){
        return Integer.parseInt(value.split(":")[2]);
    }

    public static boolean check(Context context){
        return contains(context, CHECK_TAG)&&get(context, CHECK_TAG, false);
    }

    public static void setCheckTag(Context context){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(CHECK_TAG, true);
        SharedPreferencesCompat.apply(editor);
    }
}
