package com.example.myapplication.database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class StudentDatabase extends SQLiteAssetHelper {
    private static  final String DB_NAME = "check.db";
    private static final int DB_VERSION = 1;

    public StudentDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
}
