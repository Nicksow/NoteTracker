package b3.mobile.nicolaschen.notetracker.controllers.AddActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.R;

public class AddBacYearActivity extends AddActivity {

    @Override
    protected void onAddButtonClick(UUID bacYearId) {
        AddBacYearFragment ABYFragment = (AddBacYearFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (ABYFragment != null) {
                ABYFragment.addElement();
            }
    }

    @Override
    protected void onConfirmButtonClick(UUID bacYearId) {
        AddBacYearFragment ABYFragment = (AddBacYearFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (ABYFragment != null) {
            ABYFragment.confirm();
        }
    }

    @Override
    protected Fragment getFragmentList(FragmentManager fm) {
        return new AddBacYearFragment();
    }
}
