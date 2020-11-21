package com.ahba1.geographylearningapp.activity.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ahba1.geographylearningapp.activity.login.LoginActivity;
import com.ahba1.geographylearningapp.databinding.ActivityRegisterBinding;
import com.ahba1.geographylearningapp.tools.RouterIndex;
import com.github.mzule.activityrouter.annotation.Router;
import com.orhanobut.logger.Logger;

import androidx.appcompat.app.AppCompatActivity;

@Router(RouterIndex.Register)
public class RegisterActivity extends AppCompatActivity implements RegisterContract.View{

    private ActivityRegisterBinding binding;

    private RegisterContract.Presenter presenter;

    public RegisterActivity(){
        this.presenter=new RegisterPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();
    }

    @Override
    public Context getContext(){
        return this;
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter){
        this.presenter=presenter;
    }

    @Override
    public void onRegisterSuccess(){
        //携带账号密码返回给登录界面
        Intent data=new Intent();
        data.putExtra("username",binding.etUsernameRegister.getText().toString());
        data.putExtra("password",binding.etPswRegister.getText().toString());
        setResult(LoginActivity.RESULT_CODE,data);
        this.finish();
    }

    @Override
    public void onFailure(String reason){
        Toast.makeText(this,reason,Toast.LENGTH_LONG).show();
        Logger.d(reason);
        clear();
    }

    @Override
    public void clear() {
        binding.etUsernameRegister.setText("");
        binding.etPswRegister.setText("");
        binding.etEmailRegister.setText("");
    }

    private void init(){
        binding.btnRegisterRegister
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.register(binding.etUsernameRegister.getText().toString(),
                                binding.etPswRegister.getText().toString(),
                                binding.etEmailRegister.getText().toString());
                    }
                });
    }
}
