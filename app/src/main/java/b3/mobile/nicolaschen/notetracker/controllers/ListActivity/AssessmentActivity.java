package b3.mobile.nicolaschen.notetracker.controllers.ListActivity;

import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.controllers.AddActivity.AddAssessmentActivity;
import b3.mobile.nicolaschen.notetracker.controllers.AddActivity.AddBackYearActivity;

public class AssessmentActivity extends ListActivity {

    @Override
    protected void onActionButtonClick(UUID bacYearId) {
        Intent intent = new Intent(getApplicationContext(), AddAssessmentActivity.class);
        intent.putExtra(AddBackYearActivity.BAC_YEAR_ID, bacYearId);
        startActivity(intent);
        updateUI();
    }

    @Override
    protected String getTitleText(String bacYearName) {
        return "Evaluations : " + bacYearName;
    }

    @Override
    protected Fragment getFragmentList(FragmentManager fm) {
        return new AssessmentFragment();
    }
}
