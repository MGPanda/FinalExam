package com.example.finalexam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.finalexam.MyDatabaseHelper;

public class MyDB {

    private MyDatabaseHelper dbHelper;

    private SQLiteDatabase database;

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


    public long createRecords(int id, String name, String date, String isComplete) {
        if (id == 0) {
            Cursor c = selectRecords(true);
            c.moveToLast();
            id = c.getInt(0)+1;
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

    public Cursor selectRecords(boolean b) {
        String[] cols = new String[]{TODO_ID, TODO_NAME, TODO_DATE, TODO_ISCOMPLETE};
        Cursor mCursor;
        if (b) {
            mCursor = database.query(true, TODO_TABLE, cols, null
                    , null, null, null, "date asc", null);
        } else {
            mCursor = database.query(true, TODO_TABLE, cols, "iscomplete = 'false'"
                    , null, null, null, "date asc", null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.
    }

    public void deleteItem(TODO t) {
        database.delete(TODO_TABLE, "_id = "+t.get_id(), null);
    }

    public long completeItem(TODO t) {
        ContentValues values = new ContentValues();
        values.put(TODO_NAME, t.getName());
        values.put(TODO_DATE, t.getDate());
        values.put(TODO_ISCOMPLETE, t.isComplete());
        return database.update(TODO_ID, values, "_id = " + t.get_id(), null);
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }
}