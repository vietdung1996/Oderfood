package com.vietdung.oderfood.ui.fooddetails.comment;

import com.vietdung.oderfood.model.ObjectClass.Comment;

public interface CommentContract {
    interface View {
        void addSuccess();

        void addFailure();
    }

    interface Presenter {
        void addComment(Comment comment);
    }
}
