package com.test.attendancemanage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, "Attendance", null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryFaculty="CREATE TABLE  faculty_table (faculty_id  INTEGER PRIMARY KEY AUTOINCREMENT, faculty_firstname  TEXT, faculty_Lastname  TEXT, faculty_mobilenumber  TEXT, faculty_address  TEXT, faculty_username  TEXT, faculty_password  TEXT )";
        String queryStudent="CREATE TABLE student_table  (student_id  INTEGER PRIMARY KEY AUTOINCREMENT, student_firstname  TEXT, student_lastname  TEXT, student_mobilenumber  TEXT, student_address  TEXT, student_department  TEXT, student_class  TEXT )";
        String queryAttendanceSession="CREATE TABLE  attendance_session_table(attendance_session_id  INTEGER PRIMARY KEY AUTOINCREMENT, attendance_session_faculty_id  INTEGER, attendance_session_department  TEXT, attendance_session_class  TEXT, attendance_session_date DATE, attendance_session_subject  TEXT)";
        String queryAttendance="CREATE TABLE attendance_table(attendance_session_id  INTEGER,attendance_student_id  INTEGER, attendance_status  TEXT )";
        try
        {
            db.execSQL(queryFaculty);
            db.execSQL(queryStudent);
            db.execSQL(queryAttendanceSession);
            db.execSQL(queryAttendance);
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        String queryFaculty="CREATE TABLE faculty_table(faculty_id  INTEGER PRIMARY KEY AUTOINCREMENT,faculty_firstname  TEXT, faculty_Lastname  TEXT, faculty_mobilenumber  TEXT, faculty_address  TEXT, faculty_username  TEXT, faculty_password  TEXT)";
        String queryStudent="CREATE TABLE student_table (student_id  INTEGER PRIMARY KEY AUTOINCREMENT, student_firstname  TEXT, student_lastname  TEXT, student_mobilenumber  TEXT, student_address  TEXT, student_department  TEXT, student_class  TEXT)";
        String queryAttendanceSession="CREATE TABLE attendance_session_table(attendance_session_id  INTEGER PRIMARY KEY AUTOINCREMENT, attendance_session_faculty_id  INTEGER, attendance_session_department  TEXT, attendance_session_class  TEXT, attendance_session_date TEXT, attendance_session_subject  TEXT)";
        String queryAttendance="CREATE TABLE attendance_table(attendance_session_id  INTEGER, attendance_student_id  INTEGER, attendance_status  TEXT)";
        try
        {
            db.execSQL(queryFaculty);
            db.execSQL(queryStudent);
            db.execSQL(queryAttendanceSession);
            db.execSQL(queryAttendance);
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
        }
    }
    public void add_att(String full_detail,String status)
    {

    }
}
