package com.vietdung.oderfood.ui.fooddetails.comment;

import com.vietdung.oderfood.model.ObjectClass.Comment;
import com.vietdung.oderfood.model.comment.ModelComment;
import com.vietdung.oderfood.remote.APIOderFood;

public class PresenterComment implements CommentContract.Presenter {
    APIOderFood mAPIResponse;
    CommentContract.View mView;
    ModelComment mModelComment;

    public PresenterComment(APIOderFood apiOderFood, CommentContract.View view) {
        mAPIResponse = apiOderFood;
        mModelComment = new ModelComment();
        mView = view;
    }


    @Override
    public void addComment(Comment comment) {
        boolean result = mModelComment.addComment(mAPIResponse, comment);
        if(result){
            mView.addSuccess();
        }else {
            mView.addFailure();
        }
    }
}
