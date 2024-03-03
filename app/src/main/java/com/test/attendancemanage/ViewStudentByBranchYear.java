package com.test.attendancemanage;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewStudentByBranchYear extends AppCompatActivity {
    ListView l1 ;
 String dept,year;
 String f,l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_by_branch_year);
        l1=(ListView)findViewById(R.id.listview);
        ArrayList<String> studentList = new ArrayList<String>();
DBHelper dbHelper = new DBHelper(this);
        dept=getIntent().getExtras().getString("branch");
        year =getIntent().getExtras().getString("year");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM student_table where student_department='"+dept+"' and student_class='"+year+"'";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            do{

               f=  cursor.getString(1);
                l =cursor.getString(2);
                studentList.add(f+ " "+l);
            }while(cursor.moveToNext());
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,studentList);
        l1.setAdapter(adapter);

    }
}