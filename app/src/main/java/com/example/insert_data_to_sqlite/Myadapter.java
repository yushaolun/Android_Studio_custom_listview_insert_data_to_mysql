package com.example.insert_data_to_sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Myadapter extends BaseAdapter {

    Context context;
    LinkedList<String> linklist = new LinkedList<String>();

    public Myadapter(MainActivity context, LinkedList<String> listItems) {  //傳入linklist
       this.context=context;
       this.linklist=listItems;


    }


    @Override
    public int getCount() {
        return this.linklist.size();
    }

    @Override
    public Object getItem(int i) {
        return linklist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.item,null);  //找xml檔Layout
        TextView textview = row.findViewById(R.id.textView);

        textview.setText(linklist.get(i));

        return row;
    }
}
