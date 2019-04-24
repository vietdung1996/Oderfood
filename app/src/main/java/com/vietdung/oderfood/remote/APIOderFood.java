package com.vietdung.oderfood.remote;


import com.vietdung.oderfood.model.APIResponse;
import com.vietdung.oderfood.model.ObjectClass.Comment;
import com.vietdung.oderfood.model.ObjectClass.Food;
import com.vietdung.oderfood.model.ObjectClass.SaleOf;
import com.vietdung.oderfood.model.ObjectClass.TypeFood;
import com.vietdung.oderfood.model.Respone.Response;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIOderFood {
    @FormUrlEncoded
    @POST("login.php")
    Call<APIResponse> loginUser(@Field("email") String email,@Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Call<APIResponse> registerUser(@Field("name") String name, @Field("email") String email,@Field("password") String password);

    @GET("gettypefood.php")
    Call<List<TypeFood>> getTypeFood();

    @FormUrlEncoded
    @POST("getmenufood.php")
    Call<List<Food>> getFood(@Query("page") Integer page, @FieldMap Map<String, String> params);

    @GET("getfoodsaleof.php")
    Call<List<SaleOf>> getFoodSaleOf();

    @FormUrlEncoded
    @POST("search.php")
    Call<List<Food>> getFoodSearch(@Query("page") Integer page, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("comment.php")
    Call<String> addComment(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("getcomment.php")
    Call<List<Comment>> getComment(@FieldMap Map<String, String> params);
}
