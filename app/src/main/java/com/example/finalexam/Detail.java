package com.example.finalexam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class Detail extends AppCompatActivity {
    private TextView createdDate, todoDate;
    private EditText todoName;
    private CheckBox todoCheck;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            if (getIntent().getExtras() == null || getIntent().getStringExtra("ISCOMPLETE").equals("false")) {
                getMenuInflater().inflate(R.menu.bar_menu, menu);
            } else {
                getMenuInflater().inflate(R.menu.bar_menu2, menu);
            }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setTitle("Edit Todo Item");
        createdDate = findViewById(R.id.createdDate);
        todoDate = findViewById(R.id.todoDate);
        todoName = findViewById(R.id.todoName);
        todoCheck = findViewById(R.id.todoCheck);
        if (getIntent().getExtras() != null) {
            if (getIntent().getStringExtra("ISCOMPLETE").equals("true")) {
                createdDate.setText("Completed Date");
                todoCheck.setChecked(true);
                todoName.setEnabled(false);
                todoCheck.setEnabled(false);
            }
            todoName.setText(getIntent().getStringExtra("NAME"));
            todoDate.setText(getIntent().getStringExtra("DATE"));
        }
    }
    public void saveTODO(MenuItem mi) {
        String c = "false";
        if (todoCheck.isChecked()) c = "true";
        if (getIntent().getExtras() != null) {
            MyDB.completeItem(getIntent().getIntExtra("ID",0), todoName.getText().toString(), todoDate.getText().toString(), c);
        } else {
            setResult(0);
            MyDB.createRecords(0, todoName.getText().toString(), new Date().toString(), c);
        }
        MainActivity.getMa().setAdapter();
        finish();
    }
    public void deleteTODO(MenuItem mi) {
        if (getIntent().getExtras() != null) {
            MyDB.deleteItem(getIntent().getIntExtra("ID",0));
            MainActivity.getMa().setAdapter();
        }
        finish();
    }
}
