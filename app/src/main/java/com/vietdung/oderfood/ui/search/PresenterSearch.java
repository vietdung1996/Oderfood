package com.vietdung.oderfood.ui.search;

import android.util.Log;

import com.vietdung.oderfood.model.Food;
import com.vietdung.oderfood.remote.APIOderFood;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterSearch implements SearchContract.Presenter {
    private SearchContract.View mView;
    APIOderFood mAPIOderFood;
    private List<Food> mFoods;

    public PresenterSearch(SearchContract.View view, APIOderFood APIOderFood) {
        mView = view;
        mAPIOderFood = APIOderFood;
    }

    @Override
    public void loadFood(String query) {
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("name",query);
        mAPIOderFood.getFoodSearch(1,param).enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                mFoods = response.body();
                mView.loadFoodSuccess(mFoods);
                Log.d("vkl", "loadFood: "+mFoods);
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

            }
        });

    }
}
