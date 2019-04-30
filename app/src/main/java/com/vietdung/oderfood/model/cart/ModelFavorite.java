package com.vietdung.oderfood.model.cart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vietdung.oderfood.model.ObjectClass.Food;

import java.util.ArrayList;
import java.util.List;

public class ModelFavorite {
    SQLiteDatabase mDatabase;

    public void conectSQL(Context context) {
        DataFood dataFood = new DataFood(context);
        mDatabase = dataFood.getWritableDatabase();
    }

    public boolean addFavorite(Food food) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataFood.TB_FAVORTE_ID, food.getId());
        contentValues.put(DataFood.TB_FAVORITE_NAME_FOOD, food.getName());
        contentValues.put(DataFood.TB_FAVORITE_PRICE, food.getPrice());
        contentValues.put(DataFood.TB_FAVORITE_IMAGE, food.getImage());
        long id = mDatabase.insert(DataFood.TB_FAVORITE, null, contentValues);
        if (id > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Food> getFoodFavorite() {
        List<Food> foods = new ArrayList<>();
        String query = "SELECT * FROM " + DataFood.TB_FAVORITE;
        Cursor cursor = mDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(DataFood.TB_FAVORTE_ID));
            String name = cursor.getString(cursor.getColumnIndex(DataFood.TB_FAVORITE_NAME_FOOD));
            int price = cursor.getInt(cursor.getColumnIndex(DataFood.TB_FAVORITE_PRICE));
            //byte[] image = cursor.getBlob(cursor.getColumnIndex(DataFood.TB_FAVORITE_IMAGE));
            String image = cursor.getString(cursor.getColumnIndex(DataFood.TB_FAVORITE_IMAGE));
            Food food = new Food();
            food.setId(id);
            food.setName(name);
            food.setImage(image);
            food.setPrice(price);
            foods.add(food);
            cursor.moveToNext();
        }
        return foods;
    }

    public boolean checkFood(Food food) {
        String query = "SELECT EXISTS(SELECT 1 FROM " + DataFood.TB_FAVORITE + " WHERE "
                + DataFood.TB_FAVORTE_ID + " = " + food.getId();
       // mDatabase.rawQuery(query,null);
        return false;
    }

    public boolean deleteFood(int idFood) {
        int check = mDatabase.delete(DataFood.TB_FAVORITE, DataFood.TB_FAVORTE_ID + "=" + idFood, null);
        if (check > 0) {
            return true;
        } else {
            return false;
        }
    }
}
