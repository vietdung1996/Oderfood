package com.vietdung.oderfood.ui.menu;

import com.vietdung.oderfood.model.Food;

import java.util.List;

public interface MenuContract {
    interface View{
        void displayMenuList(List<Food> foods);

    }

    interface Presenter{

    }
}
