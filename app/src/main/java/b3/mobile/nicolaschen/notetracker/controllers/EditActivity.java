package b3.mobile.nicolaschen.notetracker.controllers;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import b3.mobile.nicolaschen.notetracker.R;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit);

        // Fragment management
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.note_container);
        // If the fragment is not already created, create it
        if(fragment==null) {
            // Create a new fragment
            fragment = new BacYearEditFragment();
            // Add the fragment to the fragment_container
            fm.beginTransaction()
                    .add(R.id.note_container, fragment)
                    .commit();
        }
    }
}
