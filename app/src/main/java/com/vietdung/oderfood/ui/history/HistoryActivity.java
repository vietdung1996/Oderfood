package com.vietdung.oderfood.ui.history;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vietdung.oderfood.R;
import com.vietdung.oderfood.adapter.HistoryAdapter;
import com.vietdung.oderfood.common.Common;
import com.vietdung.oderfood.model.ObjectClass.History;
import com.vietdung.oderfood.remote.APIOderFood;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity implements HistoryContact.View {
    private RecyclerView mRecyclerView;
    private List<History> mHistories;
    private HistoryAdapter mHistoryAdapter;
    private HistoryContact.Presenter mPresenter;
    private APIOderFood mAPIOderFood;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initView();
        setToolbar();
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("History");

    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar_history);
        mRecyclerView = findViewById(R.id.recycler_history);
        mHistories = new ArrayList<>();
        mHistoryAdapter = new HistoryAdapter(getApplicationContext(), mHistories);
        mRecyclerView.setAdapter(mHistoryAdapter);
        mAPIOderFood = Common.getAPI();
        mPresenter = new PresenterHistory(getApplicationContext(), mAPIOderFood, this);
        mPresenter.loadHistory();

    }

    @Override
    public void showHistory(List<History> histories) {
        mHistoryAdapter.setHistories(histories);
    }
}
