package com.test.attendancemanage;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Add_Faculty extends AppCompatActivity {
    Button registerButton;
    EditText textFirstName;
    EditText textLastName;
    EditText textcontact;
    EditText textaddress;
    EditText textusername;
    EditText textpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);
        textFirstName=(EditText)findViewById(R.id.editText);
        textLastName=(EditText)findViewById(R.id.editText2);
        textcontact=(EditText)findViewById(R.id.editText3);
        textaddress=(EditText)findViewById(R.id.editText4);
        textusername=(EditText)findViewById(R.id.editText5);
        textpassword=(EditText)findViewById(R.id.editText6);
        registerButton=(Button)findViewById(R.id.button12);

    }
public void submit(View v)
{
    String first_name = textFirstName.getText().toString();
    String last_name = textLastName.getText().toString();
    String phone_no = textcontact.getText().toString();
    String address = textaddress.getText().toString();
    String userName = textusername.getText().toString();
    String passWord = textpassword.getText().toString();

    if (TextUtils.isEmpty(first_name)) {
        textFirstName.setError("please enter firstname");
    }
    else if (TextUtils.isEmpty(last_name)) {
        textLastName.setError("please enter lastname");
    }
    else if (TextUtils.isEmpty(phone_no)) {
        textcontact.setError("please enter phoneno");
    }

    else if (TextUtils.isEmpty(address)) {
        textaddress.setError("enter address");
    }
    else if (TextUtils.isEmpty(userName)) {
        textcontact.setError("please enter username");
    }
    else if (TextUtils.isEmpty(passWord)) {
        textaddress.setError("enter password");
    }
    else {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("faculty_firstname",first_name);
        values.put("faculty_Lastname",last_name);
        values.put("faculty_mobilenumber",phone_no);
        values.put("faculty_address",address);
        values.put("faculty_username",userName);
        values.put("faculty_password",passWord);
        long id=db.insert("faculty_table", null, values);


        db.close();
        Intent intent =new Intent(this,Admin.class);
        startActivity(intent);
        if(id>0)
        Toast.makeText(getApplicationContext(), "Faculty added successfully", Toast.LENGTH_SHORT).show();
    }
    }
}