package b3.mobile.nicolaschen.notetracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.R;
import b3.mobile.nicolaschen.notetracker.controllers.ListActivity.EditAssessmentActivity;
import b3.mobile.nicolaschen.notetracker.models.Assessment;
import b3.mobile.nicolaschen.notetracker.models.AssessmentLab;
import b3.mobile.nicolaschen.notetracker.models.BacYear;
import b3.mobile.nicolaschen.notetracker.models.BacYearLab;
import b3.mobile.nicolaschen.notetracker.models.Note;
import b3.mobile.nicolaschen.notetracker.models.NoteLab;
import b3.mobile.nicolaschen.notetracker.models.Student;
import b3.mobile.nicolaschen.notetracker.models.StudentLab;

public class DetailedAssessmentActivity extends AppCompatActivity {
    public static final String BAC_YEAR_ID = "BACYEAR_ID";
    public static final String ASSESSMENT_ID = "ASSESSMENT_ID";
    private LinearLayout mContainer;
    private ImageButton mEditButton;
    private TextView mTitle;
    private BacYear mBacYear;
    private Assessment mAssessment;
    private String mBacYearId;
    private String mAssessmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detailed_assessment);
        UUID bacYear_id = (UUID) getIntent().getSerializableExtra(BAC_YEAR_ID);
        mBacYear = BacYearLab.get(getApplicationContext()).getBacYear(bacYear_id);
        UUID assessment_id = (UUID) getIntent().getSerializableExtra(ASSESSMENT_ID);
        mAssessment = AssessmentLab.get(getApplicationContext()).getAssessment(assessment_id);
        mBacYearId = mBacYear.getId().toString();
        mAssessmentId = mAssessment.getId().toString();

        mContainer = findViewById(R.id.list_container);
        mEditButton = findViewById(R.id.edit_button);
        mEditButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), EditAssessmentActivity.class);
            intent.putExtra(EditAssessmentActivity.BAC_YEAR_ID, mBacYear.getId());
            intent.putExtra(EditAssessmentActivity.ASSESSMENT_NAME, mAssessment.getNoteName());
            intent.putExtra(EditAssessmentActivity.PARENT_ID, mAssessment.getId());
            startActivity(intent);
        });
        mTitle = findViewById(R.id.assessmentName_textView);
        mTitle.setText(mAssessment.getNoteName() + " - " + mBacYear.getName());
        updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        mContainer.removeAllViews();
        List<Student> students = StudentLab.get(this.getApplicationContext()).getStudentsByBacYear(mBacYearId);

        for (Student student : students) {
            Note note = getOrCreateNoteForStudent(student);
            View assessmentView = getAssessmentView(note, student);
            mContainer.addView(assessmentView);
        }
    }

    private Note getOrCreateNoteForStudent(Student student) {
        List<Assessment> assessments = AssessmentLab.get(this.getApplicationContext()).getAssessmentsByParentId(mAssessmentId);
        for (Assessment assessment : assessments) {
            Note note = NoteLab.get(this.getApplicationContext()).getNoteByStudentAndAssessment(assessment.getId().toString(), student.getId().toString());
            if (note != null) {
                return note;
            }
        }
        Assessment newAssessment = new Assessment(null, mBacYearId, mAssessmentId, 20.0);
        AssessmentLab.get(this.getApplicationContext()).addAssessment(newAssessment);
        Note newNote = new Note(newAssessment.getId().toString(), student.getId().toString());
        NoteLab.get(this.getApplicationContext()).addNote(newNote);
        return newNote;
    }

    private View getAssessmentView(final Note note, final Student student) {
        View columnForAssessment = getLayoutInflater().inflate(R.layout.list_item_note, null);
        TextView nameTextView = columnForAssessment.findViewById(R.id.name_textView);
        TextView noteTextView = columnForAssessment.findViewById(R.id.note_textView);
        TextView matriculeTextView = columnForAssessment.findViewById(R.id.matricule_textView);
        nameTextView.setText(student.getLastname() +" "+ student.getFirstname());
        matriculeTextView.setText(student.getMatricule());
        noteTextView.setText(String.valueOf(note.getNoteValue()));
        columnForAssessment.setOnClickListener(view -> {
            // Open the DetailedStudentActivity
        });
        return columnForAssessment;
    }
}