package com.ahba1.geographylearningapp.module;

import android.content.Context;

import com.ahba1.geographylearningapp.tools.SpUtils;

public class ApplicationAPI {
    private int language;

    public static ApplicationAPI getInstance() {
        return SingleTon.mInstance;
    }

    private static String loginToken = "";

    public final static String LOGIN_TOKEN_KEY = "LOGIN_TOKEN";

    public final static String VERSION = "VERSION";

    //private static int version_code = -1;

    private static class SingleTon {
        static ApplicationAPI mInstance = new ApplicationAPI();
    }

    private Context context;

    public void bind(Context context){
        this.context=context;

        if (SpUtils.contains(context, LOGIN_TOKEN_KEY)){
            loginToken = (String)SpUtils.get(context, LOGIN_TOKEN_KEY, "noneToken");
        }
    }

    public void unbind() {
        this.context = null;
    }

    public Context getContext() {
        return context;
    }

    public static void updateToken(String token){
        loginToken = token;

        SpUtils.put(getInstance().getContext(), LOGIN_TOKEN_KEY, token);
    }

    public static String getLoginToken(){
        return loginToken;
    }
}
