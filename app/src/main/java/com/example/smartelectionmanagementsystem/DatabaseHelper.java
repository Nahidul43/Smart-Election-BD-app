package com.example.smartelectionmanagementsystem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseHelper extends SQLiteAssetHelper {
    public DatabaseHelper(Context context) {

        super(context, "info.db", null, 1);

    }

    public Cursor getData(String result){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("select * from Ylection where nid like "+result,null);
        return cursor;


    }





}
