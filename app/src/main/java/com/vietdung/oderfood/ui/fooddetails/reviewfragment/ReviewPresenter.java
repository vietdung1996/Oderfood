package com.vietdung.oderfood.ui.fooddetails.reviewfragment;

import com.vietdung.oderfood.model.ObjectClass.Comment;
import com.vietdung.oderfood.model.comment.ModelComment;
import com.vietdung.oderfood.remote.APIOderFood;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewPresenter implements ReviewContract.Presenter {
    int numberStar = 0;
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
    public int loadReview(int idFood, int limit) {
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("idfood", String.valueOf(idFood));
        param.put("limmit", String.valueOf(limit));
        mAPIOderFood.getComment(param).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                mComments = response.body();
                getNumber();

                mView.showComment(mComments, numberStar);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
        return numberStar;
    }

    public void getNumber() {
        if(mComments.size()>0){
            for (int i = 0; i < mComments.size(); i++) {
                numberStar += mComments.get(i).getStar();
            }
            numberStar = numberStar / (mComments.size());
        }

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
                getNumber();
                mView.showComment(mComments1, numberStar);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
        return mComments1;
    }
}
