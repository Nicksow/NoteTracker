package b3.mobile.nicolaschen.notetracker.controllers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.R;
import b3.mobile.nicolaschen.notetracker.models.Assessment;
import b3.mobile.nicolaschen.notetracker.models.AssessmentLab;
import b3.mobile.nicolaschen.notetracker.models.Student;
import b3.mobile.nicolaschen.notetracker.models.StudentLab;

public abstract class ListActivity extends AppCompatActivity {
    public static final String FRAGMENT_TYPE = "FRAGMENT_TYPE";
    public static final String BAC_YEAR_NAME = "BACYEAR_NAME";
    public static final String BAC_YEAR_ID = "BACYEAR_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        // Fragment management
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.list_container);
        // Determine which fragment to create based on the intent extra
        String fragmentType = getIntent().getStringExtra(FRAGMENT_TYPE);
        if (fragment == null) {
            fragment = this.getFragmentList(fm, fragmentType);
            if (fragment != null) {
                fm.beginTransaction()
                        .add(R.id.list_container, fragment)
                        .commit();
            }
        }

        TextView titleTextView = findViewById(R.id.activity_textview);
        Button actionButton = findViewById(R.id.add_button);
        String bacYearName = getIntent().getStringExtra(BAC_YEAR_NAME);
        UUID bacYearId = (UUID) getIntent().getSerializableExtra(BAC_YEAR_ID);
        if (fragmentType.equals("assessment")) {
            titleTextView.setText("Evaluations : " + bacYearName);
            actionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Buttons", "Add_Assessment button clicked");
                    Assessment newAssessment = new Assessment();
                    newAssessment.setNoteName("Nouvelle évaluation");
                    newAssessment.setUuidBacYear(bacYearId.toString()); // Set BacYear ID
                    AssessmentLab.get(getApplicationContext()).addAssessment(newAssessment);
                    updateUI();
                }
            });
        }else if (fragmentType.equals("student")) {
            titleTextView.setText("Elèves en :" + bacYearName);
            actionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Buttons", "Add_Student button clicked");
                    Student newStudent = new Student();
                    newStudent.setUuidBacYear(bacYearId.toString()); // Set BacYear ID
                    StudentLab.get(getApplicationContext()).addStudent(newStudent);
                    updateUI();
                }
            });
        }
    }

    protected void updateUI() {
        // Update the UI based on the fragment type
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.list_container);
        if (fragment != null) {
            if (fragment instanceof AssessmentFragment) {
                ((AssessmentFragment) fragment).updateUI();
            }
            if (fragment instanceof StudentFragment) {
                ((StudentFragment) fragment).updateUI();
            }
        }
    }

    protected abstract Fragment getFragmentList(FragmentManager fm, String fragmentType);
}