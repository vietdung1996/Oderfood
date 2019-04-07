package com.vietdung.oderfood.ui.cart;

public class PresenterCart implements CartContract.Presenter {
    CartContract.View mView;

    public PresenterCart(CartContract.View view) {
        mView = view;
    }


}
