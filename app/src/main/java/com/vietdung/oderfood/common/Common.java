package com.vietdung.oderfood.common;

import com.vietdung.oderfood.remote.APIOderFood;
import com.vietdung.oderfood.remote.RetrofitCilent;

public class Common {
    public static final String Base_URL= "http://192.168.1.4/oderfood/";
    public static APIOderFood getAPI(){
        return RetrofitCilent.getClient(Base_URL).create(APIOderFood.class);

    }
}
