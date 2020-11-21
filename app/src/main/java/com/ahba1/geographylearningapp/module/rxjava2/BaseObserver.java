package com.ahba1.geographylearningapp.module.rxjava2;

import android.widget.Toast;

import com.ahba1.geographylearningapp.R;
import com.ahba1.geographylearningapp.module.http.internet.exception.InputException;
import com.ahba1.geographylearningapp.module.http.internet.exception.RegisterConflictException;
import com.ahba1.geographylearningapp.module.http.internet.exception.UserNotExistException;
import com.ahba1.geographylearningapp.mvp.BaseView;


import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

public abstract class BaseObserver<T> extends DisposableObserver<T> {
    private BaseView view;

    /**
     *异常包括网络的异常和输入的异常
     */

    public BaseObserver(BaseView view) {
        this.view = view;
    }

    @Override
    protected void onStart() {
    }

    @Override
    public void onNext(T o){
        success(o);
    }

    @Override
    public void onError(Throwable e) {
        try {
            if(e!=null &&
                    e instanceof ConnectException
                    || e instanceof SocketException
                    || e instanceof HttpException
                    || e instanceof UnknownHostException
                    || e instanceof SocketTimeoutException
                    || e instanceof IOException
                    || e instanceof TimeoutException){

            } else if (e instanceof InputException){
                Toast.makeText(view.getContext(),R.string.input_error,Toast.LENGTH_LONG).show();
            }else if(e instanceof RegisterConflictException){
                Toast.makeText(view.getContext(),R.string.register_conflict,Toast.LENGTH_LONG).show();
            }else if(e instanceof UserNotExistException){
                Toast.makeText(view.getContext(),R.string.user_not_existed,Toast.LENGTH_LONG).show();
            }
            error(e);
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
    }

    @Override
    public void onComplete(){

    }

    public abstract void success(T o);

    public abstract void error(Throwable e);
}
