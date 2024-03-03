package com.test.attendancemanage;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddAttendance extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    int y1, m1, d1;
EditText e;
Spinner s1,s2,s3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendance);
        e = findViewById(R.id.editText);
        s1 = findViewById(R.id.spinner1);
        s2 = findViewById(R.id.spinner2);
        s3 = findViewById(R.id.spinner3);
    }

    public void go(View v) {
        final Calendar c = Calendar.getInstance();
        y1 = c.get(Calendar.YEAR);
        m1 = c.get(Calendar.MONTH);
        d1 = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, this, y1, m1, d1);
        dialog.show();
    }

    public void onDateSet(DatePicker view, int y, int m, int d) {
        e.setText(d + "-" + (m + 1) + "-" + y);
    }
    public void add(View v)
    {
        Intent intent = new Intent(this,Faculty.class);
        intent.putExtra("department", s1.getSelectedItem().toString());
        intent.putExtra("year", s2.getSelectedItem().toString());
        intent.putExtra("subject", s3.getSelectedItem().toString());
        intent.putExtra("date", e.getText().toString());
        startActivity(intent);
    }
    public void view(View v)
    {
        Intent intent = new Intent(this,View_Attendance.class);
        intent.putExtra("department", s1.getSelectedItem().toString());
        intent.putExtra("year", s2.getSelectedItem().toString());
        intent.putExtra("subject", s3.getSelectedItem().toString());
        intent.putExtra("date", e.getText().toString());
        startActivity(intent);
    }
    public void logout(View v)
    {
        SharedPreferences sharedpreferences = getSharedPreferences("my", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        Intent i = new Intent(this,MainActivity2.class);
        startActivity(i);


    }
}