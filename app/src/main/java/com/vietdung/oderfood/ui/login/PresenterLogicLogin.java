package com.vietdung.oderfood.ui.login;

import com.vietdung.oderfood.model.APIResponse;
import com.vietdung.oderfood.remote.APIOderFood;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogicLogin implements PresenterLogin {
    private ViewLogin mViewLogin;
    private APIOderFood mService;

    public PresenterLogicLogin(ViewLogin viewLogin, APIOderFood service) {
        mViewLogin = viewLogin;
        mService = service;
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
                        }
                    }

                    @Override
                    public void onFailure(Call<APIResponse> call, Throwable t) {

                    }
                });
    }
}
