package com.vietdung.oderfood.model.ObjectClass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Comment implements Parcelable {
    @SerializedName("IDCM")
    private String idCM;

    @SerializedName("IDFOOD")
    private int idFood;

    @SerializedName("NAMETB")
    private String nameDevice;

    @SerializedName("TITLE")
    private String title;

    @SerializedName("CONTENT")
    private String content;

    @SerializedName("STAR")
    private int star;

    @SerializedName("DATECM")
    private String time;

    public Comment(String idCM, int idFood, String nameDevice, String title, String content, int star) {
        this.idCM = idCM;
        this.idFood = idFood;
        this.star = star;
        this.nameDevice = nameDevice;
        this.title = title;
        this.content = content;
    }

    public Comment(String idCM, int idFood, String nameDevice, String title, String content, int star, String time) {
        this.idCM = idCM;
        this.idFood = idFood;
        this.nameDevice = nameDevice;
        this.title = title;
        this.content = content;
        this.star = star;
        this.time = time;
    }

    protected Comment(Parcel in) {
        idCM = in.readString();
        idFood = in.readInt();
        nameDevice = in.readString();
        title = in.readString();
        content = in.readString();
        star = in.readInt();
        time = in.readString();
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIdCM() {
        return idCM;
    }

    public void setIdCM(String idCM) {
        this.idCM = idCM;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getNameDevice() {
        return nameDevice;
    }

    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idCM);
        parcel.writeInt(idFood);
        parcel.writeString(nameDevice);
        parcel.writeString(title);
        parcel.writeString(content);
        parcel.writeInt(star);
        parcel.writeString(time);
    }
}
