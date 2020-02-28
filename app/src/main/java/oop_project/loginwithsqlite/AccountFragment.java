package oop_project.loginwithsqlite;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static oop_project.loginwithsqlite.DatabaserHelper.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private TextView welcomeTextView;
    private User user;


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_account, container, false);

        welcomeTextView = (TextView) view.findViewById(R.id.account_fragment_text_view);

        //    welcomeTextView.setText("Welcome");


        DatabaserHelper accDBHelper = new DatabaserHelper(getActivity());

        SharedPreferences preferences = getActivity().getSharedPreferences("FOR_ALL_FRAGMENTS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Intent mainIntent = getActivity().getIntent();
        String userName = mainIntent.getStringExtra("LOGGED_IN_USER");

        if (userName != null) {
//
//            Log.d("TAG", "userName is not null saving data: " + userName);
//            editor.putString("SHARED_USER", userName);
//            editor.commit();

            user = accDBHelper.getUserData(userName);


        } else {
            Log.d("TAG", "userName is null, maybe called from add task activity: getting data from shared prefs!");
            String shared_usedString = preferences.getString("SHARED_USER", "defaultValue");
            Log.d("TAG", "got from shared prefs: " + shared_usedString);

            user = accDBHelper.getUserData(shared_usedString);

        }

        welcomeTextView.setText(user.getUserName());
        Log.d(TAG, "onCreateView: (AccountFragment): " + user.getEmail() + " "
                + user.getSecurityQuestion() + " "
                + user.getSecurityAnswer() + " " + user.getName());

        welcomeTextView.setText("\n\n\nusername: " + user.getUserName() + "\n\n" + "password: ****" + "\n\nEmail: " + user.getEmail() + "\n\n"
                + "Name: " + user.getName() + "\n\n" + "Security Question: " + user.getSecurityQuestion() + "\n\n"
                + "Answer: " + user.getSecurityAnswer()

        );


        return view;
    }

}
