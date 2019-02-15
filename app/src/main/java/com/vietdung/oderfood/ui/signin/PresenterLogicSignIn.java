package com.vietdung.oderfood.ui.signin;

import com.vietdung.oderfood.model.APIResponse;
import com.vietdung.oderfood.remote.APIOderFood;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogicSignIn implements PresenterSignIn {
    private ViewSignIn mViewSignIn;
    private APIOderFood mAPIOderFood;

    public PresenterLogicSignIn(ViewSignIn viewSignIn, APIOderFood service) {
        mViewSignIn = viewSignIn;
        mAPIOderFood = service;
    }

    @Override
    public void createNewUser(String name, String email, String password) {
        mAPIOderFood.registerUser(name, email, password)
                .enqueue(new Callback<APIResponse>() {
                    @Override
                    public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                        APIResponse result = response.body();
                        if (result.isError()) {
                            mViewSignIn.SignInFail();
                        } else {
                            mViewSignIn.SignInSuccess();
                        }

                    }

                    @Override
                    public void onFailure(Call<APIResponse> call, Throwable t) {

                    }
                });
    }
}
