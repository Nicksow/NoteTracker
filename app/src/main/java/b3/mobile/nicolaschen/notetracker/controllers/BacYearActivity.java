package b3.mobile.nicolaschen.notetracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import b3.mobile.nicolaschen.notetracker.R;
import b3.mobile.nicolaschen.notetracker.controllers.AddOrEditActivity.AddBackYearActivity;
import b3.mobile.nicolaschen.notetracker.controllers.ListActivity.AssessmentActivity;
import b3.mobile.nicolaschen.notetracker.controllers.ListActivity.ListActivity;
import b3.mobile.nicolaschen.notetracker.controllers.ListActivity.StudentActivity;
import b3.mobile.nicolaschen.notetracker.models.BacYear;
import b3.mobile.nicolaschen.notetracker.models.BacYearLab;

public class BacYearActivity extends AppCompatActivity {
    private LinearLayout mContainer;
    private Button mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_class_list);

        mContainer = findViewById(R.id.list_container);
        mAddButton = findViewById(R.id.add_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddBackYearActivity.class);
                startActivity(intent);
            }
        });
        updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        mContainer.removeAllViews();
        BacYearLab lab = BacYearLab.get(this.getApplicationContext());
        for (final BacYear bacYear : lab.getBacYears()) {
            View bacYearView = getBacYearView(bacYear);
            mContainer.addView(bacYearView);
        }
    }

    private View getBacYearView(final BacYear bacYear) {
        View columnForBacYear = getLayoutInflater().inflate(R.layout.list_item_bac_year, null);
        ((TextView) columnForBacYear.findViewById(R.id.bac_year_text_view)).setText(bacYear.getName());
        ImageButton assessementButton = columnForBacYear.findViewById(R.id.assessment_button);
        ImageButton studentButton = columnForBacYear.findViewById(R.id.student_button);


        assessementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Buttons", "Assessment button clicked" + bacYear.getId());
                Intent intent = new Intent(getApplicationContext(), AssessmentActivity.class);
                intent.putExtra(ListActivity.BAC_YEAR_NAME, bacYear.getName());
                intent.putExtra(ListActivity.BAC_YEAR_ID, bacYear.getId());
                startActivity(intent);
            }
        });

        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Buttons", "Student button clicked" + bacYear.getId());
                Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
                intent.putExtra(ListActivity.BAC_YEAR_NAME, bacYear.getName());
                intent.putExtra(ListActivity.BAC_YEAR_ID, bacYear.getId());
                startActivity(intent);
            }
        });
        return columnForBacYear;
    }
}