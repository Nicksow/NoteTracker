package b3.mobile.nicolaschen.notetracker.controllers.AddOrEditActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.R;

public class AddAssessmentActivity extends AddOrEditActivity {

    @Override
    protected void onAddButtonClick(UUID bacYearId) {
        AddAssessmentFragment AAFragment = (AddAssessmentFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (AAFragment != null) {
            AAFragment.addElement();
        }
    }
    @Override
    protected void onConfirmButtonClick(UUID bacYearId) {
        AddAssessmentFragment AAFragment = (AddAssessmentFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (AAFragment != null) {
            AAFragment.confirm(bacYearId.toString());
        }
    }
    @Override
    protected Fragment getFragmentList(FragmentManager fm) {
        return new AddAssessmentFragment();
    }
}
