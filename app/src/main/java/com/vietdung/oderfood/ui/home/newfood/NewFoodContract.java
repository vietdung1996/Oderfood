package com.vietdung.oderfood.ui.home.newfood;

import android.widget.ProgressBar;

import com.vietdung.oderfood.model.ObjectClass.Food;

import java.util.List;

public interface NewFoodContract {
    interface View {
        void showFoods(List<Food> foods);
    }

    interface Presenter {
        List<Food> loadNewFood(int limit);

       // void loadMoreNewFood(int sumItem);
        List<Food> loadMoreNewFood(int limit, ProgressBar progressBar);
    }
}
