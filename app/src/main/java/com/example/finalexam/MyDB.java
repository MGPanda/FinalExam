package com.example.finalexam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.finalexam.MyDatabaseHelper;

public class MyDB {

    private MyDatabaseHelper dbHelper;

    private static SQLiteDatabase database;

    public final static String TODO_TABLE = "todolist"; // name of table

    public final static String TODO_ID = "_id";
    public final static String TODO_NAME = "name";
    public final static String TODO_DATE = "date";
    public final static String TODO_ISCOMPLETE = "iscomplete";

    /**
     * @param context
     */
    public MyDB(Context context) {
        dbHelper = new MyDatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }


    public static long createRecords(int id, String name, String date, String isComplete) {
        if (id == 0) {
            id = nextId();
        }
        ContentValues values = new ContentValues();
        values.put(TODO_ID, id);
        values.put(TODO_NAME, name);
        values.put(TODO_DATE, date);
        values.put(TODO_ISCOMPLETE, isComplete);
        return database.insert(TODO_TABLE, null, values);
    }

    public void insertTODO() {
        createRecords(1, "Fill Gas", "09/02/2017 23:02", "false");
        createRecords(2, "Call John", "09/02/2017 23:01", "false");
    }
    public static int nextId() {
        String[] cols = new String[]{TODO_ID};
        Cursor mCursor = database.query(true, TODO_TABLE, cols, null
                , null, null, null, "_id desc", null);
        mCursor.moveToFirst();
        int id = mCursor.getInt(0)+1;
        return id;
    }
    public static Cursor selectRecords(boolean b) {
        String[] cols = new String[]{TODO_ID, TODO_NAME, TODO_DATE, TODO_ISCOMPLETE};
        Cursor mCursor;
        if (b) {
            mCursor = database.query(true, TODO_TABLE, cols, null
                    , null, null, null, "date asc", null);
        } else {
            mCursor = database.query(true, TODO_TABLE, cols, "iscomplete = ?"
                    , new String[]{"false"}, null, null, "date asc", null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.
    }

    public static void deleteItem(int id) {
        database.delete(TODO_TABLE, "_id = ?", new String[]{String.valueOf(id)});
    }

    public static long completeItem(int id, String name, String date, String isComplete) {
        ContentValues values = new ContentValues();
        values.put(TODO_NAME, name);
        values.put(TODO_DATE, date);
        values.put(TODO_ISCOMPLETE, isComplete);
        return database.update(TODO_TABLE, values, "_id = ?", new String[]{String.valueOf(id)});
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }
}