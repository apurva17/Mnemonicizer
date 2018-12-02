package com.example.vonkdothuser11.meanings;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DBHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "meaningnew.sqlite";
    private SQLiteDatabase db;
    private final Context context;
    private String DB_PATH;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        // DB_PATH= context.getDatabasePath("").getPath();
        Log.d("path",""+DB_PATH);
        DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
    }

    public void createDataBase() throws IOException {

        boolean dbExist = false;
        if (dbExist) {

        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }

    }

    void openDatabase() {
        // TODO Auto-generated method stub
        String outFileName = DB_PATH + DB_NAME;
        db = SQLiteDatabase.openDatabase(outFileName, null, SQLiteDatabase.OPEN_READWRITE);
        db.close();
    }


    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    void copyDataBase() throws IOException {

        InputStream myInput = context.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        Log.d("Copied", "Database");
        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }
    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        try {
            copyDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public JSONArray getData() {
        int totalColumn = 0;
        JSONArray resultSet = new JSONArray();
        String outFileName = DB_PATH + DB_NAME;
        //String query="SELECT * FROM Questions";
        //String query = "SELECT * FROM Questions WHERE levelnumber=" + Level.Level + " AND keystage='1'";
        String query = "SELECT * FROM meaning";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // Note: Master is the one table in External db. Here we trying to access the records of table from external db.
        if (cursor.moveToFirst()) {
            do {
                // get  the  data into array,or class variable
                totalColumn = cursor.getColumnCount();
                JSONObject rowObject = new JSONObject();
                for (int i = 0; i < totalColumn; i++) {
                    if (cursor.getColumnName(i) != null) {
                        try {
                            if (cursor.getString(i) != null) {
                                //Log.d("TAG_NAME", cursor.getString(i));
                                rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                            } else {
                                rowObject.put(cursor.getColumnName(i), "");
                            }
                        } catch (Exception e) {
                            Log.d("TAG_NAME", e.getMessage());
                        }
                    }

                }
                Log.d("meanings", "" + rowObject);

                resultSet.put(rowObject);
            }
            while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return resultSet;

    }
}
