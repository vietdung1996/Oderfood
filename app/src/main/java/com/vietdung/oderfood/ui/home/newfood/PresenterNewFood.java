package com.vietdung.oderfood.ui.home.newfood;

import android.view.View;
import android.widget.ProgressBar;

import com.vietdung.oderfood.model.ObjectClass.Food;
import com.vietdung.oderfood.remote.APIOderFood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterNewFood implements NewFoodContract.Presenter {
    private NewFoodContract.View mView;
    private APIOderFood mAPIOderFood;
    private List<Food> mFoods;

    public PresenterNewFood(NewFoodContract.View view, APIOderFood APIOderFood) {
        mView = view;
        mAPIOderFood = APIOderFood;
    }

    @Override
    public List<Food> loadNewFood(int limit) {
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("limit", String.valueOf(limit));
        mAPIOderFood.getNewFood(param).enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                mFoods = response.body();
                mView.showFoods(mFoods);
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

            }
        });
        return mFoods;
    }

    @Override
    public List<Food> loadMoreNewFood(int limit, final ProgressBar progressBar) {
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("limit", String.valueOf(limit));
        mFoods = new ArrayList<>();
       // progressBar.setVisibility(View.VISIBLE);
        mAPIOderFood.getNewFood(param).enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {

                mFoods = response.body();
                mView.showFoods(mFoods);
                if(mFoods!=null){
                    //progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

            }
        });
        return mFoods;
    }
}
