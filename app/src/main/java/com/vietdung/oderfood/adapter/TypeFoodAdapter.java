package com.vietdung.oderfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vietdung.oderfood.R;
import com.vietdung.oderfood.model.ObjectClass.TypeFood;
import com.vietdung.oderfood.ui.menu.MenuActivity;

import java.util.List;

public class TypeFoodAdapter extends RecyclerView.Adapter<TypeFoodAdapter.ViewHolder> {
    private Context mContext;
    private List<TypeFood> mTypeFoods;
    private LayoutInflater mInflater;

    public TypeFoodAdapter(Context context, List<TypeFood> typeFoods) {
        mContext = context;
        mTypeFoods = typeFoods;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_item_type_food,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.bindView(mTypeFoods.get(position),mContext);
        holder.mFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MenuActivity.class);
                intent.putExtra("idtypefood",mTypeFoods.get(position).getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTypeFoods == null ? 0 : mTypeFoods.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageTypeFood;
        private TextView mTextName;
        private FrameLayout mFrameLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageTypeFood = itemView.findViewById(R.id.iv_name_type_food);
            mTextName = itemView.findViewById(R.id.text_name_type_food);
            mFrameLayout = itemView.findViewById(R.id.frame_layout);
        }

        private void bindView(TypeFood typeFood,Context context){
            mTextName.setText(typeFood.getName());
            Glide.with(context).load(typeFood.getUrlImage())
                    .placeholder(R.drawable.load1)
                    .error(R.drawable.cancel)
                    .into(mImageTypeFood);
        }
    }


    public void setTypeFoods(List<TypeFood> typeFoods){
        mTypeFoods = typeFoods;
        notifyDataSetChanged();
    }
}
