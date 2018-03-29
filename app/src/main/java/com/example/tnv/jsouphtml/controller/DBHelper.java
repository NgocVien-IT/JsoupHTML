package com.example.tnv.jsouphtml.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tnv.jsouphtml.model.DBEnglish;
import com.example.tnv.jsouphtml.model.English;
import com.example.tnv.jsouphtml.model.EnglishDetails;

/**
 * Created by TNV on 3/27/2018.
 */

public class DBHelper extends SQLiteOpenHelper {
    private final String TAG = getClass().getName() ;
    private  Context mContext;
    private static final int VERSION = 1;
    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DBEnglish.DBNAME, null, VERSION);
        this.mContext = context;
    }

    private static final String CREATE_TABLE_ENGLISH ="CREATE TABLE "+DBEnglish.TABLENAME+"( " +
            DBEnglish.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            DBEnglish.NAME +" text , "+
            DBEnglish.IMAGE+" text, "+
            DBEnglish.URL + " text "+
            " )";

    private static final String CREATE_TABLE_DETAILS ="CREATE TABLE "+DBEnglish.TABLENAMEDETAILS+"( " +
            DBEnglish.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            DBEnglish.WORD +" text , "+
            DBEnglish.IMAGE+" text, "+
            DBEnglish.EASYREAD +" text , "+
            DBEnglish.CATEGORY+" text, "+
            DBEnglish.EXAMPLE +" text , "+
            DBEnglish.TRANSLATE+" text"+
            " )";

    private final String DROPTABLE = "DROP TABLE IF EXISTS "+DBEnglish.TABLENAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ENGLISH);
        Log.e(TAG, "Tao Table English thanh cong" );
        db.execSQL(CREATE_TABLE_DETAILS);
        Log.e(TAG, "Tao Table Details thanh cong" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROPTABLE);
        onCreate(sqLiteDatabase);
    }

    public void insertIntoData(English english){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBEnglish.NAME,english.getmName());
        values.put(DBEnglish.IMAGE,english.getmUrlImage());
        values.put(DBEnglish.URL,english.getmUrlNextPage());
        db.insert(DBEnglish.TABLENAME,null,values);
    }
    public Cursor getAllEnglish(){
        db = this.getReadableDatabase();
        String[] projection = {DBEnglish.ID,DBEnglish.NAME,DBEnglish.IMAGE,DBEnglish.URL};
        return db.query(DBEnglish.TABLENAME,projection,null,null,null,null,null);
    }

    public void insertIntoDataDetails(EnglishDetails englishDetails){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBEnglish.WORD,englishDetails.getmWord());
        values.put(DBEnglish.IMAGE,englishDetails.getmImage());
        values.put(DBEnglish.EASYREAD,englishDetails.getmEasyRead());
        values.put(DBEnglish.CATEGORY,englishDetails.getmCategory());
        values.put(DBEnglish.EXAMPLE,englishDetails.getmExample());
        values.put(DBEnglish.TRANSLATE,englishDetails.getmTranslate());

        db.insert(DBEnglish.TABLENAMEDETAILS,null,values);
    }
    public Cursor getAllDetails(){
        db = this.getReadableDatabase();
        String[] projection = {DBEnglish.ID,DBEnglish.WORD,DBEnglish.IMAGE,DBEnglish.EASYREAD,
        DBEnglish.CATEGORY,DBEnglish.EXAMPLE,DBEnglish.TRANSLATE};
        return db.query(DBEnglish.TABLENAMEDETAILS,projection,null,null,null,null,null);
    }
}
