package com.vietdung.oderfood.ui.home.fragmentsaleof;

import com.vietdung.oderfood.model.ObjectClass.Food;
import com.vietdung.oderfood.model.ObjectClass.SaleOf;
import com.vietdung.oderfood.remote.APIOderFood;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaleOfPresenter implements SaleOfContract.Presenter {
    private SaleOfContract.View mView;
    private APIOderFood mAPIOderFood;
    private List<SaleOf> mSaleOfs;
    private  List<Food> mFoods;


    public SaleOfPresenter(SaleOfContract.View view, APIOderFood APIOderFood) {
        mView = view;
        mAPIOderFood = APIOderFood;
        mFoods = new ArrayList<>();
    }

    @Override
    public List<Food> loadFoodSaleOf() {

        mAPIOderFood.getFoodSaleOf().enqueue(new Callback<List<SaleOf>>() {
            @Override
            public void onResponse(Call<List<SaleOf>> call, Response<List<SaleOf>> response) {
                mSaleOfs = response.body();
                for(int i = 0;i<mSaleOfs.size();i++){
                   List<Food> foods = mSaleOfs.get(i).getFoods();
                   for(int j =0;j<foods.size();j++){
                       mFoods.add(foods.get(j));
                   }
                }

                mView.showFoods(mFoods,mSaleOfs);
            }

            @Override
            public void onFailure(Call<List<SaleOf>> call, Throwable t) {

            }
        });

        return mFoods;
    }
}
