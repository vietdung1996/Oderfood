package com.vietdung.oderfood.model.cart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DataFood extends SQLiteOpenHelper {
    public static String TB_CART = "CART";
    public static String TB_CART_ID_FOOD = "IDSP";
    public static String TB_CART_NAME_FOOD = "NAMESP";
    public static String TB_CART_PRICE = "PRICESP";
    public static String TB_CART_IMAGE = "IMAGESP";
    public static String TB_CART_QUALITY = "QUALITY";
    public static String TB_CART_PERCENT = "PERCENT";

    public static String TB_FAVORITE = "FAVORITE";
    public static String TB_FAVORTE_ID = "IDSP";
    public static String TB_FAVORITE_NAME_FOOD = "NAMESP";
    public static String TB_FAVORITE_PRICE = "PRICESP";
    public static String TB_FAVORITE_IMAGE = "IMAGESP";

    public DataFood(@Nullable Context context) {
        super(context, "SQLFOOD", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tbCart = "Create table " + TB_CART + " (" + TB_CART_ID_FOOD + " INTEGER PRIMARY KEY, "
                + TB_CART_NAME_FOOD + " TEXT, " + TB_CART_PRICE + " REAL, " + TB_CART_IMAGE + " BLOB, "
                + TB_CART_QUALITY + " INTEGER, " + TB_CART_PERCENT + " INTERGER);";

        String TBFAVORITE = "Create table " + TB_FAVORITE + " (" + TB_FAVORTE_ID + " INTEGER PRIMARY KEY, "
                + TB_FAVORITE_NAME_FOOD + " TEXT, " + TB_FAVORITE_PRICE + " REAL, " + TB_FAVORITE_IMAGE + " TEXT);";
        sqLiteDatabase.execSQL(tbCart);
        sqLiteDatabase.execSQL(TBFAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
