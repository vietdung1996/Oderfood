package com.vietdung.oderfood.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietdung.oderfood.R;
import com.vietdung.oderfood.model.Food;
import com.vietdung.oderfood.model.cart.ModelCart;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class CartFoodAdapter extends RecyclerView.Adapter<CartFoodAdapter.ViewHolder> {
    private Context mContext;
    private List<Food> mFoods;
    private LayoutInflater mInflater;
    private ModelCart mModelCart;
    private UpdateListener mUpdateListener;

    public CartFoodAdapter(Context context, List<Food> foods) {
        mContext = context;
        mFoods = foods;
        mInflater = LayoutInflater.from(context);
        mModelCart = new ModelCart();
        mModelCart.conectSQL(context);

    }

    public void setUpdateListener(UpdateListener updateListener) {
        mUpdateListener = updateListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_food_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Food food = mFoods.get(position);
        holder.bindView(mFoods.get(position));
        holder.mImageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelCart modelCart = new ModelCart();
                modelCart.conectSQL(mContext);
                modelCart.deleteFood(mFoods.get(position).getId());
                mFoods.remove(position);
                notifyDataSetChanged();
                mUpdateListener.updatePrice();
            }
        });
        holder.mImageSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quality = Integer.parseInt(holder.mTextQuality.getText().toString());
                if (quality > 1) {
                    quality--;
                }
                mModelCart.updateQuality(food.getId(), quality);
                holder.mTextQuality.setText(String.valueOf(quality));
                mUpdateListener.updatePrice();
            }
        });
        holder.mImageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quality = Integer.parseInt(holder.mTextQuality.getText().toString());
                quality++;
                mModelCart.updateQuality(food.getId(), quality);
                holder.mTextQuality.setText(String.valueOf(quality));
                mUpdateListener.updatePrice();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFoods == null ? 0 : mFoods.size();
    }

    public void setFoods(List<Food> foods) {
        mFoods = foods;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLinearLayout;
        private ImageView mImageFood;
        private TextView mTextNameFood;
        private TextView mTextPriceFood;
        private ImageView mImageClose;
        private TextView mTextQuality;
        private ImageView mImageAdd;
        private ImageView mImageSub;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageFood = itemView.findViewById(R.id.image_food);
            mTextNameFood = itemView.findViewById(R.id.text_name_food);
            mTextPriceFood = itemView.findViewById(R.id.text_price_food);
            mLinearLayout = itemView.findViewById(R.id.linearlayout);
            mImageClose = itemView.findViewById(R.id.image_close);
            mTextQuality = itemView.findViewById(R.id.text_quality);
            mImageAdd = itemView.findViewById(R.id.image_add);
            mImageSub = itemView.findViewById(R.id.image_sub);
        }

        public void bindView(final Food food) {
            mTextNameFood.setText(food.getName());
            NumberFormat numberFormat = new DecimalFormat("###,###");
            String price = numberFormat.format(food.getPrice());
            mTextPriceFood.setText(price + " VNĐ");
            Bitmap bitmap = BitmapFactory.decodeByteArray(food.getImageCart(), 0, food.getImageCart().length);
            mImageFood.setImageBitmap(bitmap);
            mTextQuality.setText(String.valueOf(food.getQuality()));
        }
    }
}
