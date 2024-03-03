package com.test.attendancemanage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    EditText e1, e2;
    Spinner spin;
  DBHelper d;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        spin = (Spinner) findViewById(R.id.spinner);
        e1 = findViewById(R.id.editText);
        e2 = findViewById(R.id.editText3);
        d= new DBHelper(this);
        sharedpreferences = getSharedPreferences("my", Context.MODE_PRIVATE);
    }

    public void login(View v) {
        String s = spin.getSelectedItem().toString();
        String user = e1.getText().toString();
        String pass = e2.getText().toString();

        if (TextUtils.isEmpty(pass)) {
            e1.setError("Invalid User Name");
        } else if (TextUtils.isEmpty(pass)) {
            e2.setError("enter password");
        }
        if (s.equals("admin")) {
            if (user.equals("admin") && pass.equals("123")) {
                Intent i = new Intent(this, Admin.class);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("name",s);
                editor.commit();
                startActivity(i);
            } else {
                Toast.makeText(this, "Login failed", Toast.LENGTH_LONG).show();
            }
        }
        else {
            SQLiteDatabase db = d.getWritableDatabase();

            String query = "SELECT * FROM faculty_table where faculty_username='" + user + "' and faculty_password='" + pass + "'";
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                Intent i = new Intent(this, AddAttendance.class);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                 int id = cursor.getInt(0);
                editor.putInt("id",id);
                editor.commit();
                startActivity(i);
            }
            else {
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
