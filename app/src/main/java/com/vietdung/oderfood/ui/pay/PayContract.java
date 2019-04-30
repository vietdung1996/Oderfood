package com.vietdung.oderfood.ui.pay;

import android.content.Context;

import com.vietdung.oderfood.model.ObjectClass.Food;
import com.vietdung.oderfood.model.ObjectClass.Invoice;

import java.util.List;

public interface PayContract {
    interface View{
        void getListFood(List<Food> foods);
        void orderSuccess();
        void oderFailure();

    }

    interface Presenter{
        void orderFood(Invoice invoice);
        void getListFoodCart(Context context);
    }
}
