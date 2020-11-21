package com.ahba1.geographylearningapp.module.http;

import android.util.Log;

import com.ahba1.geographylearningapp.module.http.internet.api.DownloadService;
import com.ahba1.geographylearningapp.module.http.internet.api.LoginService;
import com.ahba1.geographylearningapp.module.http.internet.api.RegisterService;
import com.ahba1.geographylearningapp.module.http.internet.converter.GsonConverterFactory;
import com.ahba1.geographylearningapp.module.http.internet.exception.NoBaseUrlException;
import com.ahba1.geographylearningapp.module.ApplicationAPI;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class APIManager {
    public static APIManager getInstance() {
        return SingleTon.mInstance;
    }

    private static class SingleTon {
        static APIManager mInstance = new APIManager();
    }

    private static File httpCacheDirectory = new File(ApplicationAPI.getInstance().getContext().getCacheDir(), "GL");

    private static int cacheSize = 200 * 1024 * 1024;

    private static Cache cache = new Cache(httpCacheDirectory, cacheSize);

    private static final int DEFAULT_TIMEOUT = 12;

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        @EverythingIsNonNull
        public void log(String  message) {
            Log.i("AhBa1", message);
        }
    });

    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build();

    static {
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private RegisterService registerService;

    private LoginService loginService;

    private DownloadService downloadService;

    public LoginService getLoginService(){
        if(loginService==null){
            loginService=createService(LoginService.class);
        }
        return loginService;
    }

    public RegisterService getRegisterService() {
        if (registerService == null) {
            registerService = createService(RegisterService.class);
        }
        return registerService;
    }

    public DownloadService getDownloadService(){
        if (downloadService == null){
            downloadService = createService(DownloadService.class);
        }
        return downloadService;
    }

    private  <T> T createService(Class<T> serviceClazz) {

        if (serviceClazz.isAnnotationPresent(BaseRouter.class)) {

            BaseRouter element = serviceClazz.getAnnotation(BaseRouter.class);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(element.url())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return getProxy(retrofit, serviceClazz);
        }

        throw new NoBaseUrlException("need base url in service");
    }

    @SuppressWarnings("unchecked")
    private  <T> T getProxy(Retrofit retrofit, Class<T> tClass) {
        return retrofit.create(tClass);
//        return (T) Proxy.newProxyInstance(tClass.getClassLoader(),
//                new Class<?>[]{tClass}, new ProxyHandler(t));
    }
}

