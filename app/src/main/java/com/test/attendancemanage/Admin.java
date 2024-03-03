package com.test.attendancemanage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
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

    public void add(View v)
    {
        Intent i = new Intent(this,Add_Student.class);
        startActivity(i);

    }
    public void view(View v)
    {
        Intent i = new Intent(this,ViewStudent.class);
        startActivity(i);

    }
    public void add_faculty(View v)
    {
        Intent i = new Intent(this,Add_Faculty.class);
        startActivity(i);

    }
    public void view_faculty(View v)
    {
        Intent i = new Intent(this,ViewFaculty.class);
        startActivity(i);

    }
}