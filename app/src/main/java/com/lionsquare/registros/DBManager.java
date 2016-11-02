package com.lionsquare.registros;

/**
 * Created by anupamchugh on 19/10/15.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.lionsquare.registros.Beans.Person;

import java.util.ArrayList;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String tel, String address, String curp, String rfc, String nsc, String afore) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.TEL, tel);
        contentValue.put(DatabaseHelper.ADDRESS, address);
        contentValue.put(DatabaseHelper.CURP, curp);
        contentValue.put(DatabaseHelper.RFC, rfc);
        contentValue.put(DatabaseHelper.NSC, nsc);
        contentValue.put(DatabaseHelper.AFORE, afore);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[]{DatabaseHelper._ID, DatabaseHelper.NAME,
                DatabaseHelper.TEL, DatabaseHelper.ADDRESS, DatabaseHelper.CURP,
                DatabaseHelper.RFC, DatabaseHelper.NSC, DatabaseHelper.AFORE};

        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String tel, String address, String curp, String rfc, String nsc, String afore) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.TEL, tel);
        contentValues.put(DatabaseHelper.ADDRESS, address);
        contentValues.put(DatabaseHelper.CURP, curp);
        contentValues.put(DatabaseHelper.RFC, rfc);
        contentValues.put(DatabaseHelper.NSC, nsc);
        contentValues.put(DatabaseHelper.AFORE, afore);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

    public ArrayList<Person> getFavList() {
        String selectQuery = "SELECT  * FROM " + DatabaseHelper.TABLE_NAME;

        SQLiteDatabase db = dbHelper.getContextlist();

        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<Person> FavList = new ArrayList<Person>();
        if (cursor.moveToFirst()) {
            do {

                FavList.add(new Person(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7)));

            } while (cursor.moveToNext());
        }
        return FavList;
    }


}
