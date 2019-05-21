package com.vietdung.oderfood.ui.history;

import android.content.Context;
import android.content.SharedPreferences;

import com.vietdung.oderfood.model.ObjectClass.History;
import com.vietdung.oderfood.remote.APIOderFood;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterHistory implements HistoryContact.Presenter{
    private Context mContext;
    private APIOderFood mAPIOderFood;
    private HistoryContact.View mView;
    private List<History> mHistories;

    public PresenterHistory(Context context,APIOderFood APIOderFood, HistoryContact.View view) {
        mAPIOderFood = APIOderFood;
        mView = view;
        mContext = context;
    }

    @Override
    public void loadHistory() {
        HashMap<String, String> param = new HashMap<String, String>();
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("login", Context.MODE_PRIVATE);
       // String name = sharedPreferences.getString("nameuser","");
        String gmail = sharedPreferences.getString("email","");
        param.put("gmail",gmail);
        param.put("limit", String.valueOf(0));
        mAPIOderFood.getHistory(param).enqueue(new Callback<List<History>>() {
            @Override
            public void onResponse(Call<List<History>> call, Response<List<History>> response) {
                mHistories = response.body();
                mView.showHistory(mHistories);
            }

            @Override
            public void onFailure(Call<List<History>> call, Throwable t) {

            }
        });

    }
}
