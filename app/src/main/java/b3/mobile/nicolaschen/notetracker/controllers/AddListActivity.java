package b3.mobile.nicolaschen.notetracker.controllers;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.R;

public abstract class AddListActivity extends AppCompatActivity {
    public static final String FRAGMENT_TYPE = "FRAGMENT_TYPE";
    public static final String BAC_YEAR_NAME = "BACYEAR_NAME";
    public static final String BAC_YEAR_ID = "BACYEAR_ID";
    protected TextView titleTextView;
    protected Button actionButton;
    protected String bacYearName;
    protected UUID bacYearId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_list);
        // Fragment management
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.list_container);
        // Determine which fragment to create based on the intent extra
        if (fragment == null) {
            fragment = this.getFragmentList(fm);
            if (fragment != null) {
                fm.beginTransaction()
                        .add(R.id.list_container, fragment)
                        .commit();
            }
        }
        titleTextView = findViewById(R.id.activity_textview);
        actionButton = findViewById(R.id.add_button);
        bacYearName = getIntent().getStringExtra(BAC_YEAR_NAME);
        bacYearId = (UUID) getIntent().getSerializableExtra(BAC_YEAR_ID);
        titleTextView.setText(this.getTitleText(bacYearName));
        actionButton.setOnClickListener((View view) -> this.onActionButtonClick(bacYearId));

    }

    protected abstract void onActionButtonClick(UUID bacYearId);

    protected abstract String getTitleText(String bacYearName);

    protected abstract Fragment getFragmentList(FragmentManager fm);

    protected void updateUI() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.list_container);
        if (fragment != null) {
            ((UpdatableFragment) fragment).updateUI();
        }
    }

}