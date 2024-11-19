package b3.mobile.nicolaschen.notetracker.controllers.ListActivity;

import android.annotation.SuppressLint;
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
import b3.mobile.nicolaschen.notetracker.utils.DisplayUtils;

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
        mContainer.removeAllViews();
        addAssessmentViews(mParentId, 0);
    }

    private void addAssessmentViews(String parentId, int level) {
        AssessmentLab lab = AssessmentLab.get(getContext());
        for (Assessment assessment : lab.getSubAssessments(mBacYear.getId().toString(), parentId)) {
            View assessmentView = getAssessmentView(assessment, level);
            mContainer.addView(assessmentView);
            addAssessmentViews(assessment.getId().toString(), level + 1);
        }
    }

    @SuppressLint("SetTextI18n")
    private View getAssessmentView(final Assessment assessment, int level) {
        View assessmentView = getLayoutInflater().inflate(R.layout.list_item_note, null);
        TextView assessmentName = assessmentView.findViewById(R.id.name_textView);
        TextView assessmentNote = assessmentView.findViewById(R.id.note_textView);
        assessmentView.findViewById(R.id.matricule_textView).setVisibility(View.GONE);
        assessmentName.setText(assessment.getNoteName());
        assessmentNote.setText(assessment.getNoteMaxValue().toString());
        DisplayUtils.setLeftMargin(assessmentView.findViewById(R.id.name_note_container), level);
        return assessmentView;
    }

}
