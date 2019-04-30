package com.vietdung.oderfood.ui.pay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.vietdung.oderfood.R;
import com.vietdung.oderfood.common.Common;
import com.vietdung.oderfood.model.ObjectClass.DetailInvoice;
import com.vietdung.oderfood.model.ObjectClass.Food;
import com.vietdung.oderfood.model.ObjectClass.Invoice;
import com.vietdung.oderfood.remote.APIOderFood;

import java.util.ArrayList;
import java.util.List;

public class PayActivity extends AppCompatActivity implements View.OnClickListener, PayContract.View {
    private EditText mEditName;
    private EditText mEditSDT;
    private EditText mEditAddress;
    private Button mButtonPay;
    private CheckBox mCheckBox;
    private ImageView mImagePay;
    private ImageView mImageDelivery;
    private PayPresenter mPayPresenter;
    private APIOderFood mAPIOderFood;
    private List<DetailInvoice> detailInvoices;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        initView();
        setToolbar();
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        mEditName = findViewById(R.id.edit_name_order);
        mEditAddress = findViewById(R.id.edit_address);
        mEditSDT = findViewById(R.id.edit_telephone);
        mButtonPay = findViewById(R.id.button_pay);
        mCheckBox = findViewById(R.id.checkbox_agree);
        mImagePay = findViewById(R.id.image_pay_card);
        mImageDelivery = findViewById(R.id.image_deliver);
        mButtonPay.setOnClickListener(this);
        mToolbar = findViewById(R.id.toolbar_pay);
        mAPIOderFood = Common.getAPI();
        mPayPresenter = new PayPresenter(mAPIOderFood, this);
        mPayPresenter.getListFoodCart(getApplicationContext());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_pay:
                String nameOrder = mEditName.getText().toString().trim();
                String telephone = mEditSDT.getText().toString().trim();
                String address = mEditAddress.getText().toString().trim();
                if (nameOrder.length() > 0 && telephone.length() > 0 && address.length() > 0) {
                    if (mCheckBox.isChecked()) {
                        Invoice invoice = new Invoice();
                        invoice.setNameOrder(nameOrder);
                        invoice.setAddress(address);
                        invoice.setTelephone(telephone);
                        invoice.setDetailInvoices(detailInvoices);

                        mPayPresenter.orderFood(invoice);
                    } else {
                        Toast.makeText(getApplicationContext(), "Bạn chưa đồng ý ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void getListFood(List<Food> foods) {
        detailInvoices = new ArrayList<>();
        for (int i = 0; i < foods.size(); i++) {
            DetailInvoice detailInvoice = new DetailInvoice();
            detailInvoice.setIdFood(foods.get(i).getId());
            detailInvoice.setNumber(foods.get(i).getQuality());
            detailInvoices.add(detailInvoice);
        }

    }

    @Override
    public void orderSuccess() {
        Toast.makeText(getApplicationContext(), "Bạn dã đặt món thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void oderFailure() {
        Toast.makeText(getApplicationContext(), "Đặt món thất bại", Toast.LENGTH_SHORT).show();
    }
}
