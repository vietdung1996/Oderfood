package com.vietdung.oderfood.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Food implements Parcelable{
    @SerializedName("idtypefood")
    private int idtypefood;
    @SerializedName("image")
    private String image;
    @SerializedName("price")
    private Integer price;
    @SerializedName("name")
    private String name;
    @SerializedName("information")
    private String information;
    @SerializedName("id")
    private int id;
    @SerializedName("PERCENTKM")
    private int percentKM;

    public int getPercentKM() {
        return percentKM;
    }

    public void setPercentKM(int percentKM) {
        this.percentKM = percentKM;
    }

    private int quality;

    private byte[] mImageCart;

    public byte[] getImageCart() {
        return mImageCart;
    }

    public void setImageCart(byte[] imageCart) {
        mImageCart = imageCart;
    }

    public Food() {
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public static Creator<Food> getCREATOR() {
        return CREATOR;
    }

    protected Food(Parcel in) {
        idtypefood = in.readInt();
        image = in.readString();
        price = in.readInt();
        name = in.readString();
        information = in.readString();
        id = in.readInt();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public int getIdtypefood() {
        return idtypefood;
    }

    public void setIdtypefood(int idtypefood) {
        this.idtypefood = idtypefood;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idtypefood);
        parcel.writeString(image);
        parcel.writeInt(price);
        parcel.writeString(name);
        parcel.writeString(information);
        parcel.writeInt(id);
    }
}
