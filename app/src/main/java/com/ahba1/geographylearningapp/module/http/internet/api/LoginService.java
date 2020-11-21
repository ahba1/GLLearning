package com.ahba1.geographylearningapp.module.http.internet.api;

import com.ahba1.geographylearningapp.module.http.BaseRouter;
import com.ahba1.geographylearningapp.module.http.model.LoginInfo;
import com.ahba1.geographylearningapp.tools.HttpClient;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

@BaseRouter(url = HttpClient.BASE_URL)
public interface LoginService {
    /**
     * 获取登录信息
     * @param username 帐号
     * @param password 密码
     * @return 登录信息
     */
    @FormUrlEncoded
    @POST(HttpClient.LOGIN_RELATIVE_URL)
    Observable<LoginInfo> getLoginInfo(
            @Field("username") String username,
            @Field("password") String password
    );
}
