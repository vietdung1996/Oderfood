package com.vietdung.oderfood.ui.menu;

import com.vietdung.oderfood.model.Food;
import com.vietdung.oderfood.remote.APIOderFood;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterMenu implements MenuContract.Presenter {
    private MenuContract.View mView;
    private APIOderFood mAPIOderFood;
    List<Food> mFoods;

    public PresenterMenu(MenuContract.View view, APIOderFood APIOderFood) {
        mView = view;
        mAPIOderFood = APIOderFood;

    }

    @Override
    public List<Food> loadFood() {
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("idtypefood",String.valueOf(mView.getIdtypefood()));
        mAPIOderFood.getFood(1, param).enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                mFoods = response.body();
                mView.displayMenuList(mFoods);
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

            }
        });

        return mFoods;
    }
}
