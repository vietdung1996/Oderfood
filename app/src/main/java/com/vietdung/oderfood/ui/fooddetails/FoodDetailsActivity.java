package com.vietdung.oderfood.ui.fooddetails;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vietdung.oderfood.R;
import com.vietdung.oderfood.adapter.FoodAdapter;
import com.vietdung.oderfood.model.Food;

public class FoodDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImageFood;
    private Toolbar mToolbar;
    private TextView mTextViewName;
    private TextView mTextViewPrice;
    private TextView mTextViewDescription;
    private FloatingActionButton mActionButtonFavorite;
    private RatingBar mRatingBar;
    private Food mFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        initView();
        addEvents();
    }

    @Override
    public void onClick(View view) {

    }

    private void addEvents() {
        setToolbar();
        getInforFood();
    }

    private void getInforFood() {
        Bundle b = getIntent().getBundleExtra(FoodAdapter.INTENT_KEY);
        mFood = b.getParcelable(FoodAdapter.PARA_KEY);
        Glide.with(getApplicationContext()).load(mFood.getImage()).into(mImageFood);
        mTextViewName.setText(mFood.getName());
        mTextViewPrice.setText(mFood.getPrice());
        mTextViewDescription.setText(mFood.getInformation());
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
    }


    private void initView() {
        mImageFood = findViewById(R.id.image_food_details);
        mToolbar = findViewById(R.id.toolbar_details);
        mTextViewName = findViewById(R.id.text_name_details);
        mTextViewPrice = findViewById(R.id.text_price_details);
        mTextViewDescription = findViewById(R.id.text_infor_food);
        mActionButtonFavorite = findViewById(R.id.float_button_favorite);
        mRatingBar = findViewById(R.id.rating_bar);
    }


}
