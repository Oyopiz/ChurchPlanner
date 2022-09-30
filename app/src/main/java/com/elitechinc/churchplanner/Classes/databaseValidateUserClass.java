package com.elitechinc.churchplanner.Classes;


import static android.content.ContentValues.TAG;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class databaseValidateUserClass {
//
    private static SQLiteDatabase wdb = null;
    private static userDB db = null;
    public static SQLiteDatabase getWritable(Context context) {
        if (db == null)
            db = new userDB(context);

        if (wdb == null)
            wdb = db.getWritableDatabase();

        return wdb;

    }

    // method is called while validating user during LOGIN
    public static boolean checkUser(String userName,String email,String selectQuery) {

        Cursor cursor = wdb.rawQuery(selectQuery, null);
        boolean res = false;
        if (cursor.moveToFirst()) {
            do {
                String s1 = cursor.getString(0);
                Log.d(TAG, "checkUser: "+s1);
                String s2 = cursor.getString(1);
                if (s1.equals(userName) && s2.equals(email))  // check if username and email already exist in DB

                {
                    res = true;
                    break;

                }


            } while (cursor.moveToNext());
        }



        return res;
    }


    // method used to validate data during registration
    //only name should be unique to register a user
    public static boolean checkUser1 (String userName,String email,String selectQuery) {

        Cursor cursor = wdb.rawQuery(selectQuery, null);
        boolean res = false;
        if (cursor.moveToFirst()) {
            do {
                String s1 = cursor.getString(0);
                Log.d(TAG, "checkUser1: " + s1);
                String s2 = cursor.getString(1);
                if (s1.equals(userName))

                {
                    res = true;
                    break;

                }


            } while (cursor.moveToNext());
        }
    return res;
    }


// close database connection

    public static void close() {
        if (db != null)
            db.close();
    }

}


