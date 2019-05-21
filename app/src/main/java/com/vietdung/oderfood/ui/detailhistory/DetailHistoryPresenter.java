package com.vietdung.oderfood.ui.detailhistory;

import android.content.Context;

import com.vietdung.oderfood.model.ObjectClass.Food;

import java.util.List;

public class DetailHistoryPresenter implements DetailHistoryContract.Presenter,
        DetailHistoryContract.GetHistoryIntractor.OnFinishedListener {
    private DetailHistoryContract.View mView;
    private Context mContext;
    private DetailHistoryContract.GetHistoryIntractor mIntractor;

    public DetailHistoryPresenter(DetailHistoryContract.View view, Context context,
                                  DetailHistoryContract.GetHistoryIntractor intractor) {
        mView = view;
        mContext = context;
        mIntractor = intractor;
    }

    @Override
    public void loadDetailHistory(int idInvoice, int limit) {
        mIntractor.getFoods(this, idInvoice, limit);

    }

    @Override
    public void onFinished(List<Food> foods) {
        mView.showHistory(foods);

    }

    @Override
    public void onFailure(Throwable t) {

    }
}
