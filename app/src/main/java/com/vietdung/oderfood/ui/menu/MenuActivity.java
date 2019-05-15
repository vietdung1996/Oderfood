package com.vietdung.oderfood.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vietdung.oderfood.R;
import com.vietdung.oderfood.adapter.FoodSaleOfAdapter;
import com.vietdung.oderfood.common.Common;
import com.vietdung.oderfood.model.ObjectClass.Food;
import com.vietdung.oderfood.remote.APIOderFood;
import com.vietdung.oderfood.ui.cart.CartActivity;
import com.vietdung.oderfood.ui.fooddetails.PresenterLoginFoodDetails;
import com.vietdung.oderfood.ui.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements MenuContract.View, View.OnClickListener {
    private Toolbar mToolbarMenu;
    private int mIdTypeFood;
    private String mTitleToolbar = "Menu";
    private PresenterMenu mPresenterMenu;
    private List<Food> mFoods;
    private FoodSaleOfAdapter mFoodAdapter;
    private RecyclerView mRecyclerView;
    private APIOderFood mAPIOderFood;
    private TextView mTextCartSize;
    private PresenterLoginFoodDetails mPresenterLoginFoodDetails;
    private Button mButtonSort;
    private boolean mCheck = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initView();
        addEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        MenuItem itemCart = menu.findItem(R.id.it_cart);
        View customCart = MenuItemCompat.getActionView(itemCart);
        customCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        mTextCartSize = customCart.findViewById(R.id.text_count_item_cart);
        mTextCartSize.setText(String.valueOf(mPresenterLoginFoodDetails.countItemCart(getApplicationContext())));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.it_notification:
                break;
            case R.id.it_search:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mTextCartSize.setText(String.valueOf(mPresenterLoginFoodDetails.countItemCart(getApplicationContext())));
    }

    private void addEvents() {
        setToolbar();
    }

    private void setToolbar() {
        setSupportActionBar(mToolbarMenu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Menu");
        mToolbarMenu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        mToolbarMenu = findViewById(R.id.toolbar_menu);
        mRecyclerView = findViewById(R.id.recycler_menu);
        mFoods = new ArrayList<>();
        mButtonSort = findViewById(R.id.button_sort);
        mButtonSort.setOnClickListener(this);
        mFoodAdapter = new FoodSaleOfAdapter(this, mFoods);
        mAPIOderFood = Common.getAPI();
        mPresenterMenu = new PresenterMenu(this, mAPIOderFood);
        mRecyclerView.setAdapter(mFoodAdapter);
        mPresenterMenu.loadFood();
        mPresenterLoginFoodDetails = new PresenterLoginFoodDetails();


    }

    @Override
    public void displayMenuList(List<Food> foods) {
        mFoods = foods;
        mFoodAdapter.setFoods(mFoods);
    }

    @Override
    public int getIdtypefood() {
        mIdTypeFood = getIntent().getIntExtra("idtypefood", -1);
        return mIdTypeFood;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_sort:
                if (mCheck) {
                    mPresenterMenu.loadFoodByPrice("ASC");
                   // Log.d("sap xep", "onClick: giam dan" );
                    mCheck = false;
                } else {
                    mPresenterMenu.loadFoodByPrice("DESC");
                  //  Log.d("sap xep", "onClick: tang dan" );
                    mCheck = true;
                }
                break;
        }
    }
}
