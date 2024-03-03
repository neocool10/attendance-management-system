package com.test.attendancemanage;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Add_Student extends AppCompatActivity {

    EditText Person1;
    EditText Last1;

    EditText Contact1;
    EditText Address1;
    Spinner department, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        department = (Spinner) findViewById(R.id.spinner2);
        year = (Spinner) findViewById(R.id.spinner3);
        Person1 = (EditText) findViewById(R.id.Person);
        Last1 = (EditText) findViewById(R.id.Last);
        Contact1 = (EditText) findViewById(R.id.Contact);
        Address1 = (EditText) findViewById(R.id.Address);

    }

    public void submit(View v) {
        String first_name = Person1.getText().toString();
        String last_name = Last1.getText().toString();
        String phone_no = Contact1.getText().toString();
        String address = Address1.getText().toString();
        String dept = department.getSelectedItem().toString();
        String y = year.getSelectedItem().toString();
        if (TextUtils.isEmpty(first_name)) {
            Person1.setError("please enter firstname");
        } else if (TextUtils.isEmpty(last_name)) {
            Last1.setError("please enter lastname");
        } else if (TextUtils.isEmpty(phone_no)) {
            Contact1.setError("please enter phoneno");
        } else if (TextUtils.isEmpty(address)) {
            Address1.setError("enter address");
        } else {

            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("student_firstname",first_name);
            values.put("student_lastname",last_name);
            values.put("student_mobilenumber",phone_no);
            values.put("student_address",address);
            values.put("student_department",dept);
            values.put("student_class",y);
            long id=db.insert("student_table", null, values);

            db.close();

            Intent intent = new Intent(this, Admin.class);
            startActivity(intent);
            Toast.makeText(this, "student added successfully", Toast.LENGTH_SHORT).show();

        }
    }
}