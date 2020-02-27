package com.example.finalexam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Detail extends AppCompatActivity {
    private TextView createdDate, todoDate;
    private EditText todoName;
    private CheckBox todoCheck;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_menu, menu);
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
        if (getIntent().getStringExtra("ISCOMPLETE").equals("true")) {
            createdDate.setText("Completed Date");
            todoCheck.setChecked(true);
        }
        todoName.setText(getIntent().getStringExtra("NAME"));
        todoDate.setText(getIntent().getStringExtra("DATE"));
    }
    public void saveTODO(View view) {
        finish();
    }
    public void deleteTODO(View view) {
        finish();
    }
}
