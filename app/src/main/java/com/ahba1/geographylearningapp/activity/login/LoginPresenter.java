package com.ahba1.geographylearningapp.activity.login;

import android.text.TextUtils;

import com.ahba1.geographylearningapp.module.ApplicationAPI;
import com.ahba1.geographylearningapp.module.http.APIManager;
import com.ahba1.geographylearningapp.module.http.model.LoginInfo;
import com.ahba1.geographylearningapp.module.rxjava2.BaseObserver;
import com.ahba1.geographylearningapp.module.rxjava2.SchedulersCompat;

import androidx.annotation.NonNull;


class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    LoginPresenter(@NonNull LoginContract.View view){
        this.view=view;
    }
    @Override
    public void start(){}

    @Override
    public void login(String username,String password){
        APIManager.getInstance()
                .getLoginService()
                .getLoginInfo(username,password)
                .compose(SchedulersCompat.applyIoSchedulers())
                .subscribe(new BaseObserver<LoginInfo>(view) {
                    @Override
                    public void success(LoginInfo info) {
                        if (!TextUtils.isEmpty(info.getInfo())){
                            ApplicationAPI.updateToken(info.getInfo());
                            view.onLoginSuccess(info);
                        }
                    }

                    @Override
                    public void error(Throwable e) {
                        view.onFailure(e.getMessage());
                    }
                });
    }
}
