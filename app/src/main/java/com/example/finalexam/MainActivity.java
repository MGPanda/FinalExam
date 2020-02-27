package com.example.finalexam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MyDB mydb;
    private RecyclerView rv;
    private boolean all;
    private static MainActivity ma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setTitle("Sqlite TODO List");
        ma = this;
        all = false;
        rv = findViewById(R.id.recyclerView);
        mydb = new MyDB(this);
        MyDatabaseHelper mdh = new MyDatabaseHelper(this);
        mdh.onCreate(mydb.getDatabase());
        mydb.insertTODO();
        setAdapter();
    }
    public void changeSwitch(View view) {
        if (all) all = false; else all = true;
        setAdapter();
    }
    public void addNew(View view) {
        Intent i = new Intent(this,Detail.class);
        startActivityForResult(i, 0);
    }
    public void setAdapter() {
        Cursor c = mydb.selectRecords(all);
        RecyclerAdapter ra = new RecyclerAdapter(this, c);
        rv.setAdapter(ra);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
    public static MainActivity getMa() {
        return ma;
    }
}
