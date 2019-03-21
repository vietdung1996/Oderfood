package com.vietdung.oderfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vietdung.oderfood.R;
import com.vietdung.oderfood.model.Food;
import com.vietdung.oderfood.model.TypeFood;
import com.vietdung.oderfood.ui.fooddetails.FoodDetailsActivity;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    private Context mContext;
    private List<Food> mFoods;
    private LayoutInflater mInflater;
    public static String PARA_KEY = "123";
    public static String INTENT_KEY ="321";

    public FoodAdapter(Context context, List<Food> foods) {
        mContext = context;
        mFoods = foods;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_item_food,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.bindView(mFoods.get(position));
        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FoodDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(PARA_KEY,mFoods.get(position));
                intent.putExtra(INTENT_KEY,bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFoods == null ? 0 : mFoods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLinearLayout;
        private ImageView mImageFood;
        private TextView mTextNameFood;
        private TextView mTextPriceFood;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageFood = itemView.findViewById(R.id.image_food);
            mTextNameFood = itemView.findViewById(R.id.text_name_food);
            mTextPriceFood = itemView.findViewById(R.id.text_price_food);
            mLinearLayout = itemView.findViewById(R.id.linearlayout);
        }

        public void bindView(Food food){
            mTextNameFood.setText(food.getName());
            mTextPriceFood.setText(food.getPrice());
            Glide.with(mContext).load(food.getImage())
                    .placeholder(R.drawable.load1)
                    .error(R.drawable.cancel)
                    .into(mImageFood);
        }
    }

    public void setFoods(List<Food> foods){
        mFoods = foods;
        notifyDataSetChanged();
    }
}
