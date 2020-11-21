package com.ahba1.geographylearningapp.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ahba1.geographylearningapp.databinding.ActivityLoginBinding;
import com.ahba1.geographylearningapp.module.http.model.LoginInfo;
import com.ahba1.geographylearningapp.tools.RouterIndex;
import com.github.mzule.activityrouter.annotation.Router;
import com.github.mzule.activityrouter.router.Routers;

import androidx.appcompat.app.AppCompatActivity;

@Router(RouterIndex.Login)
public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    public final static int REQUEST_CODE=888;
    public final static int RESULT_CODE=777;

    private LoginContract.Presenter presenter;

    private ActivityLoginBinding binding;

    public LoginActivity(){
        this.presenter=new LoginPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //视图绑定
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //初始化
        init();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode==RESULT_CODE){
            binding.etUsernameLogin.setText(data.getStringExtra("username"));
            binding.etPswLogin.setText(data.getStringExtra("password"));
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public Context getContext(){
        return this;
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter){
        this.presenter=presenter;
    }

    @Override
    public void onLoginSuccess(LoginInfo info){
        Routers.open(getContext(), RouterIndex.getActivityIndex(RouterIndex.Cube));
        finish();
    }

    @Override
    public void onFailure(String reason){
        Toast.makeText(this,reason,Toast.LENGTH_LONG).show();
    }

    private void login(String username,String psw){
        presenter.login(username,psw);
    }

    private void init(){
        binding.btnLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(binding.etUsernameLogin.getText().toString(),
                        binding.etPswLogin.getText().toString());
            }
        });

        binding.btnRegisterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Routers.openForResult(LoginActivity.this, RouterIndex.getActivityIndex(RouterIndex.Register), REQUEST_CODE);
            }
        });
    }
}
