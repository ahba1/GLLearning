package com.ahba1.geographylearningapp.activity.register;


import com.ahba1.geographylearningapp.module.http.APIManager;
import com.ahba1.geographylearningapp.module.http.internet.api.ApiResponse;
import com.ahba1.geographylearningapp.module.http.model.RegisterInfo;
import com.ahba1.geographylearningapp.module.rxjava2.BaseObserver;
import com.ahba1.geographylearningapp.module.rxjava2.SchedulersCompat;

class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View view;

    RegisterPresenter(RegisterContract.View view){
        this.view=view;
    }

    @Override
    public void register(String username,String password,String email){
        APIManager.getInstance()
                .getRegisterService()
                .getRegisterInfo(username,password,email)
                .compose(SchedulersCompat.applyIoSchedulers())
                .subscribe(new BaseObserver<RegisterInfo>(view) {
                    @Override
                    public void success(RegisterInfo info) {
                        view.onRegisterSuccess();
                    }

                    @Override
                    public void error(Throwable e) {
                        view.onFailure(e.getMessage());
                    }
                });
    }
}
