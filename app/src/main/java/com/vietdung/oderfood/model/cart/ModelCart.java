package com.vietdung.oderfood.model.cart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vietdung.oderfood.model.ObjectClass.Food;

import java.util.ArrayList;
import java.util.List;

public class ModelCart {
    SQLiteDatabase mDatabase;

    public void conectSQL(Context context) {
        DataFood dataFood = new DataFood(context);
        mDatabase = dataFood.getWritableDatabase();
    }

    public boolean addCart(Food food) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataFood.TB_CART_ID_FOOD, food.getId());
        contentValues.put(DataFood.TB_CART_NAME_FOOD, food.getName());
        contentValues.put(DataFood.TB_CART_PRICE, food.getPrice());
        contentValues.put(DataFood.TB_CART_IMAGE, food.getImageCart());
        contentValues.put(DataFood.TB_CART_QUALITY,food.getQuality());
        contentValues.put(DataFood.TB_CART_PERCENT,food.getPercentKM());
        long id = mDatabase.insert(DataFood.TB_CART, null, contentValues);
        if (id > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Food> getFoodCart(){
        List<Food> foods = new ArrayList<>();
        String query = "SELECT * FROM "+DataFood.TB_CART;
        Cursor cursor = mDatabase.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(cursor.getColumnIndex(DataFood.TB_CART_ID_FOOD));
            String name = cursor.getString(cursor.getColumnIndex(DataFood.TB_CART_NAME_FOOD));
            int price = cursor.getInt(cursor.getColumnIndex(DataFood.TB_CART_PRICE));
            byte[] image = cursor.getBlob(cursor.getColumnIndex(DataFood.TB_CART_IMAGE));
            int quality = cursor.getInt(cursor.getColumnIndex(DataFood.TB_CART_QUALITY));
            int percent = cursor.getInt(cursor.getColumnIndex(DataFood.TB_CART_PERCENT));
            Food food = new Food();
            food.setId(id);
            food.setName(name);
            food.setImageCart(image);
            food.setPrice(price);
            food.setQuality(quality);
            food.setPercentKM(percent);
            foods.add(food);
            cursor.moveToNext();
        }
        return foods;
    }

    public boolean deleteFood(int idFood){
        int check = mDatabase.delete(DataFood.TB_CART,DataFood.TB_CART_ID_FOOD +"="+idFood,null);
        if(check>0){
            return true;
        }else {
            return false;
        }
    }

    public boolean updateQuality(int idFood, int quality){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataFood.TB_CART_QUALITY,quality);
        int id = mDatabase.update(DataFood.TB_CART,contentValues,DataFood.TB_CART_ID_FOOD +" = "+ idFood,null);
        if(id>0){
            return true;
        }else {
            return false;
        }
    }

}
