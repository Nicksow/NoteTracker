package b3.mobile.nicolaschen.notetracker.controllers.AddActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.R;

public class AddStudentActivity extends AddActivity {

    @Override
    protected void onAddButtonClick(UUID bacYearId) {
        AddStudentFragment ASFragment = (AddStudentFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (ASFragment != null) {
            ASFragment.addElement();
        }
    }

    @Override
    protected void onConfirmButtonClick(UUID bacYearId) {
        AddStudentFragment ASFragment = (AddStudentFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (ASFragment != null) {
            ASFragment.confirm(bacYearId.toString());
        }
    }

    @Override
    protected Fragment getFragmentList(FragmentManager fm) {
        return new AddStudentFragment();
    }



}
