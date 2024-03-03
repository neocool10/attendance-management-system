package com.test.attendancemanage;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewFaculty extends AppCompatActivity {
    ListView l1 ;
    String f,l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_faculty);
        l1=(ListView)findViewById(R.id.listview);
        ArrayList<String> facultyList = new ArrayList<String>();
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM faculty_table";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            do{

                f=  cursor.getString(1);
                l =cursor.getString(2);
                facultyList.add(f+ " "+l);
            }while(cursor.moveToNext());
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,facultyList);
        l1.setAdapter(adapter);
    }
}