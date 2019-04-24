package com.vietdung.oderfood.ui.home.fragmentsaleof;

import com.vietdung.oderfood.model.ObjectClass.Food;
import com.vietdung.oderfood.model.ObjectClass.SaleOf;

import java.util.List;

public interface SaleOfContract {
    interface View{
        void showFoods(List<Food>foods, List<SaleOf> saleOfs );

    }

    interface Presenter{
        List<Food> loadFoodSaleOf();
    }
}
