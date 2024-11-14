package b3.mobile.nicolaschen.notetracker.controllers;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.R;
import b3.mobile.nicolaschen.notetracker.models.Assessment;
import b3.mobile.nicolaschen.notetracker.models.AssessmentLab;
import b3.mobile.nicolaschen.notetracker.models.BacYear;
import b3.mobile.nicolaschen.notetracker.models.BacYearLab;
import b3.mobile.nicolaschen.notetracker.models.Note;
import b3.mobile.nicolaschen.notetracker.models.NoteLab;
import b3.mobile.nicolaschen.notetracker.models.Student;
import b3.mobile.nicolaschen.notetracker.models.StudentLab;

public class DetailedStudentNoteActivity extends AppCompatActivity {
    public static final String STUDENT_ID = "STUDENT_ID";
    public static final String ASSESSMENT_ID =  "ASSESSMENT_ID";
    public static final String BAC_YEAR_ID = "BAC_YEAR_ID";
    private LinearLayout mContainer;
    private Student mStudent;
    private Assessment mAssessment;
    private BacYear mBacYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.fragment_list);
        mContainer = findViewById(R.id.fragment_container);
        UUID student_id = (UUID) getIntent().getSerializableExtra(STUDENT_ID);
        mStudent = StudentLab.get(getApplicationContext()).getStudent(student_id);
        UUID assessment_id = (UUID) getIntent().getSerializableExtra(ASSESSMENT_ID);
        mAssessment = AssessmentLab.get(getApplicationContext()).getAssessment(assessment_id);
        UUID bacYear_id = (UUID) getIntent().getSerializableExtra(BAC_YEAR_ID);
        mBacYear = BacYearLab.get(getApplicationContext()).getBacYear(bacYear_id);
        Log.d("test", mAssessment.getId().toString());
        createCurrentAssessmentView();
        updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        mContainer.removeAllViews();
        createCurrentAssessmentView();
        List<Assessment> assessments = AssessmentLab.get(this.getApplicationContext()).getCurrentAssessmentsByParentId(mAssessment.getId().toString());
        for (Assessment assessment : assessments) {
            handleAssessment(assessment, 0);
        }
    }

    private void createCurrentAssessmentView() {
        Note note = NoteLab.get(this.getApplicationContext()).getNoteByStudentAndAssessment(mAssessment.getId().toString(), mStudent.getId().toString());
        if (note != null) {
            Assessment assessment = AssessmentLab.get(this.getApplicationContext()).getAssessment(mAssessment.getId());
            View assessmentView = getCurrentAssessmentView(note, mStudent, assessment);
            mContainer.addView(assessmentView);
        }
    }

    private void handleAssessment(Assessment assessment, int level) {
        Note note = NoteLab.get(this.getApplicationContext()).getNoteByStudentAndAssessment(assessment.getId().toString(), mStudent.getId().toString());
        if (note == null) {
            note = createNewNoteForAssessment(assessment);
        }
        View assessmentView = getAssessmentView(note, assessment);
        mContainer.addView(assessmentView);
        handleSubAssessments(assessment, level);
    }

    private void handleSubAssessments(Assessment assessment, int level) {
        List<Assessment> subAssessments = AssessmentLab.get(this.getApplicationContext()).getAssessmentsByParentId(assessment.getId().toString());
        for (Assessment subAssessment : subAssessments) {
            Note note = NoteLab.get(this.getApplicationContext()).getNoteByStudentAndAssessment(subAssessment.getId().toString(), mStudent.getId().toString());
            if (note == null) {
                note = createNewNoteForAssessment(subAssessment);
            }
            View subAssessmentView = getSubAssessmentView(note, subAssessment, level + 1);
            mContainer.addView(subAssessmentView);
            handleSubAssessments(subAssessment, level + 1);
        }
    }

    private View getSubAssessmentView(Note note, Assessment subAssessment, int level) {
        View columnForAssessment = getLayoutInflater().inflate(R.layout.list_item_note, null);
        TextView nameTextView = columnForAssessment.findViewById(R.id.name_textView);
        TextView noteTextView = columnForAssessment.findViewById(R.id.note_textView);
        columnForAssessment.findViewById(R.id.matricule_textView).setVisibility(View.GONE);
        nameTextView.setText(subAssessment.getNoteName());
        noteTextView.setText(note.getNoteValue() + "/" + subAssessment.getNoteMaxValue().intValue());
        setLeftMargin(columnForAssessment.findViewById(R.id.name_note_container), level);
        return columnForAssessment;
    }

    private Note createNewNoteForAssessment(Assessment assessment) {
        Note newNote = new Note(assessment.getId().toString(), mStudent.getId().toString());
        NoteLab.get(this.getApplicationContext()).addNote(newNote);
        return newNote;
    }

    private View getAssessmentView(Note note, Assessment assessment) {
        View columnForAssessment = getLayoutInflater().inflate(R.layout.list_item_note, null);
        TextView nameTextView = columnForAssessment.findViewById(R.id.name_textView);
        TextView noteTextView = columnForAssessment.findViewById(R.id.note_textView);
        columnForAssessment.findViewById(R.id.matricule_textView).setVisibility(View.GONE);
        nameTextView.setText(assessment.getNoteName());
        noteTextView.setText(note.getNoteValue() + "/" + assessment.getNoteMaxValue().intValue());
        return columnForAssessment;
    }


    private View getCurrentAssessmentView(final Note note, final Student student, final Assessment assessment) {
        View columnForAssessment = getLayoutInflater().inflate(R.layout.list_item_note, null);
        TextView nameTextView = columnForAssessment.findViewById(R.id.name_textView);
        TextView noteTextView = columnForAssessment.findViewById(R.id.note_textView);
        TextView studentNameView = columnForAssessment.findViewById(R.id.matricule_textView);
        LinearLayout container = columnForAssessment.findViewById(R.id.note_container);
        container.setBackgroundColor(getColor(R.color.blue));
        studentNameView.setText(student.getLastname() +" "+ student.getFirstname());
        nameTextView.setText(assessment.getNoteName());
        noteTextView.setText(note.getNoteValue()+"/"+ assessment.getNoteMaxValue().intValue());
        return columnForAssessment;
    }

    private void setLeftMargin(LinearLayout layout, int level) {
        int leftMargin = level * 64;
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) layout.getLayoutParams();
        params.setMargins(leftMargin, params.topMargin, params.rightMargin, params.bottomMargin);
        layout.setLayoutParams(params);
    }

}
