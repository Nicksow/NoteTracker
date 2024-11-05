package b3.mobile.nicolaschen.notetracker.controllers;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.models.Student;
import b3.mobile.nicolaschen.notetracker.models.StudentLab;

public class StudentActivity extends AddListActivity {

    @Override
    protected void onActionButtonClick(UUID bacYearId) {
        Log.d("Buttons", "Add_Student button clicked");
        Student newStudent = new Student();
        newStudent.setUuidBacYear(bacYearId.toString()); // Set BacYear ID
        StudentLab.get(getApplicationContext()).addStudent(newStudent);
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
