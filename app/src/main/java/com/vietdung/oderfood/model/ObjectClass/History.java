package com.vietdung.oderfood.model.ObjectClass;

import com.google.gson.annotations.SerializedName;

public class History {
    @SerializedName("IDINVOICE")
    private int idInvoice;
    @SerializedName("DATEBUY")
    private String datebuy;
    @SerializedName("ADDRESS")
    private String address;
    @SerializedName("NAMEORDER")
    private String nameoder;
    @SerializedName("TRANSFER")
    private int tranfer;

    public History(String datebuy, String address, String nameoder, int tranfer) {
        this.datebuy = datebuy;
        this.address = address;
        this.nameoder = nameoder;
        this.tranfer = tranfer;
    }

    public History() {

    }

    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    public String getDatebuy() {
        return datebuy;
    }

    public void setDatebuy(String datebuy) {
        this.datebuy = datebuy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameoder() {
        return nameoder;
    }

    public void setNameoder(String nameoder) {
        this.nameoder = nameoder;
    }

    public int getTranfer() {
        return tranfer;
    }

    public void setTranfer(int tranfer) {
        this.tranfer = tranfer;
    }
}
