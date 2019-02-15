package com.vietdung.oderfood.ui.signin;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vietdung.oderfood.common.Common;
import com.vietdung.oderfood.R;
import com.vietdung.oderfood.remote.APIOderFood;
import com.vietdung.oderfood.ui.login.MainActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,ViewSignIn {
    private EditText etRegisterName;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etEmail;
    private Button btnCreate;
    private APIOderFood mAPIOderFood;
    private TextView tvLogin;

    private PresenterLogicSignIn mLogicSignIn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        etRegisterName = findViewById(R.id.text_register_name);
        etEmail = findViewById(R.id.text_register_email);
        etPassword = findViewById(R.id.text_register_password);
        etConfirmPassword = findViewById(R.id.text_register_confirm_password);
        btnCreate = findViewById(R.id.btn_create);
        tvLogin = findViewById(R.id.tv_login);
        mAPIOderFood = Common.getAPI();
        btnCreate.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        mLogicSignIn = new PresenterLogicSignIn(this,mAPIOderFood);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_create:
                if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, R.string.passwordfails, Toast.LENGTH_SHORT).show();
                } else {
                    //createNewUser(etRegisterName.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString());
                    mLogicSignIn.createNewUser(etRegisterName.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString());
                }
                break;
            case R.id.tv_login:
                Intent itentMain = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(itentMain, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
    }

//    public void createNewUser(String name, String email, String password) {
//        mAPIOderFood.registerUser(name, email, password)
//                .enqueue(new Callback<APIResponse>() {
//                    @Override
//                    public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
//                        APIResponse result = response.body();
//                        if (result.isError()) {
//                            Toast.makeText(RegisterActivity.this, result.getError_msg(), Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(RegisterActivity.this, "User Create!" + result.getUid(), Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                    @Override
//                    public void onFailure(Call<APIResponse> call, Throwable t) {
//
//                    }
//                });
//
//    }

    @Override
    public void SignInSuccess() {
        Toast.makeText(RegisterActivity.this, R.string.signinsuccess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void SignInFail() {
        Toast.makeText(RegisterActivity.this, R.string.signinfail, Toast.LENGTH_SHORT).show();
    }
}
