package b3.mobile.nicolaschen.notetracker.controllers.AddActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import b3.mobile.nicolaschen.notetracker.R;
import b3.mobile.nicolaschen.notetracker.models.Assessment;
import b3.mobile.nicolaschen.notetracker.models.AssessmentLab;

public class AddAssessmentFragment extends Fragment {
    private LinearLayout mContainer;
    private EditText mMatriculeField;
    private EditText mNameField;
    private EditText mFirstNameField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_item_student_textfield, container, false);
        mContainer = getActivity().findViewById(R.id.fragment_container);
        initView(v);
        mContainer = getActivity().findViewById(R.id.fragment_container);
        return v;
    }

    public void addElement() {
        View newView = (LayoutInflater.from(getContext())).inflate(R.layout.list_item_student_textfield, mContainer, false);
        initView(newView);
        mContainer.addView(newView);
    }

    private void initView(View newView) {
        mMatriculeField = newView.findViewById(R.id.matricule_textfield);
        mNameField = newView.findViewById(R.id.assessmentName_textfield);
        mFirstNameField = newView.findViewById(R.id.maxNote_textfield);
        mNameField.setHint("Nom de l'évaluation");
        mMatriculeField.setVisibility(View.GONE);
        mFirstNameField.setVisibility(View.GONE);
    }

    public void confirm(String bacYearId) {
        int childCount = mContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = mContainer.getChildAt(i);
            mNameField = childView.findViewById(R.id.assessmentName_textfield);
            String name = mNameField.getText().toString();
            if (name.isEmpty()) {
                Toast.makeText(getContext(), "Tous les champs doivent être remplis.", Toast.LENGTH_SHORT).show();
                return;
            }
            Assessment assessment = new Assessment(name,bacYearId,null,20.0,false);
            AssessmentLab.get(getContext()).addAssessment(assessment);
        }
        getActivity().finish();
    }
}