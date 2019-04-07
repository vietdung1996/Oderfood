package com.vietdung.oderfood.ui.fooddetails;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vietdung.oderfood.R;
import com.vietdung.oderfood.model.Food;
import com.vietdung.oderfood.ui.cart.CartActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CustomDialogSheet extends BottomSheetDialogFragment implements View.OnClickListener {
    private static final String ARGUMENT_FOOD = "ARGUMENT_FOOD";
    private ImageView mImageFood;
    private TextView mNameFood;
    private TextView mPriceFood;
    private Button mButtonCart;
    private Context mContext;
    private Food mFood;

    public static CustomDialogSheet newInstance(Food food) {
        CustomDialogSheet customDialogSheet = new CustomDialogSheet();
        Bundle args = new Bundle();
        args.putParcelable(ARGUMENT_FOOD, food);
        customDialogSheet.setArguments(args);
        return customDialogSheet;
    }

    public void setFood() {
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String price = numberFormat.format(mFood.getPrice());
        mPriceFood.setText(price + " VNĐ");
        mNameFood.setText(mFood.getName());
        Glide.with(getContext()).load(mFood.getImage()).into(mImageFood);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_bottom_sheet, container, false);
        mImageFood = view.findViewById(R.id.image_food_bottom);
        mNameFood = view.findViewById(R.id.txt_name_food);
        mPriceFood = view.findViewById(R.id.txt_price_food);
        mFood = getArguments().getParcelable(ARGUMENT_FOOD);
        mButtonCart = view.findViewById(R.id.btn_show_cart);
        mButtonCart.setOnClickListener(this);
        setFood();
        return view;
    }

    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_show_cart:
                startActivity(CartActivity.getCartIntent(getContext(), mFood));
                break;
        }
    }
}
