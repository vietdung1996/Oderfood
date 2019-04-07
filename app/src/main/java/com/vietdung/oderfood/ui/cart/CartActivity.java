package com.vietdung.oderfood.ui.cart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vietdung.oderfood.R;
import com.vietdung.oderfood.adapter.CartFoodAdapter;
import com.vietdung.oderfood.adapter.UpdateListener;
import com.vietdung.oderfood.model.Food;
import com.vietdung.oderfood.model.cart.ModelCart;

import java.util.List;

public class CartActivity extends AppCompatActivity implements CartContract.View,
        View.OnClickListener, UpdateListener {

    private static final String EXTRA_FOOD = "EXTRA_FOOD";
    ModelCart mModelCart;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private List<Food> mFoods;
    private CartFoodAdapter mFoodAdapter;
    private Button mButtonOrder;
    private TextView mTextPriceOrder;

    public static Intent getCartIntent(Context context, Food food) {
        Intent intent = new Intent(context, CartActivity.class);
        intent.putExtra(EXTRA_FOOD, food);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();
        setToolbar();
        updateTextPrice();
    }

    private void updateTextPrice() {
        mFoods = mModelCart.getFoodCart();
        int mPriceOrder = 0;
        for(int i =0;i<mFoods.size();i++){
            mPriceOrder += mFoods.get(i).getPrice()*mFoods.get(i).getQuality();
            Log.d("mPrice", "updateTextPrice: "+mPriceOrder+" "+mFoods.get(i).getQuality());
        }

        mTextPriceOrder.setText("Tổng tiền: "+String.valueOf(mPriceOrder)+" VNĐ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateTextPrice();
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Giỏ hàng");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        mToolbar = findViewById(R.id.tool_bar_cart);
        mRecyclerView = findViewById(R.id.recycler_cart);
        mModelCart = new ModelCart();
        mModelCart.conectSQL(getApplicationContext());
        mFoods = mModelCart.getFoodCart();
        mFoodAdapter = new CartFoodAdapter(getApplicationContext(), mFoods);
        mFoodAdapter.setUpdateListener(this);
        mFoodAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mFoodAdapter);
        mButtonOrder = findViewById(R.id.button_order);
        mTextPriceOrder = findViewById(R.id.text_price_oder);
        mButtonOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_order:
                break;
        }
    }

    @Override
    public void updatePrice() {
        updateTextPrice();
    }
}
