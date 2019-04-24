package com.vietdung.oderfood.ui.fooddetails.reviewfragment;

import com.vietdung.oderfood.model.ObjectClass.Comment;

import java.util.List;

public interface ReviewContract {
    interface View{
        void showComment(List<Comment> comments);
    }

    interface Presenter{
        void loadReview(int idFood,int limit);

        List<Comment> loadMoreReview(int idFood, int limit);
    }
}
