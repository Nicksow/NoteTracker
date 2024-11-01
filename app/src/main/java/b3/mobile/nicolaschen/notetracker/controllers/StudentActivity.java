package b3.mobile.nicolaschen.notetracker.controllers;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class StudentActivity extends ListActivity {

    @Override
    protected Fragment getFragmentList(FragmentManager fm, String fragmentType) {
        if (fragmentType.equals("student")) {
            return new StudentFragment();
        } else {
            return null;
        }
    }

}
