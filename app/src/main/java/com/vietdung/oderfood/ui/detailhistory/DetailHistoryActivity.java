package com.vietdung.oderfood.ui.detailhistory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vietdung.oderfood.R;
import com.vietdung.oderfood.adapter.FoodSaleOfAdapter;
import com.vietdung.oderfood.adapter.HistoryFoodAdapter;
import com.vietdung.oderfood.model.ObjectClass.Food;
import com.vietdung.oderfood.model.history.ModelHistory;

import java.util.ArrayList;
import java.util.List;

public class DetailHistoryActivity extends AppCompatActivity implements DetailHistoryContract.View {
    private RecyclerView mRecyclerView;
    private List<Food> mFoods;
    private HistoryFoodAdapter mSaleOfAdapter;
    private DetailHistoryContract.Presenter mPresenter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);
        initView();
        setToolbar();
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Lịch sử");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar_details_history);
        mRecyclerView = findViewById(R.id.recycler_detail_history);
        mFoods = new ArrayList<>();
        mSaleOfAdapter = new HistoryFoodAdapter(getApplicationContext(), mFoods);
        mRecyclerView.setAdapter(mSaleOfAdapter);
        mPresenter = new DetailHistoryPresenter(this, getApplicationContext(), new ModelHistory());
        int id = getIntent().getIntExtra("idinvoice",0);
        mPresenter.loadDetailHistory(id,0);
    }

    @Override
    public void showHistory(List<Food> foods) {
        mSaleOfAdapter.setFoods(foods);
    }
}
