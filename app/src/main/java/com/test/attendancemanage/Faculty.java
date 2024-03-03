package com.test.attendancemanage;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Faculty extends AppCompatActivity implements  AdapterView.OnItemClickListener {
    ListView l1;
    String dept, year,subject,date;
    String f, l;
    String status="P";
  String full_detail;



    int student_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        l1 = (ListView) findViewById(R.id.listView);
        ArrayList<String> studentList = new ArrayList<String>();
        dept = getIntent().getExtras().getString("department");
        year = getIntent().getExtras().getString("year");
        subject = getIntent().getExtras().getString("subject");
        date = getIntent().getExtras().getString("date");

        String query = "SELECT * FROM student_table where student_department='" + dept + "' and student_class='" + year + "'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                student_id = cursor.getInt(0);
                f = cursor.getString(1);
                l = cursor.getString(2);
                studentList.add(student_id + " " + f + " " + l + "Fa ");


            } while (cursor.moveToNext());
        }
            SharedPreferences sharedpreferences = getSharedPreferences("my", Context.MODE_PRIVATE);

            int id = sharedpreferences.getInt("id",0);
            ContentValues values = new ContentValues();
            values.put("attendance_session_faculty_id",id);
            values.put("attendance_session_department",dept);
            values.put("attendance_session_class",year);
            values.put("attendance_session_date",date);
            values.put("attendance_session_subject ",subject);

            long n=db.insert("attendance_session_table", null, values);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, studentList);
        cursor.close();
            db.close();

            l1.setAdapter(adapter);
        l1.setOnItemClickListener(this);

    }
    public void onItemClick(AdapterView<?> a, View v,final int p,long i){
        full_detail = l1.getItemAtPosition(p).toString();
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//...........
        dialog.setContentView(R.layout.test_layout);
// set title and cancelable
        RadioGroup radioGroup;
        RadioButton present;
        RadioButton absent;
        radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGroup);
        present=(RadioButton)dialog.findViewById(R.id.PresentradioButton);
        absent=(RadioButton)dialog.findViewById(R.id.AbsentradioButton);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.PresentradioButton) {
                    status = "P";
                } else if(checkedId == R.id.AbsentradioButton) {
                    status = "A";
                } else {
                }
            }
        });
        Button b = (Button)dialog.findViewById(R.id.attendanceSubmitButton);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                DBHelper d = new DBHelper(Faculty.this);
                SQLiteDatabase db =d.getWritableDatabase();
                String query1= "select max(attendance_session_id) from attendance_session_table";
                Cursor cursor = db.rawQuery(query1, null);
                int sessionId=0,student_id;
                String full_detail;
                if(cursor.moveToFirst())
                    sessionId =cursor.getInt(0);

                cursor.close();
                full_detail= l1.getItemAtPosition(p).toString();
                String[] splited = full_detail.split(" ");
                student_id = Integer.parseInt(splited[0]);
                ContentValues values = new ContentValues();
                values.put("attendance_session_id",sessionId);
                values.put("attendance_student_id",student_id);
                values.put("attendance_status",status);
                long id=db.insert("attendance_table", null, values);
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.show();


    }



}
