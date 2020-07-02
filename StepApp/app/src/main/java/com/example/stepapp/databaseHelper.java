package com.example.stepapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseHelper extends SQLiteOpenHelper {

    public databaseHelper( Context context) {
        super(context, "User.db", null, 1);
    }

    //create two tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        //stores an id and username
        String createTable = "CREATE TABLE USER_TABLE( id INTEGER PRIMARY KEY AUTOINCREMENT, userName TEXT UNIQUE);";
        db.execSQL(createTable);

        //stores the date and number of steps and an id to link to the user table
        createTable = "CREATE TABLE STEP_TABLE(id INTEGER, date TEXT, steps INTEGER, FOREIGN KEY(id) REFERENCES USER_TABLE(id));";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    //adds a user to the table;
    public boolean addUser(String name){

        //get editable database
        SQLiteDatabase db = this.getWritableDatabase();

        //add data to be inserted
        ContentValues cv = new ContentValues();
        cv.put("userName", name);

        //insert to table
        long insert = db.insert("USER_TABLE", null, cv);

        //check if it inserted successfully
        if(insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

    //add steps to the table
    public boolean addSteps(int steps){

        //get editable database
        SQLiteDatabase db = this.getWritableDatabase();

        //add data to be inserted
        ContentValues cv = new ContentValues();
        cv.put("steps", steps);

        //insert to table
        long insert = db.insert("STEP_TABLE", null, cv);

        //check if it inserted successfully
        if(insert == -1){
            return false;
        }
        else{
            return true;
        }
    }
}
