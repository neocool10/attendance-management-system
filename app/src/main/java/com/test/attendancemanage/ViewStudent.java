package com.test.attendancemanage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class ViewStudent extends AppCompatActivity {
    Spinner department,year;
    String d,y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        department=(Spinner)findViewById(R.id.department);
        year=(Spinner)findViewById(R.id.year);
    }
    public void Next(View v)
    {
        d =(String) department.getSelectedItem();
        y =(String) year.getSelectedItem();
        Intent intent = new Intent(this,ViewStudentByBranchYear.class);
        intent.putExtra("branch", d);
        intent.putExtra("year", y);
        startActivity(intent);
    }
}