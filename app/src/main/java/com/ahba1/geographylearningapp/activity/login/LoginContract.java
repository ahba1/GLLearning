package com.ahba1.geographylearningapp.activity.login;

import com.ahba1.geographylearningapp.module.http.model.LoginInfo;
import com.ahba1.geographylearningapp.mvp.BasePresenter;
import com.ahba1.geographylearningapp.mvp.BaseView;

class LoginContract {
    interface View extends BaseView<Presenter>{
        void onLoginSuccess(LoginInfo info);
        void onFailure(String reason);
    }
    interface Presenter extends BasePresenter{
        @Override
        default void start(){

        }
        void login(String username,String password);
    }
}
