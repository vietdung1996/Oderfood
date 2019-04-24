package com.vietdung.oderfood.ui.typemenu;

import com.vietdung.oderfood.model.ObjectClass.TypeFood;
import com.vietdung.oderfood.remote.APIOderFood;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogicMenu implements PresenterMenu.Presenter {
    private PresenterMenu.View mView;
    private APIOderFood mAPIOderFood;
    List<TypeFood> mTypeFoods;

    public PresenterLogicMenu(PresenterMenu.View view, APIOderFood APIOderFood) {
        mView = view;
        mAPIOderFood = APIOderFood;
    }

    @Override
    public List<TypeFood> loadTypeFood() {
        mAPIOderFood.getTypeFood().enqueue(new Callback<List<TypeFood>>() {
            @Override
            public void onResponse(Call<List<TypeFood>> call, Response<List<TypeFood>> response) {
                if(response.isSuccessful()){
                    mTypeFoods = response.body();
                    mView.disPlayList(mTypeFoods);
                }else{

                }
            }

            @Override
            public void onFailure(Call<List<TypeFood>> call, Throwable t) {

            }
        });
        return mTypeFoods;
    }
}
