package com.vietdung.oderfood.ui.fooddetails;

import android.content.Context;

import com.vietdung.oderfood.model.Food;

public interface PresenterFoodDatails {
    interface View {
        void addCartSuccess();
        void addCartFailure();
    }

    interface Presenter {
        void addCart(Food food, Context context);
    }
}
