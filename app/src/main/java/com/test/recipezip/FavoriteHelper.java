package com.test.recipezip;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.test.recipezip.model.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavoriteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "favorite.db";
    public static final String TABLE_NAME = "favorite";
    public static final String key = "id";
    public static final String COL_1 = "user_id";
    public static final String COL_2 = "recipe";

    public FavoriteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 10);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME +" (id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, recipe TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean isFavorite(Integer user, String recipe) {
        String[] columns = {COL_1, COL_2};
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COL_1 + "=?" + " and " + COL_2 + "=?";
        String[] selectionArgs = {user.toString(), recipe};
        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null,
                null, null);
        int count = cursor.getCount();
        Log.d("is favourite", recipe + " " + user +" "+ count);
        cursor.close();
        return count > 0;
    }

    public Map<String, Boolean> areFavorites(Integer user, List<String> recipes) {
        String[] columns = {COL_1, COL_2};
        Map<String, Boolean> result = new HashMap<>();
        String recipesString = "(";
        for (int i = 0; i < recipes.size(); i++) {
            result.put(recipes.get(i), false);
            recipesString += recipes.get(i);
            if (i < recipes.size() -1) {
                recipesString += ",";
            }
        }
        recipesString += ")";
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COL_1 + "=?" + " and " + COL_2 + "in ?";

        String[] selectionArgs = {user.toString(), recipesString};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null,
                null, null);
        while(cursor.moveToNext()) {
            String rec = cursor.getString(cursor.getColumnIndexOrThrow(COL_2));
            result.put(rec, true);
        }
        cursor.close();
        return result;
    }

    public boolean addFavorite(Integer user, String recipe) {
       if (isFavorite(user, recipe)) {
           return false;
       }
       SQLiteDatabase db = this.getWritableDatabase();
       ContentValues values = new ContentValues();
       values.put(COL_1, user);
       values.put(COL_2, recipe);
       db.insert(TABLE_NAME, null, values);
       Log.d("favorite!!!", "add favorite "+user.toString() + " " + recipe);
       return true;
    }

    public boolean removeFavorite(Integer user, String recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COL_1 + "=?" + " and " + COL_2 + "=?";

        String[] selectionArgs = {user.toString(), recipe};
        boolean result = db.delete(TABLE_NAME, selection, selectionArgs) > 0;

        Log.d("favorite!!!", "delete favorite "+user.toString() + " " + recipe + " " + result);
        return result;
    }

    public List<Recipe> getAllFavorite(Integer user) {
        String[] columns = {COL_2};
        List<Recipe> results = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COL_1 + "=?";

        String[] selectionArgs = {user.toString()};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null,
                null, null);
        while(cursor.moveToNext()) {
            String rec = cursor.getString(cursor.getColumnIndexOrThrow(COL_2));
            results.add(new Recipe(rec));
        }
        cursor.close();
        return results;
    }
}
