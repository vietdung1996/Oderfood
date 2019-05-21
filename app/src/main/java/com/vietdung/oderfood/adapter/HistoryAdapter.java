package com.vietdung.oderfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietdung.oderfood.R;
import com.vietdung.oderfood.model.ObjectClass.History;
import com.vietdung.oderfood.ui.detailhistory.DetailHistoryActivity;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private Context mContext;
    private List<History> mHistories;
    private LayoutInflater mInflater;

    public HistoryAdapter(Context context, List<History> histories) {
        mContext = context;
        mHistories = histories;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_item_history, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.onBindView(mHistories.get(position));
        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailHistoryActivity.class);
                intent.putExtra("idinvoice",mHistories.get(position).getIdInvoice());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mHistories == null ? 0 : mHistories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLinearLayout;
        private TextView mTextAddress;
        private TextView mTextDate;
        private TextView mTextPay;

        public ViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = itemView.findViewById(R.id.linear_history);
            mTextAddress = itemView.findViewById(R.id.text_address);
            mTextDate = itemView.findViewById(R.id.text_date);
            mTextPay = itemView.findViewById(R.id.text_pay);
        }

        public void onBindView(History history) {
            mTextDate.setText(history.getDatebuy());
            mTextAddress.setText(history.getAddress());
            if (history.getTranfer() == 0) {
                mTextPay.setText("Thanh toán trực tiếp");
            } else {
                mTextPay.setText("Thanh toán qua thẻ");
            }

        }
    }

    public void setHistories(List<History> histories){
        mHistories = histories;
        notifyDataSetChanged();
    }
}
