package com.example.calculadoraimc.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.calculadoraimc.controller.CadastroActivity;

import java.util.ArrayList;

public class UserDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "users.sqlite";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE_USER = "User";
    private static final String COL_ID_USER = "id";
    private static final String COL_NAME = "name";
    private static final String COL_PASSWORD = "password";
    private static final String COL_DATE = "date";
    private static final String DB_TABLE_IMC = "Imc";
    private static final String COL_ID_IMC = "id";
    private static final String COL_IMC = "imc";
    private static final String COL_USER_IMC_ID = "userimc";



    public UserDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE IF NOT EXISTS " + DB_TABLE_USER + "( " +
                COL_ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_PASSWORD + " TEXT, " +
                COL_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP) ";

        String query2 = "CREATE TABLE IF NOT EXISTS " + DB_TABLE_IMC + "( " +
                COL_ID_IMC + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_IMC + " TEXT, " +
                COL_USER_IMC_ID + " INTEGER, " +
                COL_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY(" + COL_USER_IMC_ID + ") REFERENCES " +  DB_TABLE_USER + "(" + COL_ID_USER + "))";
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.execSQL(query);
            sqLiteDatabase.execSQL(query2);
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_IMC);
    }

    public Boolean insertData(UserDetails u) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, u.getUsername());
        values.put(COL_PASSWORD, u.getPassword());
        long result = database.insert(DB_TABLE_USER, null, values);

        return result == -1 ? false : true;
    }

    public Boolean insertImc(String imc) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_IMC, imc);
        long result = database.insert(DB_TABLE_IMC, null, values);
        return result == -1 ? false : true;
    }

    public Boolean checkUsernamePassword(UserDetails u) {
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + DB_TABLE_USER + " WHERE " + COL_NAME + " = ? AND " + COL_PASSWORD + " = ?", new String[]{u.getUsername(), u.getPassword()});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DB_TABLE_IMC + " INNER JOIN " + DB_TABLE_USER + " ON " + DB_TABLE_USER+"."+COL_ID_USER+" = "+DB_TABLE_IMC+"."+COL_USER_IMC_ID, null);
        return cursor;
    }
}

