package com.vietdung.oderfood.ui.fooddetails;

import android.content.Context;

import com.vietdung.oderfood.model.ObjectClass.Food;

public interface FoodDatailsContract {
    interface View {
        void addCartSuccess();
        void addCartFailure();
        void addFavoriteSuccess();
        void addFavoriteFailure();
    }

    interface Presenter {
        void addCart(Food food, Context context);
        void addFavorite(Food food,Context context);
    }
}
