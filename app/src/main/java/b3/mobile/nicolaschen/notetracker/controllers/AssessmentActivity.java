package b3.mobile.nicolaschen.notetracker.controllers;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.models.Assessment;
import b3.mobile.nicolaschen.notetracker.models.AssessmentLab;

public class AssessmentActivity extends AddListActivity {

    @Override
    protected void onActionButtonClick(UUID bacYearId) {
        Log.d("Buttons", "Add_Assessment button clicked");
        Assessment newAssessment = new Assessment();
        newAssessment.setNoteName("Nouvelle évaluation");
        newAssessment.setUuidBacYear(bacYearId.toString()); // Set BacYear ID
        AssessmentLab.get(getApplicationContext()).addAssessment(newAssessment);
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
