package com.lionsquare.registros;

/**
 * Created by anupamchugh on 19/10/15.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "COUNTRIES";

    // Table columns
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String TEL = "tel";
    public static final String ADDRESS = "address";
    public static final String CURP = "curp";
    public static final String RFC = "rfc";
    public static final String NSC = "nds";
    public static final String AFORE = "afore";

    // Database Information
    static final String DB_NAME = "AGENDA_PROFUTURO.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + TEL + " TEXT," + ADDRESS + " TEXT ," + CURP + " TEXT ," + RFC + " TEXT, " + NSC + " TEXT, " + AFORE + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public   SQLiteDatabase getContextlist() {

        SQLiteDatabase context = this.getWritableDatabase();
        return context;
    }
}
