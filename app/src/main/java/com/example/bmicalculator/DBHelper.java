package com.example.bmicalculator;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context,"Userdata.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(date String ,age INT,height INT,weight INT,bmi FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insert(int age,int weight,double height,double bmi){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        Date currentTime = Calendar.getInstance().getTime();
       // android.text.format.DateFormat df = new android.text.format.DateFormat();
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
       int year = c.get(Calendar.YEAR);
       String date = day + "/" + (month+1) + "/" + year;
        contentValues.put("date","11/11/2020");
        contentValues.put("age",age);
        contentValues.put("height",height);
        contentValues.put("weight",weight);
        contentValues.put("bmi",bmi);
        long result=DB.insert("Userdetails",null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor view() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor3 = DB.rawQuery("select * from Userdetails ", null);
        return  cursor3;
    }

}

