package b3.mobile.nicolaschen.notetracker.controllers.AddActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.R;
import b3.mobile.nicolaschen.notetracker.models.Assessment;
import b3.mobile.nicolaschen.notetracker.models.AssessmentLab;
import b3.mobile.nicolaschen.notetracker.utils.DisplayUtils;

public class AddSubAssessmentFragment extends Fragment {
    private LinearLayout mContainer;
    private EditText mNoteField;
    private EditText mNameField;
    private Button mAddSubButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_item_assessment_textfield, container, false);
        initializeViews(v);
        return v;
    }

    private void initializeViews(View v) {
        mContainer = getActivity().findViewById(R.id.fragment_container);
        mNoteField = v.findViewById(R.id.maxNote_textfield);
        mNameField = v.findViewById(R.id.assessmentName_textfield);
        mAddSubButton = v.findViewById(R.id.add_subAssessment_button);
        mAddSubButton.setOnClickListener(view -> addSubAssessmentView(v, 1));
    }

    private void addSubAssessmentView(View parentView, int level) {
        View newView = (LayoutInflater.from(getContext())).inflate(R.layout.list_item_assessment_textfield, mContainer, false);
        int parentIndex = mContainer.indexOfChild(parentView);
        mContainer.addView(newView, parentIndex + 1);
        DisplayUtils.setLeftMargin(newView.findViewById(R.id.linearLayout_container), level);
        Button addSubButton = newView.findViewById(R.id.add_subAssessment_button);
        addSubButton.setOnClickListener(view -> addSubAssessmentView(newView, level + 1));
    }

    public void addElement() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View newView = inflater.inflate(R.layout.list_item_assessment_textfield, mContainer, false);
        mContainer.addView(newView);
        Button addSubButton = newView.findViewById(R.id.add_subAssessment_button);
        addSubButton.setOnClickListener(view -> addSubAssessmentView(newView, 1));
    }

    public void confirm(String bacYearId, UUID parentId) {
        int childCount = mContainer.getChildCount();
        String subParentId = null;
        String subSubParentId = null;
        for (int i = 0; i < childCount; i++) {
            View childView = mContainer.getChildAt(i);
            mNameField = childView.findViewById(R.id.assessmentName_textfield);
            mNoteField = childView.findViewById(R.id.maxNote_textfield);
            String name = mNameField.getText().toString();
            String note = mNoteField.getText().toString();
            if (warning(name, note)) return;
            LinearLayout container = childView.findViewById(R.id.linearLayout_container);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) container.getLayoutParams();
            
            if (params.leftMargin > 64) {
                // This is a sub-sub-assessment
                Assessment subSubAssessment = new Assessment(name, bacYearId, subSubParentId, Double.parseDouble(note), true);
                AssessmentLab.get(getContext()).addAssessment(subSubAssessment);
            } else if (params.leftMargin > 0) {
                // This is a sub-assessment
                Assessment subAssessment = new Assessment(name, bacYearId, subParentId, Double.parseDouble(note), true);
                AssessmentLab.get(getContext()).addAssessment(subAssessment);
                subSubParentId = subAssessment.getId().toString();
            } else {
                // This is a main assessment
                Assessment assessment = new Assessment(name, bacYearId, parentId.toString(), Double.parseDouble(note), true);
                AssessmentLab.get(getContext()).addAssessment(assessment);
                subParentId = assessment.getId().toString();
            }
        }
        getActivity().finish();
    }

    private boolean warning(String name, String note) {
        if (name.isEmpty() || note.isEmpty()) {
            Toast.makeText(getContext(), "Tous les champs doivent être remplis.", Toast.LENGTH_SHORT).show();
            return true;
        }
        try {
            Double.parseDouble(note);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "La note doit être un nombre", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}