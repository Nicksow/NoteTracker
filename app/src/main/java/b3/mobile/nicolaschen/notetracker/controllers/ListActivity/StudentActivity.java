package b3.mobile.nicolaschen.notetracker.controllers.ListActivity;

import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.controllers.AddActivity.AddBacYearActivity;
import b3.mobile.nicolaschen.notetracker.controllers.AddActivity.AddStudentActivity;

public class StudentActivity extends ListActivity {

    @Override
    protected void onActionButtonClick(UUID bacYearId) {
        Intent intent = new Intent(getApplicationContext(), AddStudentActivity.class);
        intent.putExtra(AddBacYearActivity.BAC_YEAR_ID, bacYearId);
        startActivity(intent);
        updateUI();
    }

    @Override
    protected String getTitleText(String bacYearName) {
        return "Étudiants : " + bacYearName;
    }

    @Override
    protected Fragment getFragmentList(FragmentManager fm) {
        return new StudentFragment();
    }
}
