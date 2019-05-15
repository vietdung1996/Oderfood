package com.vietdung.oderfood.ui.home.newfood;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.vietdung.oderfood.R;
import com.vietdung.oderfood.adapter.FoodSaleOfAdapter;
import com.vietdung.oderfood.common.Common;
import com.vietdung.oderfood.model.ObjectClass.Food;
import com.vietdung.oderfood.model.ObjectClass.ILoadMore;
import com.vietdung.oderfood.model.ObjectClass.LoadMoreScroll;
import com.vietdung.oderfood.remote.APIOderFood;
import com.vietdung.oderfood.ui.fooddetails.reviewfragment.FragmentReview;

import java.util.ArrayList;
import java.util.List;

public class FragmentNewFood extends Fragment implements NewFoodContract.View, ILoadMore {
    private RecyclerView mRecyclerView;
    private FoodSaleOfAdapter mSaleOfAdapter;
    private List<Food> mFoods;
    private NewFoodContract.Presenter mPresenter;
    private APIOderFood mAPIOderFood;
    private ProgressBar mProgressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_drinks, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mFoods = new ArrayList<>();
        mAPIOderFood = Common.getAPI();
        mPresenter = new PresenterNewFood(this, mAPIOderFood);
        mRecyclerView = view.findViewById(R.id.recycler_new_food);
        mSaleOfAdapter = new FoodSaleOfAdapter(getContext(), mFoods);
        mRecyclerView.setAdapter(mSaleOfAdapter);
        mProgressBar = view.findViewById(R.id.progressbar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addOnScrollListener(new LoadMoreScroll(linearLayoutManager, this));
        mPresenter.loadNewFood(0);

    }


    @Override
    public void showFoods(List<Food> foods) {
        mFoods.addAll(foods);
        mSaleOfAdapter.notifyDataSetChanged();

    }

    @Override
    public void LoadMore(int sumItem) {
        mPresenter.loadMoreNewFood(sumItem, mProgressBar);
    }
}
