package com.vietdung.oderfood.model.ObjectClass;

import com.google.gson.annotations.SerializedName;
import com.vietdung.oderfood.model.ObjectClass.Food;

import java.util.List;

public class SaleOf {
    @SerializedName("IDKM")
    private int ID;
    @SerializedName("NAMEKM")
    private String NameSaleOf;
    @SerializedName("IMAGEKM")
    private String imageSaleOf;
    @SerializedName("LISTFOOD")
    private List<Food> mFoods;
    private String NameTypeFood;

    private int IdTypeFood;
    private String dateStart;
    private String dateEnd;
    public String getNameTypeFood() {
        return NameTypeFood;
    }

    public void setNameTypeFood(String nameTypeFood) {
        NameTypeFood = nameTypeFood;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNameSaleOf() {
        return NameSaleOf;
    }

    public void setNameSaleOf(String nameSaleOf) {
        NameSaleOf = nameSaleOf;
    }

    public int getIdTypeFood() {
        return IdTypeFood;
    }

    public void setIdTypeFood(int idTypeFood) {
        IdTypeFood = idTypeFood;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getImageSaleOf() {
        return imageSaleOf;
    }

    public void setImageSaleOf(String imageSaleOf) {
        this.imageSaleOf = imageSaleOf;
    }

    public List<Food> getFoods() {
        return mFoods;
    }

    public void setFoods(List<Food> foods) {
        mFoods = foods;
    }
}
