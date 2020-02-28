package oop_project.loginwithsqlite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    MyFragmentPagerAdapter mfmp;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor prefEditor;

    String loggedUserName;

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        inAll();


        loggedUserName = getIntent().getStringExtra("LOGGED_IN_USER");
        if (loggedUserName != null) {

            Log.d("TAG", "Logged userName: " + loggedUserName);
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void inAll() {
        toolbar = (Toolbar) findViewById(R.id.tb);
        viewPager = (ViewPager) findViewById(R.id.dashboard_view_pager);
        tabLayout = (TabLayout) findViewById(R.id.dashboard_tabs);

        mfmp = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mfmp);
        tabLayout.setupWithViewPager(viewPager);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(getDrawable(R.mipmap.ic_menu_white_24dp));


        tabLayout.getTabAt(0).setIcon(R.drawable.ic_note_add_white_48dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_cloud_done_white_48dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_account_circle_white_18dp);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.about) {
            Toast.makeText(this, "Clicked: About", Toast.LENGTH_SHORT).show();


        } else if (item.getItemId() == R.id.logout_item) {
            Log.d("TAG", "onClick: Clicked on: Logout");
            Toast.makeText(this, "Clicked on Logout", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}
