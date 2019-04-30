package com.vietdung.oderfood.ui.search;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vietdung.oderfood.R;
import com.vietdung.oderfood.adapter.FoodAdapter;
import com.vietdung.oderfood.adapter.FoodSaleOfAdapter;
import com.vietdung.oderfood.common.Common;
import com.vietdung.oderfood.model.ObjectClass.Food;
import com.vietdung.oderfood.remote.APIOderFood;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchContract.View, SearchView.OnQueryTextListener {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerSearch;
    private List<Food> mFoods;
    private FoodSaleOfAdapter mFoodAdapter;
    private SearchContract.Presenter mPresenter;
    private APIOderFood mAPIOderFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
        setToolbar();
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initView() {
        mToolbar = findViewById(R.id.tool_bar_search);
        mRecyclerSearch = findViewById(R.id.recycler_search);
        mFoods = new ArrayList<>();
        mFoodAdapter = new FoodSaleOfAdapter(getApplicationContext(),mFoods);
        mRecyclerSearch.setAdapter(mFoodAdapter);
        mAPIOderFood = Common.getAPI();
        mPresenter = new PresenterSearch(this,mAPIOderFood);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem menuItem = menu.findItem(R.id.it_search);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);
        return true;
    }


    @Override
    public void loadFoodSuccess(List<Food> foods) {
        mFoodAdapter.setFoods(foods);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mPresenter.loadFood(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
