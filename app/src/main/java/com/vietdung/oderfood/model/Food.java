package com.vietdung.oderfood.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Food implements Parcelable{
    @SerializedName("idtypefood")
    private String idtypefood;
    @SerializedName("image")
    private String image;
    @SerializedName("price")
    private String price;
    @SerializedName("name")
    private String name;
    @SerializedName("information")
    private String information;
    @SerializedName("id")
    private String id;

    protected Food(Parcel in) {
        idtypefood = in.readString();
        image = in.readString();
        price = in.readString();
        name = in.readString();
        information = in.readString();
        id = in.readString();
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

    public String getIdtypefood() {
        return idtypefood;
    }

    public void setIdtypefood(String idtypefood) {
        this.idtypefood = idtypefood;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idtypefood);
        parcel.writeString(image);
        parcel.writeString(price);
        parcel.writeString(name);
        parcel.writeString(information);
        parcel.writeString(id);
    }
}
