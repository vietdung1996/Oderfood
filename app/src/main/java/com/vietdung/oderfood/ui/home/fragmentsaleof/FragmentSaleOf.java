package com.vietdung.oderfood.ui.home.fragmentsaleof;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.vietdung.oderfood.R;
import com.vietdung.oderfood.adapter.FoodSaleOfAdapter;
import com.vietdung.oderfood.common.Common;
import com.vietdung.oderfood.model.ObjectClass.Food;
import com.vietdung.oderfood.model.ObjectClass.SaleOf;
import com.vietdung.oderfood.remote.APIOderFood;

import java.util.ArrayList;
import java.util.List;

public class FragmentSaleOf extends Fragment implements SaleOfContract.View{
    private LinearLayout mLinearImage;
    private RecyclerView mRecyclerSaleOf;
    private SaleOfContract.Presenter mPresenter;
    private APIOderFood mAPIOderFood;
    private FoodSaleOfAdapter mFoodAdapter;
    private List<Food> mFoods;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_promotions,container,false);
        initView(view);
        mPresenter.loadFoodSaleOf();
        return view;
    }

    private void initView(View view) {
        mLinearImage = view.findViewById(R.id.linear_image);
        mRecyclerSaleOf = view.findViewById(R.id.recycler_sale_of);
        mAPIOderFood= Common.getAPI();
        mPresenter = new SaleOfPresenter(this,mAPIOderFood);
        mFoods = new ArrayList<>();
        mFoodAdapter = new FoodSaleOfAdapter(getContext(),mFoods);
        mRecyclerSaleOf.setAdapter(mFoodAdapter);
    }

    @Override
    public void showFoods(List<Food> foods, List<SaleOf> saleOfList) {
        mFoods = foods;
        mFoodAdapter.setFoods(mFoods);
        int size = saleOfList.size();
        if(size > 5){
            size =5;
        }else {
            size =saleOfList.size();
        }
        mLinearImage.removeAllViews();
        for (int i =0;i<size;i++){
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams layoutParams =new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,450);
            layoutParams.setMargins(0,10,0,10);
            imageView.setLayoutParams(layoutParams);
            Glide.with(getContext()).load(saleOfList.get(i).getImageSaleOf()).into(imageView);
            mLinearImage.addView(imageView);
        }

    }
}
