package b3.mobile.nicolaschen.notetracker.controllers.ListActivity;

import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.controllers.AddActivity.AddSubAssessmentActivity;
import b3.mobile.nicolaschen.notetracker.models.Assessment;
import b3.mobile.nicolaschen.notetracker.models.AssessmentLab;

public class EditAssessmentActivity extends ListActivity {
    public static final String PARENT_ID = "PARENT_ID";
    public static final String ASSESSMENT_NAME = "ASSESSMENT_NAME";

    @Override
    protected void onActionButtonClick(UUID bacYearId) {
        UUID AssessmentId = (UUID) getIntent().getSerializableExtra(PARENT_ID);
        Intent intent = new Intent(getApplicationContext(), AddSubAssessmentActivity.class);
        intent.putExtra(AddSubAssessmentActivity.BAC_YEAR_ID, bacYearId);
        intent.putExtra(AddSubAssessmentActivity.PARENT_ID, AssessmentId);
        startActivity(intent);
        updateUI();
    }

    @Override
    protected String getTitleText(String Name) {
        String assessmentName = getIntent().getStringExtra(ASSESSMENT_NAME);
        return "Edition - " + assessmentName;
    }

    @Override
    protected Fragment getFragmentList(FragmentManager fm) {
        return new EditAssessmentFragment();
    }
}
