package com.example.stepapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseHelper extends SQLiteOpenHelper {

    final String user_table = "USER_TABLE";
    final String id = "id";
    final static String userName = "userName";
    final String step_table = "STEP_TABLE";
    final String date = "date";
    final String steps = "steps";

    public databaseHelper( Context context) {
        super(context, "User.db", null, 1);
    }

    //create two tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        //stores an id and username
        String createTable = "CREATE TABLE " + user_table + "( " + id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + userName + " TEXT UNIQUE);";
        db.execSQL(createTable);

        //stores the date and number of steps and an id to link to the user table
        createTable = "CREATE TABLE " + step_table + "(" + id + " INTEGER, " + date + " TEXT, " + steps + " INTEGER, FOREIGN KEY(id) REFERENCES USER_TABLE(id));";
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
        cv.put(userName, name);

        //insert to table
        long insert = db.insert(user_table, null, cv);

        //check if it inserted successfully
        if(insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

    //add steps to the table
    public boolean addSteps(int numSteps){

        //get editable database
        SQLiteDatabase db = this.getWritableDatabase();

        //add data to be inserted
        ContentValues cv = new ContentValues();
        cv.put(steps, numSteps);

        //TODO pass id and date
        cv.put(id, 0);
        cv.put(date, 0);

        //insert to table
        long insert = db.insert(step_table, null, cv);

        //check if it inserted successfully
        if(insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public int getTotalSteps(){
        int totalSteps = 0;

        //get editable database
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT SUM(steps) FROM " + step_table + " WHERE id = 0";

        Cursor cursor = db.rawQuery(query, null);
        final boolean b = cursor.moveToNext();
        if(b){
            totalSteps = cursor.getInt(0);
        }
        return totalSteps;
    }
}
