package oop_project.loginwithsqlite;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Sumon on 7/26/2017.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NotesFragment();
        } else if (position == 1) {
            return new SecondFragment();
        } else if (position == 2) {
            return new AccountFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "";//Notes
            //return "Notes";//Notes
        } else if (position == 1) {
            return "";//Center
            //return "Center";//Center
        } else {
            return "";//Account
            // return "Account";//Account
        }

    }
}
