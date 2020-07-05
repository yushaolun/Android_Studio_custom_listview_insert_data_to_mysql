package com.example.insert_data_to_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;


public class MyDB extends SQLiteOpenHelper {
    final static String database_name="db_data_2";
    String table_name="tb_table_2";
    String id="_id";
    String name="name";
    String phone="phone";
    String interest="interest";
    SQLiteDatabase db;
    private MyDB helper = null;

    public MyDB(Context context)
    {
        super(context,database_name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable="CREATE TABLE IF NOT EXISTS "+table_name+"(_id INTEGER PRIMARY KEY AUTOINCREMENT ,name VARCHER(32),"+"phone VARCHER(16),"+"interest VARCHER(16))";//新增資料表

        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }






    public MyDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }




    public void close(){
        db.close();
    }







}
