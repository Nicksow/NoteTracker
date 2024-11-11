package b3.mobile.nicolaschen.notetracker.controllers.ListActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.R;
import b3.mobile.nicolaschen.notetracker.controllers.UpdatableFragment;
import b3.mobile.nicolaschen.notetracker.models.Assessment;
import b3.mobile.nicolaschen.notetracker.models.AssessmentLab;
import b3.mobile.nicolaschen.notetracker.models.BacYear;
import b3.mobile.nicolaschen.notetracker.models.BacYearLab;

public class EditAssessmentFragment extends Fragment implements UpdatableFragment {
    public static final String BAC_YEAR_ID = "BACYEAR_ID";
    public static final String PARENT_ID = "PARENT_ID";
    protected BacYear mBacYear;
    protected Assessment mAssessment;
    private String mParentId;
    private LinearLayout mContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID bacYearId = (UUID) getActivity().getIntent().getSerializableExtra(BAC_YEAR_ID);
        mBacYear = BacYearLab.get(getContext()).getBacYear(bacYearId);
        UUID assessmentId = (UUID) getActivity().getIntent().getSerializableExtra(PARENT_ID);
        mAssessment = AssessmentLab.get(getContext()).getAssessment(assessmentId);
        mParentId = mAssessment.getId().toString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        AssessmentLab lab = AssessmentLab.get(getContext());
        mContainer.removeAllViews();
        for (Assessment subAssessment : lab.getSubAssessments(mBacYear.getId().toString(), mParentId)) {
            if (subAssessment.getNoteName() != null) {
                mContainer.addView(getSubAssessmentView(subAssessment));
            }
            for (Assessment subSubAssessment : lab.getSubAssessments(mBacYear.getId().toString(), subAssessment.getId().toString())) {
                if (subSubAssessment.getNoteName() != null) {
                    mContainer.addView(getSubSubAssessmentView(subSubAssessment));
                }
            }
        }
    }

    private View getSubSubAssessmentView(final Assessment subAssessment) {
        View assessmentView = getLayoutInflater().inflate(R.layout.list_item_note, null);
        TextView assessmentName = assessmentView.findViewById(R.id.name_textView);
        TextView assessmentNote = assessmentView.findViewById(R.id.note_textView);
        TextView matricule = assessmentView.findViewById(R.id.matricule_textView);
        assessmentName.setText(subAssessment.getNoteName());
        assessmentNote.setText(subAssessment.getNoteMaxValue().toString());
        setLeftMargin(assessmentView.findViewById(R.id.name_note_container), 64);
        matricule.setVisibility(View.GONE);
        return assessmentView;
    }

    private View getSubAssessmentView(final Assessment assessment) {
        View assessmentView = getLayoutInflater().inflate(R.layout.list_item_note, null);
        TextView assessmentName = assessmentView.findViewById(R.id.name_textView);
        TextView assessmentNote = assessmentView.findViewById(R.id.note_textView);
        TextView matricule = assessmentView.findViewById(R.id.matricule_textView);
        assessmentName.setText(assessment.getNoteName());
        assessmentNote.setText(assessment.getNoteMaxValue().toString());
        matricule.setVisibility(View.GONE);
        return assessmentView;
    }

    private void setLeftMargin(LinearLayout layout, int leftMargin) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) layout.getLayoutParams();
        params.setMargins(leftMargin, params.topMargin, params.rightMargin, params.bottomMargin);
        layout.setLayoutParams(params);
    }
}
