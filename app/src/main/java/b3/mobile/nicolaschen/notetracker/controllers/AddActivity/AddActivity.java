package b3.mobile.nicolaschen.notetracker.controllers.AddActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.R;

public abstract class AddActivity extends AppCompatActivity {
    public static final String BAC_YEAR_ID = "BACYEAR_ID";
    protected Button mAddElementButton;
    protected Button mConfirmButton;
    protected UUID bacYearId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = getFragmentList(fm);
            if (fragment != null) {
                fm.beginTransaction()
                        .add(R.id.fragment_container, fragment)
                        .commit();
            }
        }

        mAddElementButton = findViewById(R.id.add_element_button);
        mConfirmButton = findViewById(R.id.confirm_button);
        bacYearId = (UUID) getIntent().getSerializableExtra(BAC_YEAR_ID);
        mAddElementButton.setOnClickListener((View view) ->this.onAddButtonClick(bacYearId));
        mConfirmButton.setOnClickListener((View view) ->this.onConfirmButtonClick(bacYearId));
    }

    protected abstract void onAddButtonClick(UUID bacYearId);
    protected abstract void onConfirmButtonClick(UUID bacYearId);
    protected abstract Fragment getFragmentList(FragmentManager fm);


}
