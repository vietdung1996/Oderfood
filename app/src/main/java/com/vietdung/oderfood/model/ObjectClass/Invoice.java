package com.vietdung.oderfood.model.ObjectClass;

import java.util.List;

public class Invoice {
    private int mIdInvoice;
    private String mDateBuy;
    private String mState;
    private String mAddress;
    private String mNameOrder;
    private  String mTelephone;
    private int mTransfer;
    private String mIdTransfer;
    private List<DetailInvoice> mDetailInvoices;

    public int getIdInvoice() {
        return mIdInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        mIdInvoice = idInvoice;
    }

    public String getDateBuy() {
        return mDateBuy;
    }

    public void setDateBuy(String dateBuy) {
        mDateBuy = dateBuy;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getNameOrder() {
        return mNameOrder;
    }

    public void setNameOrder(String nameOrder) {
        mNameOrder = nameOrder;
    }

    public String getTelephone() {
        return mTelephone;
    }

    public void setTelephone(String telephone) {
        mTelephone = telephone;
    }

    public int getTransfer() {
        return mTransfer;
    }

    public void setTransfer(int transfer) {
        mTransfer = transfer;
    }

    public String getIdTransfer() {
        return mIdTransfer;
    }

    public void setIdTransfer(String idTransfer) {
        mIdTransfer = idTransfer;
    }

    public List<DetailInvoice> getDetailInvoices() {
        return mDetailInvoices;
    }

    public void setDetailInvoices(List<DetailInvoice> detailInvoices) {
        mDetailInvoices = detailInvoices;
    }
}
