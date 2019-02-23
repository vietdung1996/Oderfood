package com.vietdung.oderfood.remote;


import com.vietdung.oderfood.model.APIResponse;
import com.vietdung.oderfood.model.TypeFood;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIOderFood {
    @FormUrlEncoded
    @POST("login.php")
    Call<APIResponse> loginUser(@Field("email") String email,@Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Call<APIResponse> registerUser(@Field("name") String name, @Field("email") String email,@Field("password") String password);

    @GET("gettypefood.php")
    Call<List<TypeFood>> getTypeFood();


}
