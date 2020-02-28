package oop_project.loginwithsqlite;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    private DatabaserHelper dbHelper;


    private TextView altLoginTitle;
    private EditText altUserName;
    private EditText altPassWord;

    private Button alt_forgetPass;
    private Button alt_RegNewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();

            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);

        }


        dbHelper = new DatabaserHelper(this);

        in_all();
    }

    private void in_all() {

        alt_forgetPass = (Button) findViewById(R.id.alt_login_forget_pass);
        alt_RegNewUser = (Button) findViewById(R.id.alt_register_new_user);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        altLoginTitle = (TextView) findViewById(R.id.alt_login_title_textview);
        altUserName = (EditText) findViewById(R.id.alt_login_username);
        altPassWord = (EditText) findViewById(R.id.alt_login_password);


        Typeface register_font = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.otf");
        altLoginTitle.setTypeface(register_font);

        altUserName.setTypeface(register_font);
        alt_forgetPass.setTypeface(register_font);
        alt_RegNewUser.setTypeface(register_font);
        altPassWord.setTypeface(register_font);


    }

    public void goButton(View view) {


        String loginUserName = altUserName.getText().toString().trim();

        Log.d("TAG", "dbHelper: " + dbHelper);

        boolean canLogin = false;

        try {
            canLogin = dbHelper.canLogin(altUserName.getText().toString(), altPassWord.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TAG", "Exception: " + e.toString());
        }

        if (canLogin) {
            Log.d("TAG", "login user name: " + loginUserName);
            // User user = dbHelper.getUserData(loginUserName);

            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);

            intent.putExtra("LOGGED_IN_USER", loginUserName);
            Toast.makeText(this, "Successfully logged in!", Toast.LENGTH_SHORT).show();

            startActivity(intent);
            //  startActivityForResult(intent, 0);

        } else {

            Toast.makeText(this, "Wrong username/password!", Toast.LENGTH_SHORT).show();
        }

    }

    public void goToresisterNewUser(View view) {
        startActivity(new Intent(this, RegistrationActivity.class));
    }

    public void forgotPass(View view) {

        startActivity(new Intent(this, ForgotPasswordActivity.class));

    }
}
