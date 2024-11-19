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
import b3.mobile.nicolaschen.notetracker.models.Note;
import b3.mobile.nicolaschen.notetracker.models.NoteLab;
import b3.mobile.nicolaschen.notetracker.models.Student;
import b3.mobile.nicolaschen.notetracker.models.StudentLab;
import b3.mobile.nicolaschen.notetracker.utils.MathUtils;

public class StudentFragment extends Fragment implements UpdatableFragment {
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
        StudentLab lab = StudentLab.get(getContext());
        for (final Student student : lab.getStudentsByBacYear((mBacYear.getId().toString()))) {
            View studentView = getStudentView(student);
            mContainer.addView(studentView);
        }
    }

    private View getStudentView(final Student student) {
        View columnForStudent = getLayoutInflater().inflate(R.layout.list_item_note, null);
        TextView nameTextView = columnForStudent.findViewById(R.id.name_textView);
        TextView noteTextView = columnForStudent.findViewById(R.id.note_textView);
        TextView matriculeTextView = columnForStudent.findViewById(R.id.matricule_textView);
        nameTextView.setText(student.getLastname() + " " + student.getFirstname());
        matriculeTextView.setText(student.getMatricule());
        if (String.valueOf(calculateGlobalNote(student)).isEmpty()) {
            noteTextView.setText("TDB/20");
        } else {
            noteTextView.setText(calculateGlobalNote(student) + "/20");
        }
        return columnForStudent;
    }

    private double calculateGlobalNote(Student student) {
        NoteLab noteLab = NoteLab.get(getContext());
        double globalNote = 0;
        double globalMaxNote = 0;
        for (Note note : noteLab.getNotesByStudent(student.getId().toString())) {
            Assessment assessment = AssessmentLab.get(getContext()).getMainAssessment(note.getAssessmentUuid());
            if (assessment != null) {
                globalNote += note.getNoteValue();
                globalMaxNote += assessment.getNoteMaxValue();
            }
        }
        return MathUtils.roundToNearestHalf(globalNote / globalMaxNote * 20);
    }


}
