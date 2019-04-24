package com.vietdung.oderfood.ui.fooddetails.reviewfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vietdung.oderfood.R;
import com.vietdung.oderfood.adapter.CommentAdapter;
import com.vietdung.oderfood.common.Common;
import com.vietdung.oderfood.model.ObjectClass.Comment;
import com.vietdung.oderfood.model.ObjectClass.Food;
import com.vietdung.oderfood.model.ObjectClass.ILoadMore;
import com.vietdung.oderfood.remote.APIOderFood;
import com.vietdung.oderfood.ui.fooddetails.FoodDetailsActivity;
import com.vietdung.oderfood.ui.fooddetails.comment.AddCommentActivity;

import java.util.ArrayList;
import java.util.List;

public class FragmentReview extends Fragment implements ReviewContract.View, ILoadMore {
    public static String EXTRA_FOOD = "IdFood";
    private TextView mTextWriteComment;
    private ReviewContract.Presenter mPresenter;
    private List<Comment> mComments;
    private CommentAdapter mCommentAdapter;
    private RecyclerView mRecyclerReview;
    private Food mFood;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review_details, container, false);
        initView(view);
        setButton();
        loadReview();
        return view;
    }

    private void loadReview() {

    }

    private void setButton() {
        mFood = FoodDetailsActivity.mFood;
        mTextWriteComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddCommentActivity.class);
                intent.putExtra(EXTRA_FOOD,mFood.getId());
                startActivity(intent);
            }
        });
        mPresenter.loadReview(mFood.getId(),0);
    }

    private void initView(View view) {
        mTextWriteComment = view.findViewById(R.id.text_write_comment);
        APIOderFood apiOderFood = Common.getAPI();
        mComments = new ArrayList<>();
        mRecyclerReview = view.findViewById(R.id.recycler_review);
        mPresenter = new ReviewPresenter(this,apiOderFood);
        mCommentAdapter = new CommentAdapter(mComments,getContext());


    }


    @Override
    public void showComment(List<Comment> comments) {
        mComments = comments;
        mCommentAdapter.setComments(comments);
        mRecyclerReview.setAdapter(mCommentAdapter);
    }

    @Override
    public void LoadMore(int sumItem) {
        List<Comment> comments = mPresenter.loadMoreReview(mFood.getId(),sumItem);
        mComments.addAll(comments);
        mCommentAdapter.notifyDataSetChanged();


    }
}
