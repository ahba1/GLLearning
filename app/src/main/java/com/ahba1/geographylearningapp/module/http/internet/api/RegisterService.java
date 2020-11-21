package com.ahba1.geographylearningapp.module.http.internet.api;

import com.ahba1.geographylearningapp.module.http.BaseRouter;
import com.ahba1.geographylearningapp.module.http.model.RegisterInfo;
import com.ahba1.geographylearningapp.tools.HttpClient;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


@BaseRouter(url = HttpClient.BASE_URL)
public interface RegisterService {
    /**
     * register
     *
     * @param username 帐号
     * @param password 密码
     * @param email 邮箱地址
     * @return 注册信息
     */
    @FormUrlEncoded
    @POST(HttpClient.REGISTER_RELATIVE_URL)
    Observable<RegisterInfo> getRegisterInfo(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email
    );
}
