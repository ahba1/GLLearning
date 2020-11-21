package com.ahba1.geographylearningapp;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.ahba1.geographylearningapp.module.ApplicationAPI;
import com.ahba1.geographylearningapp.module.http.APIManager;
import com.ahba1.geographylearningapp.module.rxjava2.BaseObserver;
import com.ahba1.geographylearningapp.module.rxjava2.SchedulersCompat;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import okhttp3.ResponseBody;


public class MyApplication extends Application {
    private String cacheDir;
    private String root;
    private String currentUserToken;
    private static MyApplication instance=null;

    @Override
    public void attachBaseContext(Context base){
        super.attachBaseContext(base);
    }
    @Override
    public void onCreate(){
        super.onCreate();
        cacheDir=getFilesDir().getPath();
        root="/cubeActivityFiles/";
        instance=this;
        ApplicationAPI.getInstance().bind(getApplicationContext());
        Logger.addLogAdapter(new AndroidLogAdapter());
        Log.v("TAG", Environment.getDataDirectory().getAbsolutePath());
    }

    public String getCacheRootDir(){
        return cacheDir+root;
    }

    public void setCurrentUserToken(String token){
        this.currentUserToken=token;
    }

    public String getCurrentUserToken(){
        return this.currentUserToken;
    }

    public static MyApplication getInstance(){
        return instance;
    }
}
