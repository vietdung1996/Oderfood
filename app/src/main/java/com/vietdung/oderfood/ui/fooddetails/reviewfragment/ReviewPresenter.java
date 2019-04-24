package com.vietdung.oderfood.ui.fooddetails.reviewfragment;

import android.util.Log;

import com.vietdung.oderfood.model.ObjectClass.Comment;
import com.vietdung.oderfood.model.comment.ModelComment;
import com.vietdung.oderfood.remote.APIOderFood;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewPresenter implements ReviewContract.Presenter {
    private ReviewContract.View mView;
    private APIOderFood mAPIOderFood;
    private ModelComment mModelComment;
    private List<Comment> mComments;
    private List<Comment> mComments1;

    public ReviewPresenter(ReviewContract.View view, APIOderFood APIOderFood) {
        mView = view;
        mAPIOderFood = APIOderFood;
        mModelComment = new ModelComment();
    }

    @Override
    public void loadReview(int idFood, int limit) {
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("idfood", String.valueOf(idFood));
        param.put("limmit", String.valueOf(limit));
        mAPIOderFood.getComment(param).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                mComments = response.body();
                //Log.d("truewhy", "onFailure: " + mComments.get(0).getContent());
                mView.showComment(mComments);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });

    }
    @Override
    public List<Comment> loadMoreReview(int idFood, int limit) {
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("idfood", String.valueOf(idFood));
        param.put("limmit", String.valueOf(limit));
        mAPIOderFood.getComment(param).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                mComments1 = response.body();
                mView.showComment(mComments1);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
        return  mComments1;
    }
}
