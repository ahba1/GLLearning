package com.ahba1.geographylearningapp.module.http.internet.converter;

import android.util.Log;

import com.ahba1.geographylearningapp.module.http.model.ProgressResponseBody;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ResponseInterceptor implements Interceptor {

    private final static Charset UTF8=Charset.forName("UTF-8");


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Response originalResponse = chain.proceed(request);

        Log.v("TAG", originalResponse.body().contentLength()+"");

        return originalResponse.newBuilder()
                .body(new ProgressResponseBody(originalResponse.body())).build();
    }
}
