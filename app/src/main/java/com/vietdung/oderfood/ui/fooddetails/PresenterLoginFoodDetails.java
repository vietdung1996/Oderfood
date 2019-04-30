package com.vietdung.oderfood.ui.fooddetails;

import android.content.Context;

import com.vietdung.oderfood.model.ObjectClass.Food;
import com.vietdung.oderfood.model.cart.ModelCart;
import com.vietdung.oderfood.model.cart.ModelFavorite;

import java.util.List;

public class PresenterLoginFoodDetails implements FoodDatailsContract.Presenter {
    FoodDatailsContract.View mView;
    ModelCart mModelCart;
    ModelFavorite mModelFavorite;

    public PresenterLoginFoodDetails(FoodDatailsContract.View view) {
        mView = view;
        mModelCart = new ModelCart();
        mModelFavorite = new ModelFavorite();
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

    @Override
    public void addFavorite(Food food, Context context) {
        mModelFavorite.conectSQL(context);
        boolean check = mModelFavorite.addFavorite(food);
        if (check) {
            mView.addFavoriteSuccess();
        } else {
            mView.addFavoriteFailure();
        }
    }

    public int countItemCart(Context context){
        mModelCart.conectSQL(context);
        List<Food> foods = mModelCart.getFoodCart();
        return foods.size();
    }
}
