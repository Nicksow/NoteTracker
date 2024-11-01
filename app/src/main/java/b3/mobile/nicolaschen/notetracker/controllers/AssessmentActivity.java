package b3.mobile.nicolaschen.notetracker.controllers;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class AssessmentActivity extends ListActivity {

    @Override
    protected Fragment getFragmentList(FragmentManager fm, String fragmentType) {
        if (fragmentType.equals("assessment")) {
            return new AssessmentFragment();
        } else {
            return null;
        }
    }
}
