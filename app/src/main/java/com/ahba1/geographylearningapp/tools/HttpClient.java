package com.ahba1.geographylearningapp.tools;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class HttpClient {
    private static AsyncHttpClient client=new AsyncHttpClient();
    private final static int DEFAULT_TIMEOUT=6*1000;
    public final static String BASE = "http://175.24.111.33";
    public final static String BASE_URL="http://175.24.111.33/api/";
    public final static String FILE_BASE_URL="http://175.24.111.33/file/";
    public final static String LOGIN_RELATIVE_URL="login";
    public final static String REGISTER_RELATIVE_URL="register";
    public final static String DOWNLOAD_RELATIVE_URL="dictionary";
    private final static String TOKEN_HEADER="Authorization";
    private final static String TOKEN_PREFIX="Bearer ";
    public static void setTimeout(int timeout){
        client.setTimeout(timeout);
    }
    public static void setTimeout(){
        setTimeout(DEFAULT_TIMEOUT);
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url),params,responseHandler);
    }

    public static void post(String url,RequestParams params,AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url),params,responseHandler);
    }

    public static void download(String url,RequestParams params,FileAsyncHttpResponseHandler fileAsyncHttpResponseHandler){
        client.get(getAbsoluteUrl(url),params,fileAsyncHttpResponseHandler);
    }

    public static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public static void setToken(String token){
        Log.v("TAG",token);
        client.addHeader(TOKEN_HEADER,TOKEN_PREFIX+token);
    }

    public static String formatToken(String token){
        return TOKEN_PREFIX+token;
    }

    public static String getRelativeUrl(String absoluteUrl){
        return absoluteUrl.replace(BASE_URL,"");
    }
}
