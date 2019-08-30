package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper {
    private Context context;
    private static final String DB_NAME = "database/check.db";

    public DatabaseHelper(Context context) {
        this.context = context;
        openDatabase();
    }

    public SQLiteDatabase openDatabase(){
        File dbFile = context.getDatabasePath(DB_NAME);
        if(!dbFile.exists()){
            try {
                coppyDatabase(dbFile);
            }catch (IOException e){
                throw new RuntimeException("Error create db", e);
            }
        }
        return SQLiteDatabase.openDatabase(dbFile.getPath(),null,SQLiteDatabase.OPEN_READWRITE);
    }

    private void coppyDatabase(File dbFile) throws IOException{
        InputStream is = context.getAssets().open(DB_NAME);
        OutputStream os = new FileOutputStream(dbFile);
        byte[] buffer = new byte[1024];
        while (is.read(buffer)> 0){
            os.write(buffer);
        }
        os.flush();
        os.close();
        is.close();
    }
}
