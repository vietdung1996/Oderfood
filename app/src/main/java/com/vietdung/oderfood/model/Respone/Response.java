package com.vietdung.oderfood.model.Respone;

import com.google.gson.annotations.SerializedName;

public class Response {
    @SerializedName("ketqua")
    private String result;

    public Response() {

    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
