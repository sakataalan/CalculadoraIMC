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
    private static final String DB_TABLE = "User";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_PASSWORD = "password";
    private static final String COL_DATE = "date";

    public UserDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE IF NOT EXISTS " + DB_TABLE + "( " +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_PASSWORD + " TEXT, " +
                COL_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Boolean insertData(UserDetails u) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, u.getUsername());
        values.put(COL_PASSWORD, u.getPassword());
        long result = database.insert(DB_TABLE, null, values);

        return result == -1 ? false : true;
    }

    public Boolean checkUsernamePassword(UserDetails u) {
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + DB_TABLE + " WHERE " + COL_NAME + " = ? AND " + COL_PASSWORD + " = ?", new String[]{u.getUsername(), u.getPassword()});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}

