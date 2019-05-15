package com.vietdung.oderfood.ui.pay;

import android.content.Context;
import android.widget.Toast;

import com.vietdung.oderfood.model.APIResponse;
import com.vietdung.oderfood.model.ObjectClass.DetailInvoice;
import com.vietdung.oderfood.model.ObjectClass.Food;
import com.vietdung.oderfood.model.ObjectClass.Invoice;
import com.vietdung.oderfood.model.cart.ModelCart;
import com.vietdung.oderfood.remote.APIOderFood;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayPresenter implements PayContract.Presenter {
    private APIOderFood mAPIOderFood;
    private PayContract.View mView;
    private ModelCart mModelCart;
    private List<Food> mFoods;
    private Context mContext;

    public PayPresenter(APIOderFood APIOderFood, PayContract.View view) {
        mAPIOderFood = APIOderFood;
        mView = view;
        mModelCart = new ModelCart();
    }

    @Override
    public void orderFood(Invoice invoice) {
        HashMap<String, String> param = new HashMap<String, String>();
        List<DetailInvoice> detailInvoices = invoice.getDetailInvoices();
        String arrayjson = "{\"LISTFOOD\" :[ ";
        for (int i = 0; i < detailInvoices.size(); i++) {
            arrayjson += "{";
            arrayjson += "\"idfood\" : " + detailInvoices.get(i).getIdFood() + ",";
            arrayjson += "\"number\" : " + detailInvoices.get(i).getNumber();
            if (i == detailInvoices.size() - 1) {
                arrayjson += "}";
            } else {
                arrayjson += "},";
            }
        }
        arrayjson += "]}";
        param.put("listfood", arrayjson);
        param.put("nameorder", invoice.getNameOrder());
        param.put("telephone", invoice.getTelephone());
        param.put("address", invoice.getAddress());
        param.put("transfer", "0");
        param.put("idtransfer",invoice.getIdTransfer());

        mAPIOderFood.orderFood(param).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse result = response.body();
                if (!result.isError()) {
                    int dem = mFoods.size();
                    for (int i = 0; i < dem; i++) {
                        mModelCart.deleteFood(mFoods.get(i).getId());
                    }
                    mView.orderSuccess();
                } else {

                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void getListFoodCart(Context context) {
        mModelCart.conectSQL(context);
        mFoods = mModelCart.getFoodCart();
        mView.getListFood(mFoods);

    }
}
