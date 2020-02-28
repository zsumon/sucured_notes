package oop_project.loginwithsqlite;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText uname, answeret;
    TextView ques, pass;
    Button submit;

    DatabaserHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        helper = new DatabaserHelper(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.fotgot_pass_toolbar);

        this.setSupportActionBar(toolbar);

        ActionBar supportActionBar = this.getSupportActionBar();

        supportActionBar.setDisplayShowTitleEnabled(false);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDefaultDisplayHomeAsUpEnabled(true);

        uname = (EditText) findViewById(R.id.editText_uname);
        answeret = (EditText) findViewById(R.id.editText4_answer);
        ques = (TextView) findViewById(R.id.editText2_question);
        pass = (TextView) findViewById(R.id.textView_your_pass);
        submit = (Button) findViewById(R.id.button_submit);

        pass.setVisibility(View.GONE);

    }

    private boolean isEligible(String username) {


        String corr_ans = helper.getUserData(username).getSecurityAnswer();
        if (answeret.getText().toString().equals(corr_ans))
            return true;
        else {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            return false;
        }


    }


    public void submit_to_get_pass(View view) {


        if (isEligible(uname.getText().toString())) {
            Toast.makeText(this, "Valid Data!", Toast.LENGTH_SHORT).show();

            pass.setVisibility(View.VISIBLE);
            pass.setText(helper.getUserData(uname.getText().toString()).getPassword());
        }

    }

    public void get_Quest(View view) {


        String getQ = helper.getUserData(uname.getText().toString()).getSecurityQuestion();

        if (getQ != null) {
            ques.setText("Security Question: " + getQ);
        } else {
            ques.setText("Invalid User");
        }


    }
}
