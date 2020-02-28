package oop_project.loginwithsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    private TextView already_login;
    private EditText userNameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText securityQuestionAnswerEditText;
    private EditText securityQuestionEditText;

    private DatabaserHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        Toolbar toolbar = (Toolbar) findViewById(R.id.reg_toolbar);

        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();

        supportActionBar.setDisplayShowTitleEnabled(false);

        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDefaultDisplayHomeAsUpEnabled(true);

//        //transparent nav bar for kitkat/afterwards
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window w = getWindow(); // in Activity's onCreate() for instance
//            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }


        dbHelper = new DatabaserHelper(this);
        initAll();

        already_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });


    }

    public void registerButton(View view) {

        String trimedUser = userNameEditText.getText().toString().trim();
//
//        if (dbHelper.isAlreadyRegistered(trimedUser)) {
//            Toast.makeText(this, "User Already Exists!", Toast.LENGTH_SHORT).show();
//            return;
//        }

        if (isValid()) {
            User user = new User(trimedUser, passwordEditText.getText().toString(), nameEditText.getText().toString(), emailEditText.getText().toString(), securityQuestionEditText.getText().toString(), securityQuestionAnswerEditText.getText().toString());
            dbHelper.addUser(user);
            Toast.makeText(this, "Registered successfully! You may Login now", Toast.LENGTH_SHORT).show();
        }


    }

    private boolean isValid() {

        if (userNameEditText.getText().toString().equals("")) {
            Log.d(TAG, "isValid: username is invalid/space");
            Toast.makeText(this, "Invalid username", Toast.LENGTH_SHORT).show();
            userNameEditText.setError("Invalid username");
            return false;
        }

        if (dbHelper.isAlreadyRegistered(userNameEditText.getText().toString())) {
            Log.d(TAG, "isValid: already registered!");
            Toast.makeText(this, "User exists!", Toast.LENGTH_SHORT).show();
            userNameEditText.setError("Already exists!");
            return false;

        }

        if (passwordEditText.getText().toString().equals("")) {
            passwordEditText.setError("Invalid");
            Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "isValid: pass is invalid/space");
        }
        if (!passwordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString())) {
            passwordEditText.setError("Not matched");
            confirmPasswordEditText.setError("Not matched");
            Log.d(TAG, "isValid: not matched");
            Toast.makeText(this, "Unmatched password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (securityQuestionEditText.getText().toString().equals("") || securityQuestionAnswerEditText.getText().toString().equals("")) {
            Toast.makeText(this, "Invalid Security Question/Answer", Toast.LENGTH_SHORT).show();
        }


        return true;
    }

    private void initAll() {
        already_login = (TextView) findViewById(R.id.already_login_tv);
        userNameEditText = (EditText) findViewById(R.id.reg_username_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edittext);
        confirmPasswordEditText = (EditText) findViewById(R.id.confirm_password_editText);
        nameEditText = (EditText) findViewById(R.id.reg_name_edit_text);
        emailEditText = (EditText) findViewById(R.id.reg_email_edit_text);
        securityQuestionEditText = (EditText) findViewById(R.id.security_question1_edit_text);
        securityQuestionAnswerEditText = (EditText) findViewById(R.id.reg_security_question1_answer_edit_text);
    }
}
