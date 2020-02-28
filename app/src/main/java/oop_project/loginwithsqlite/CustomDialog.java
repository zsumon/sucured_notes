package oop_project.loginwithsqlite;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;
import android.widget.Toast;

import static android.view.View.GONE;

/**
 * Created by Sumon on 8/4/2017.
 */
public class CustomDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;

    CheckBox cbox;
    TimePicker picker;

    public CustomDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);

        cbox = (CheckBox) findViewById(R.id.checkBox);
        picker = (TimePicker) findViewById(R.id.timePicker_notif);

        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        cbox.setOnClickListener(this);
        picker.setVisibility(GONE);


    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btn_yes:
                c.finish();
                break;
            case R.id.btn_no:
                dismiss();
                break;
            case R.id.checkBox:
                if (cbox.isChecked()) {
                    picker.setVisibility(View.VISIBLE);
                    Toast.makeText(c, "Set Time for Notification!", Toast.LENGTH_SHORT).show();

                } else {
                    picker.setVisibility(GONE);

                }

                break;
            default:
                break;
        }
        // dismiss();
    }
}