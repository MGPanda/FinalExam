package com.example.finalexam;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.recyclerView);
        mydb = new MyDB(this);
        MyDatabaseHelper mdh = new MyDatabaseHelper(this);
        mdh.onCreate(mydb.getDatabase());
        setAdapter();
    }
    public void addNew(View view) {

    }
    public void setAdapter() {
        Cursor c = mydb.selectRecords();
        RecyclerAdapter ra = new RecyclerAdapter(this, c);
        rv.setAdapter(ra);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
        }
        setAdapter();
    }
}
