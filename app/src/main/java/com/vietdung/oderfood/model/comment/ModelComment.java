package com.vietdung.oderfood.model.comment;

import android.util.Log;

import com.vietdung.oderfood.model.ObjectClass.Comment;
import com.vietdung.oderfood.remote.APIOderFood;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelComment {
    List<Comment> mComments;
    private String mResponse = "";

    public boolean addComment(APIOderFood apiOderFood, Comment comment) {
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("IDCM", comment.getIdCM());
        param.put("IDFOOD", String.valueOf(comment.getIdFood()));
        param.put("NAMETB", comment.getNameDevice());
        param.put("TITLE", comment.getTitle());
        param.put("CONTENT", comment.getContent());
        param.put("STAR", String.valueOf(comment.getStar()));
        apiOderFood.addComment(param).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                mResponse = response.body();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        if (mResponse.equals("true")) {
            return true;
        }
        return true;
    }

    public List<Comment> getListComment(APIOderFood apiOderFood, int idfood, int limit) {
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("idfood", String.valueOf(idfood));
        param.put("limmit", String.valueOf(limit));
        apiOderFood.getComment(param).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                mComments = response.body();
              //  Log.d("truewhy", "onFailure: " + mComments.get(0).getContent());
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
            }
        });
       // Log.d("truewhy1", "onFailure: " + mComments);
        return mComments;
    }
}
