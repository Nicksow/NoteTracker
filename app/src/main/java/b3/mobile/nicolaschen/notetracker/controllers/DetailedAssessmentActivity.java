package b3.mobile.nicolaschen.notetracker.controllers;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import b3.mobile.nicolaschen.notetracker.utils.MathUtils;

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
        initActivityData();
        initActivityView();
        updateUI();
    }
    @SuppressLint("SetTextI18n")
    private void initActivityView() {
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
    }

    private void initActivityData() {
        UUID bacYear_id = (UUID) getIntent().getSerializableExtra(BAC_YEAR_ID);
        mBacYear = BacYearLab.get(getApplicationContext()).getBacYear(bacYear_id);
        UUID assessment_id = (UUID) getIntent().getSerializableExtra(ASSESSMENT_ID);
        mAssessment = AssessmentLab.get(getApplicationContext()).getAssessment(assessment_id);
        mBacYearId = mBacYear.getId().toString();
        mAssessmentId = mAssessment.getId().toString();
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
        Note note = NoteLab.get(this.getApplicationContext()).getNoteByStudentAndAssessment(mAssessment.getId().toString(), student.getId().toString());
        if (note != null) {
            return note;
        }
        Note newNote = new Note(mAssessment.getId().toString(), student.getId().toString());
        NoteLab.get(this.getApplicationContext()).addNote(newNote);
        return newNote;
    }

    @SuppressLint("SetTextI18n")
    private View getAssessmentView(final Note note, final Student student) {
        View columnForAssessment = getLayoutInflater().inflate(R.layout.list_item_note, null);
        TextView nameTextView = columnForAssessment.findViewById(R.id.name_textView);
        TextView noteTextView = columnForAssessment.findViewById(R.id.note_textView);
        TextView matriculeTextView = columnForAssessment.findViewById(R.id.matricule_textView);
        nameTextView.setText(student.getLastname() +" "+ student.getFirstname());
        matriculeTextView.setText(student.getMatricule());
        noteTextView.setText(MathUtils.roundToNearestHalf(note.getNoteValue()) +"/"+mAssessment.getNoteMaxValue().intValue());
        columnForAssessment.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), DetailedStudentNoteActivity.class);
            intent.putExtra(DetailedStudentNoteActivity.STUDENT_ID, student.getId());
            intent.putExtra(DetailedStudentNoteActivity.ASSESSMENT_ID, mAssessment.getId());
            intent.putExtra(DetailedStudentNoteActivity.BAC_YEAR_ID, mBacYear.getId());
            startActivity(intent);
        });
        return columnForAssessment;
    }
}