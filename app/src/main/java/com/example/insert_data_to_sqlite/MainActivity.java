package com.example.insert_data_to_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    LinkedList<String> listItems = new LinkedList<String>();


    TextView te,te1, te2, te3;
    EditText ed1, ed2, ed3;
    ListView listview;



    SQLiteDatabase db;
    MyDB helper;


    String table;

    String name, phone, interest;
    int position;

    public Cursor cursor;
    Myadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper=new MyDB(this.getApplicationContext());


        te=findViewById(R.id.textView);
        te1 = findViewById(R.id.textView1);
        te2 = findViewById(R.id.textView2);
        te3 = findViewById(R.id.textView3);


        ed1 = findViewById(R.id.editText1);
        ed2 = findViewById(R.id.editText2);
        ed3 = findViewById(R.id.editText3);

        listview = findViewById(R.id.listview);


        table=helper.table_name; //資料表


        db=helper.getReadableDatabase(); //讀取資料庫

        Cursor cursor=db.rawQuery("SELECT * FROM tb_table_2",null); //把資料表所有內容抓出來

        listItems.clear();
        while (cursor.moveToNext()){
            String name=cursor.getString(1);
            listItems.add(name);

            Log.v("str",""+listItems);
        }

        adapter=new Myadapter(this,listItems); //傳入
        listview.setAdapter(adapter);
        
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {   //讀取觸碰位置
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 position=i;
            }
        });

    }


    public void add(View view) {  //新增資料到資料庫
        db=helper.getWritableDatabase(); //寫入資料庫
        name=ed1.getEditableText().toString();
        phone=ed2.getEditableText().toString();
        interest=ed3.getEditableText().toString();


        ContentValues cv=new ContentValues(3);
        cv.put("name",name);
        cv.put("phone",phone);
        cv.put("interest",interest);
        db.insert("tb_table_2",null,cv);

        Cursor cursor=db.rawQuery("SELECT * FROM tb_table_2",null); //把資料表所有內容抓出來


        listItems.clear();
        while (cursor.moveToNext()){
            String name=cursor.getString(1);
            listItems.add(name);

            Log.v("str",""+listItems);
        }



         adapter=new Myadapter(this,listItems);
         listview.setAdapter(adapter);

    }





    public void delete(View view) { //刪除

        listItems.clear();   //清空listview內容
        db=helper.getWritableDatabase();
        name=ed1.getEditableText().toString();
        phone=ed2.getEditableText().toString();
        interest=ed3.getEditableText().toString();

        db.execSQL("DELETE FROM " + table + " WHERE _id= "+position+"");


        Log.v("po",""+position);

        Cursor cursor=db.rawQuery("SELECT * FROM tb_table_2",null); //把資料表所有內容抓出來


        while (cursor.moveToNext()){
            String name=cursor.getString(1);
            listItems.add(name);

            Log.v("str",""+listItems);
        }

        listview.setAdapter(adapter);
    }



    public void advise(View view) { //修改內容
        listItems.clear();
        db=helper.getReadableDatabase();
        name=ed1.getEditableText().toString();
        db.execSQL("DELETE FROM " + table + " WHERE name= '"+name+"'");


        ContentValues cv=new ContentValues(3);
        db=helper.getWritableDatabase();
        name=ed1.getEditableText().toString();
        phone=ed2.getEditableText().toString();
        interest=ed3.getEditableText().toString();

        cv.put("name",name);
        cv.put("phone",phone);
        cv.put("interest",interest);
        db.insert(table,null,cv);



        Cursor cursor=db.rawQuery("SELECT * FROM tb_table_2",null); //把資料表所有內容抓出來
        while (cursor.moveToNext()){
            String name=cursor.getString(1);
            listItems.add(name);

            Log.v("str",""+listItems);
        }
        listview.setAdapter(adapter);


    }












}