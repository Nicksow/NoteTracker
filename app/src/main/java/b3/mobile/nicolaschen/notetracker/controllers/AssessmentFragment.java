package b3.mobile.nicolaschen.notetracker.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.R;
import b3.mobile.nicolaschen.notetracker.models.Assessment;
import b3.mobile.nicolaschen.notetracker.models.AssessmentLab;
import b3.mobile.nicolaschen.notetracker.models.BacYear;
import b3.mobile.nicolaschen.notetracker.models.BacYearLab;

public class AssessmentFragment extends Fragment {
    public static final String BAC_YEAR_ID = "BACYEAR_ID";
    protected BacYear mBacYear;
    private LinearLayout mContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID bacYear_id = (UUID) getActivity().getIntent().getSerializableExtra(BAC_YEAR_ID);
        mBacYear = BacYearLab.get(getContext()).getBacYear(bacYear_id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mContainer = view.findViewById(R.id.fragment_container);
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public void updateUI() {
        mContainer.removeAllViews();
        AssessmentLab lab = AssessmentLab.get(getContext());
        for (final Assessment assessment : lab.getAssessmentsByBacYear((mBacYear.getId().toString()))) {
            View assessmentView = getAssessmentView(assessment);
            mContainer.addView(assessmentView);
        }
    }

    private View getAssessmentView(final Assessment assessment) {
        View columnForAssessment = getLayoutInflater().inflate(R.layout.list_item_note, null);
        TextView nameTextView = columnForAssessment.findViewById(R.id.name_textView);
        TextView noteTextView = columnForAssessment.findViewById(R.id.note_textView);
        TextView matriculeTextView = columnForAssessment.findViewById(R.id.matricule_textView);
        nameTextView.setText(assessment.getNoteName());
        noteTextView.setVisibility(View.GONE);
        matriculeTextView.setVisibility(View.GONE);
        return columnForAssessment;
    }
}