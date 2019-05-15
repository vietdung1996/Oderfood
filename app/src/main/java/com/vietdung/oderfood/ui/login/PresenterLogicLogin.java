package com.vietdung.oderfood.ui.login;

import android.content.Context;
import android.content.SharedPreferences;

import com.vietdung.oderfood.model.APIResponse;
import com.vietdung.oderfood.model.ObjectClass.User;
import com.vietdung.oderfood.remote.APIOderFood;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogicLogin implements PresenterLogin {
    private ViewLogin mViewLogin;
    private APIOderFood mService;
    private Context mContext;

    public PresenterLogicLogin(ViewLogin viewLogin, APIOderFood service,Context context) {
        mViewLogin = viewLogin;
        mService = service;
        mContext = context;
    }

    @Override
    public void authenticateUser(String email, String pass) {
        mService.loginUser(email, pass)
                .enqueue(new Callback<APIResponse>() {
                    @Override
                    public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                        APIResponse result = response.body();
                        if (result.isError()) {
                            mViewLogin.LoginFail();

                        } else {
                            mViewLogin.LoginSuccess();
                            User user = result.getUser();
                            SharedPreferences sharedPreferences = mContext.getSharedPreferences("login",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("nameuser",user.getName());
                            editor.putString("email",user.getEmail());
                            editor.commit();
                        }

                    }

                    @Override
                    public void onFailure(Call<APIResponse> call, Throwable t) {

                    }
                });
    }
}
