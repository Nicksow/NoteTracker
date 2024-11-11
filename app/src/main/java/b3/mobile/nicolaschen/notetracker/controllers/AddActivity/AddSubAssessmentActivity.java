package b3.mobile.nicolaschen.notetracker.controllers.AddActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.R;

public class AddSubAssessmentActivity extends AddActivity{
    public static final String PARENT_ID = "PARENT_ID";

    @Override
    protected void onAddButtonClick(UUID bacYearId) {
        AddSubAssessmentFragment ASAFragment = (AddSubAssessmentFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (ASAFragment != null) {
            ASAFragment.addElement();
        }
    }

    @Override
    protected void onConfirmButtonClick(UUID bacYearId) {
        UUID mParentId = (UUID) getIntent().getSerializableExtra(PARENT_ID);
        AddSubAssessmentFragment ASAFragment = (AddSubAssessmentFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (ASAFragment != null) {
            ASAFragment.confirm(bacYearId.toString(), mParentId);
        }
    }

    @Override
    protected Fragment getFragmentList(FragmentManager fm) {
        return new AddSubAssessmentFragment();
    }
}
