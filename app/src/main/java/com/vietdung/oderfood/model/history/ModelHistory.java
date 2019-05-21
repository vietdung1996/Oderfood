package com.vietdung.oderfood.model.history;

import android.util.Log;

import com.vietdung.oderfood.common.Common;
import com.vietdung.oderfood.model.ObjectClass.Food;
import com.vietdung.oderfood.remote.APIOderFood;
import com.vietdung.oderfood.ui.detailhistory.DetailHistoryContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelHistory implements DetailHistoryContract.GetHistoryIntractor {
    private APIOderFood mAPIOderFood;
    private List<Food> mFoods;


    @Override
    public void getFoods(final OnFinishedListener onFinishedListener, int idinvoice, int limit) {
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("idinvoice", String.valueOf(idinvoice));
        param.put("limit", String.valueOf(limit));
        mAPIOderFood = Common.getAPI();
        mFoods = new ArrayList<>();
        mAPIOderFood.getDetailHistory(param).enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                mFoods = response.body();
                onFinishedListener.onFinished(mFoods);
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

            }
        });

    }
}
