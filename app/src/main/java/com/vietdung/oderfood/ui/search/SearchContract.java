package com.vietdung.oderfood.ui.search;

import com.vietdung.oderfood.model.Food;

import java.util.List;

public interface SearchContract {
    interface View {
        void loadFoodSuccess(List<Food> foods);

        String getName();
    }

    interface Presenter {
        void loadFood(String query);
    }
}
