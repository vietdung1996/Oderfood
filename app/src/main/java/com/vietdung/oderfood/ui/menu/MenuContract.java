package com.vietdung.oderfood.ui.menu;

import com.vietdung.oderfood.model.ObjectClass.Food;

import java.util.List;

public interface MenuContract {
    interface View{
        void displayMenuList(List<Food> foods);
        int getIdtypefood();
    }

    interface Presenter{
        public List<Food> loadFood();
    }
}
