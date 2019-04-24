package com.vietdung.oderfood.ui.fooddetails;

import android.content.Context;

import com.vietdung.oderfood.model.ObjectClass.Food;
import com.vietdung.oderfood.model.cart.ModelCart;

import java.util.List;

public class PresenterLoginFoodDetails implements PresenterFoodDatails.Presenter {
    PresenterFoodDatails.View mView;
    ModelCart mModelCart;

    public PresenterLoginFoodDetails(PresenterFoodDatails.View view) {
        mView = view;
        mModelCart = new ModelCart();
    }

    public PresenterLoginFoodDetails() {
        mModelCart = new ModelCart();
    }

    @Override
    public void addCart(Food food,Context context) {
        mModelCart.conectSQL(context);
        boolean check = mModelCart.addCart(food);
        if (check) {
            mView.addCartSuccess();
        } else {
            mView.addCartFailure();
        }

    }

    public int countItemCart(Context context){
        mModelCart.conectSQL(context);
        List<Food> foods = mModelCart.getFoodCart();
        return foods.size();
    }
}
