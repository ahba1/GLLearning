package com.ahba1.geographylearningapp.activity.register;

import com.ahba1.geographylearningapp.mvp.BasePresenter;
import com.ahba1.geographylearningapp.mvp.BaseView;

class RegisterContract {
    interface View extends BaseView<Presenter>{
        void onRegisterSuccess();

        void onFailure(String reason);

        void clear();
    }

    interface Presenter extends BasePresenter{
        default void start(){}

        void register(String username,String password,String email);
    }
}
