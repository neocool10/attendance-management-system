package com.test.attendancemanage;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class View_Attendance extends AppCompatActivity {
    ListView l1;
    String dept, year, subject, date;
    String f, l;
    String status = "P";
    String full_detail;
    int attendance_session_id;
    int student_id;
 TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SharedPreferences sharedpreferences = getSharedPreferences("my", Context.MODE_PRIVATE);

        int id = sharedpreferences.getInt("id", 0);
        dept = getIntent().getExtras().getString("department");
        year = getIntent().getExtras().getString("year");
        subject = getIntent().getExtras().getString("subject");
        date = getIntent().getExtras().getString("date");
String s ="";
        ArrayList<String> list = new ArrayList<String>();
        list.add("date     "+"subject" +" id"+"  status \n");
        l1 = findViewById(R.id.listView);

        db = dbHelper.getWritableDatabase();

        String query = "SELECT * FROM attendance_table where attendance_session_id = (SELECT  attendance_session_id FROM attendance_session_table where attendance_session_faculty_id='" + id + "' and attendance_session_department='" + dept + "' and attendance_session_class ='" + year + "' and attendance_session_date ='" + date + "' and attendance_session_subject='" + subject + "')";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                student_id = cursor.getInt(1);
                status = cursor.getString(2);
                s = s +date+" "+subject+"      "+student_id+ "       "  +status +"\n";
               list.add(s);

            } while (cursor.moveToNext());
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        l1.setAdapter(adapter);
    }
}

