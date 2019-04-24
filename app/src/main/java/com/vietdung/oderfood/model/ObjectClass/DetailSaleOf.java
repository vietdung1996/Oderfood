package com.vietdung.oderfood.model.ObjectClass;

import com.google.gson.annotations.SerializedName;

public class DetailSaleOf {
    @SerializedName("IDKM")
    private int IdKM;
    @SerializedName("IDFOOD")
    private int IdFood;
    @SerializedName("PERCENTKM")
    private int percentSaleOf;

    public int getIdKM() {
        return IdKM;
    }

    public void setIdKM(int idKM) {
        IdKM = idKM;
    }

    public int getIdFood() {
        return IdFood;
    }

    public void setIdFood(int idFood) {
        IdFood = idFood;
    }

    public int getPercentSaleOf() {
        return percentSaleOf;
    }

    public void setPercentSaleOf(int percentSaleOf) {
        this.percentSaleOf = percentSaleOf;
    }
}
