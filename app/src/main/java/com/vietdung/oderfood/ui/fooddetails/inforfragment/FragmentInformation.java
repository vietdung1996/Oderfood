package com.vietdung.oderfood.ui.fooddetails.inforfragment;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.vietdung.oderfood.R;
import com.vietdung.oderfood.model.ObjectClass.Food;
import com.vietdung.oderfood.ui.fooddetails.FoodDetailsActivity;
import com.vietdung.oderfood.ui.fooddetails.reviewfragment.NumberStarListerner;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FragmentInformation extends Fragment implements NumberStarListerner {
    private static FragmentInformation sInformation = null;
    private TextView mTextViewName;
    private TextView mTextPriceFood;
    private TextView mTextViewDescription;
    private RatingBar mRatingBar;
    private int starNumber = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infor_details, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getInforFood();
    }

    public static FragmentInformation getInstance(){
        if (sInformation == null) {
            sInformation = new FragmentInformation();
        }
        return(sInformation);
    }
    public NumberStarListerner setListerner() {
        return this;
    }

    private void getInforFood() {
        Food mFood = FoodDetailsActivity.mFood;
        mTextViewName.setText(mFood.getName());
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String price = numberFormat.format(mFood.getPrice());
        if (mFood.getPercentKM() != 0) {
            String priceSaleOf = numberFormat.format(mFood.getPrice() - mFood.getPrice() * mFood.getPercentKM() / 100);
            mTextPriceFood.setText(priceSaleOf + " VNĐ");
        } else {
            mTextPriceFood.setText(price);
        }
        mTextViewDescription.setText(mFood.getInformation());


    }

    private void initView(View view) {
        mTextViewName = view.findViewById(R.id.text_name_details);
        mTextPriceFood = view.findViewById(R.id.text_price_details);
        mTextViewDescription = view.findViewById(R.id.text_infor_food);
        mRatingBar = view.findViewById(R.id.rating_bar);
        mRatingBar.setRating(starNumber);

    }

    @Override
    public void getNumber(int star) {
        starNumber = star;
        setStar();
    }

    private void setStar() {
        //mRatingBar.setRating();
    }
}
