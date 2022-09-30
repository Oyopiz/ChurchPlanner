package com.elitechinc.churchplanner.Classes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


//  class to set conncetion with database named USERDB details
public class databaseMovieDetailsClass {
    private static SQLiteDatabase wdb = null;
    private static userDB db = null;
    public static SQLiteDatabase getWritable(Context context) {
        if (db == null)
            db = new userDB(context);

        if (wdb == null)
            wdb = db.getWritableDatabase();

        return wdb;
    }






}
