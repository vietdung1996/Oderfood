package com.vietdung.oderfood.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.vietdung.oderfood.R;
import com.vietdung.oderfood.model.ObjectClass.Comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<Comment> mComments;
    private Context mContext;
    private LayoutInflater mInflater;

    public CommentAdapter(List<Comment> comments, Context context) {
        mComments = comments;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_layuout_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBindView(mComments.get(position));
    }

    @Override
    public int getItemCount() {
        return mComments == null ? 0 : mComments.size();
    }

    public void setComments(List<Comment> comments) {
        mComments = comments;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextTitle;
        private RatingBar mRatingBar;
        private TextView mTextTime;
        private TextView mTextContent;
        private TextView mTextDevice;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextTitle = itemView.findViewById(R.id.text_title);
            mTextContent = itemView.findViewById(R.id.text_content);
            mRatingBar = itemView.findViewById(R.id.rating_bar);
            mTextTime = itemView.findViewById(R.id.text_time_comment);
            mTextDevice = itemView.findViewById(R.id.text_device);
        }

        public void onBindView(Comment comment) {
            mTextTitle.setText(comment.getTitle());
            mRatingBar.setRating(comment.getStar());
            mTextTime.setText(comment.getTime());
            mTextContent.setText(comment.getContent());
            mTextDevice.setText(comment.getNameDevice());
        }
    }
}
