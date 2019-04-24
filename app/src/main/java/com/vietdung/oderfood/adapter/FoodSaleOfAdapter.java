package com.vietdung.oderfood.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vietdung.oderfood.R;
import com.vietdung.oderfood.model.ObjectClass.Food;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class FoodSaleOfAdapter extends RecyclerView.Adapter<FoodSaleOfAdapter.ViewHolder> {
    private Context mContext;
    private List<Food> mFoods;
    private LayoutInflater mInflater;

    public FoodSaleOfAdapter(Context context, List<Food> foodList) {
        mContext = context;
        mFoods = foodList;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_food_sale_of,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(mFoods.get(position));

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
        private TextView mTextPercent;
        private TextView mTextPriceSaleOf;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageFood = itemView.findViewById(R.id.image_food);
            mTextNameFood = itemView.findViewById(R.id.text_name_food);
            mTextPriceFood = itemView.findViewById(R.id.text_price_food);
            mLinearLayout = itemView.findViewById(R.id.linearlayout);
            mTextPercent = itemView.findViewById(R.id.text_percent);
            mTextPriceSaleOf = itemView.findViewById(R.id.text_price_sale_of);
        }

        public void bindView(Food food){
            mTextNameFood.setText(food.getName());
            NumberFormat numberFormat = new DecimalFormat("###,###");
            String price = numberFormat.format(food.getPrice());
            String priceSaleOf = numberFormat.format(food.getPrice()-food.getPrice()*food.getPercentKM()/100);
            Log.d("what "," "+ priceSaleOf);
            mTextPriceSaleOf.setPaintFlags(mTextPriceFood.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            mTextPriceFood.setText(priceSaleOf + " VNĐ");
            mTextPriceSaleOf.setText(price+" VND");
            mTextPercent.setText("-" +food.getPercentKM()+"%");
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
