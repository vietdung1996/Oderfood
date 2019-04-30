package com.vietdung.oderfood.ui.favorite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vietdung.oderfood.R;
import com.vietdung.oderfood.adapter.CartFoodAdapter;
import com.vietdung.oderfood.adapter.FavoriteAdapter;
import com.vietdung.oderfood.adapter.FoodSaleOfAdapter;
import com.vietdung.oderfood.model.ObjectClass.Food;
import com.vietdung.oderfood.model.cart.ModelFavorite;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {
    private RecyclerView mRecyclerFavorite;
    private Toolbar mToolbar;
    private FavoriteAdapter mSaleOfAdapter;
    private List<Food> mFoods;
    private ModelFavorite mModelFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

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

    private void initView() {
        mRecyclerFavorite = findViewById(R.id.recycler_favorite);
        mModelFavorite = new ModelFavorite();
        mModelFavorite.conectSQL(getApplicationContext());
        mFoods = mModelFavorite.getFoodFavorite();
        mToolbar = findViewById(R.id.toolbar_favorite);
        mSaleOfAdapter = new FavoriteAdapter(getApplicationContext(),mFoods);
        mRecyclerFavorite.setAdapter(mSaleOfAdapter);

    }
}
