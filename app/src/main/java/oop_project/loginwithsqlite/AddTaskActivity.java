package oop_project.loginwithsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddTaskActivity extends AppCompatActivity {

    EditText titleEditText;
    EditText detailsEditText;
    Intent addTaskIntent;
    DatabaserHelper helper;
    String username_;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Toolbar toolbar = (Toolbar) findViewById(R.id.add_task_toolbar);

        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();

        supportActionBar.setDisplayShowTitleEnabled(false);
        supportActionBar.setDisplayShowHomeEnabled(true);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDefaultDisplayHomeAsUpEnabled(true);


        titleEditText = (EditText) findViewById(R.id.taskNameEditText);
        detailsEditText = (EditText) findViewById(R.id.taskDetailsEditText);
        helper = new DatabaserHelper(this);

        addTaskIntent = getIntent();
        username_ = addTaskIntent.getStringExtra("USERNAME_");


    }

    public void saveTask(View view) {

        String title = titleEditText.getText().toString();
        String details = detailsEditText.getText().toString();
        Task task = new Task(title, details);

        Log.d("TAG", "adding task to table: " + username_);

        try {
            helper.addTaskToTable(task, username_);
            Log.d("TAG", "added task: " + title + "to table: " + username_);

        } catch (Exception e) {
            Log.d("TAG", e.toString());
        }
        startActivity(new Intent(this, DashboardActivity.class));

    }


}
