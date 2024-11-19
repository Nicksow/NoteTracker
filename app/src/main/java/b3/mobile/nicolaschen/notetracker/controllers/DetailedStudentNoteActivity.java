package b3.mobile.nicolaschen.notetracker.controllers;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import b3.mobile.nicolaschen.notetracker.utils.DisplayUtils;
import b3.mobile.nicolaschen.notetracker.utils.MathUtils;

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
        initActivityData();
        updateUI();
    }
    private void initActivityData() {
        mContainer = findViewById(R.id.fragment_container);
        mStudent = StudentLab.get(getApplicationContext()).getStudent((UUID) getIntent().getSerializableExtra(STUDENT_ID));
        mAssessment = AssessmentLab.get(getApplicationContext()).getAssessment((UUID) getIntent().getSerializableExtra(ASSESSMENT_ID));
        mBacYear = BacYearLab.get(getApplicationContext()).getBacYear((UUID) getIntent().getSerializableExtra(BAC_YEAR_ID));
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        mContainer.removeAllViews();
        calculateAverage(mAssessment);
        createCurrentAssessmentView();
        List<Assessment> assessments = AssessmentLab.get(this.getApplicationContext()).getAssessmentsByParentId(mAssessment.getId().toString());
        for (Assessment assessment : assessments) {
            createAssessmentView(assessment, 0);
        }
    }

    private double calculateAverage(Assessment parentAssessment) {
        List<Assessment> subAssessments = AssessmentLab.get(this.getApplicationContext()).getAssessmentsByParentId(parentAssessment.getId().toString());
        double totalNote = 0.0;
        double totalMaxNote = 0.0;
        for (Assessment subAssessment : subAssessments) {
            if (subAssessment == null) {
                continue;
            }
            Note note = NoteLab.get(this.getApplicationContext()).getNoteByStudentAndAssessment(subAssessment.getId().toString(), mStudent.getId().toString());
            if (note != null && note.getNoteValue() != null) {
                totalNote += note.getNoteValue();
                totalMaxNote += subAssessment.getNoteMaxValue();
            } else {
                double subAverage = calculateAverage(subAssessment);
                if (subAverage > 0 && subAssessment.getNoteMaxValue() != null) {
                    totalNote += subAverage;
                    totalMaxNote += subAssessment.getNoteMaxValue();
                }
            }
        }
        if (totalMaxNote == 0) {
            return 0.0;
        }
        double average = (totalNote / totalMaxNote) * parentAssessment.getNoteMaxValue();
        double roundedToCent = Math.round(average * 100.0) / 100.0;
        double roundedForDisplay= MathUtils.roundToNearestHalf(roundedToCent);
        updateParentNoteValue(parentAssessment, roundedToCent);
        return roundedForDisplay;
    }

    private void updateParentNoteValue(Assessment parentAssessment, double roundedToCent) {
        Note parentNote = NoteLab.get(this.getApplicationContext()).getNoteByStudentAndAssessment(parentAssessment.getId().toString(), mStudent.getId().toString());
        if (parentNote == null) {
            parentNote = new Note(parentAssessment.getId().toString(), mStudent.getId().toString());
        }
        parentNote.setNoteValue(roundedToCent);
        NoteLab.get(this.getApplicationContext()).updateNoteValue(parentNote);
    }

    private void createCurrentAssessmentView() {
        Note note = NoteLab.get(this.getApplicationContext()).getNoteByStudentAndAssessment(mAssessment.getId().toString(), mStudent.getId().toString());
        if (note != null) {
            Assessment assessment = AssessmentLab.get(this.getApplicationContext()).getAssessment(mAssessment.getId());
            View assessmentView = getCurrentAssessmentView(note, mStudent, assessment);
            mContainer.addView(assessmentView);
        }
    }

    private void createAssessmentView(Assessment assessment, int level) {
        Note note = NoteLab.get(this.getApplicationContext()).getNoteByStudentAndAssessment(assessment.getId().toString(), mStudent.getId().toString());
        if (note == null) note = createNewNoteForAssessment(assessment);
        View assessmentView = getAssessmentView(note, assessment, level);
        mContainer.addView(assessmentView);
        handleSubAssessments(assessment, level);
    }

    private void handleSubAssessments(Assessment assessment, int level) {
        List<Assessment> subAssessments = AssessmentLab.get(this.getApplicationContext()).getAssessmentsByParentId(assessment.getId().toString());
        for (Assessment subAssessment : subAssessments) {
            createAssessmentView(subAssessment, level + 1);
        }
    }

    private Note createNewNoteForAssessment(Assessment assessment) {
        Note newNote = new Note(assessment.getId().toString(), mStudent.getId().toString());
        newNote.setNoteValue(null);
        NoteLab.get(this.getApplicationContext()).addNote(newNote);
        return newNote;
    }

    private View getAssessmentView(Note note, Assessment assessment, int level) {
        View columnForAssessment = getLayoutInflater().inflate(R.layout.list_item_note_editable, null);
        TextView nameTextView = columnForAssessment.findViewById(R.id.noteName_textView);
        EditText noteEditText = columnForAssessment.findViewById(R.id.note_editText);
        TextView maxNoteTextView = columnForAssessment.findViewById(R.id.maxNote_textView);
        nameTextView.setText(assessment.getNoteName());
        noteEditText.setText(note.getNoteValue() == null ? "TBD" : String.valueOf(MathUtils.roundToNearestHalf(note.getNoteValue())));
        maxNoteTextView.setText("/" + assessment.getNoteMaxValue().intValue());
        noteEditText.setOnKeyListener((v, keyCode, event) -> handleNoteEdit(note, noteEditText, keyCode, event, assessment));
        DisplayUtils.setLeftMargin(columnForAssessment.findViewById(R.id.note_container), level);
        return columnForAssessment;
    }

    private boolean handleNoteEdit(Note note, EditText noteEditText, int keyCode, KeyEvent event, Assessment assessment) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
            String text = noteEditText.getText().toString();
            if (text.isEmpty()) {
                note.setNoteValue(null);
            } else {
                try {
                    double noteValue = Double.parseDouble(text);
                    if (noteValue % 0.5 != 0) {
                        return DisplayToast("La note doit être un multiple de 0.5");
                    }
                    if (noteValue > assessment.getNoteMaxValue()) {
                        return DisplayToast("La note ne peut pas être supérieure à la note maximale");
                    }
                    note.setNoteValue(noteValue);
                } catch (NumberFormatException e) {
                    return DisplayToast("La note doit être un nombre");
                }
            }
            NoteLab.get(getApplicationContext()).updateNoteValue(note);
            resetParentNoteValue(assessment);
            updateUI();
            return true;
        }
        return false;
    }

    private boolean DisplayToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        return true;
    }

    private void resetParentNoteValue(Assessment subAssessment) {
        Assessment parentAssessment = AssessmentLab.get(this.getApplicationContext()).getAssessment(UUID.fromString(subAssessment.getParentUuid()));
        Note parentNote = NoteLab.get(this.getApplicationContext()).getNoteByStudentAndAssessment(parentAssessment.getId().toString(), mStudent.getId().toString());
        parentNote.setNoteValue(null);
        NoteLab.get(this.getApplicationContext()).updateNoteValue(parentNote);
    }

    private View getCurrentAssessmentView(final Note note, final Student student, final Assessment assessment) {
        View columnForAssessment = getLayoutInflater().inflate(R.layout.list_item_note, null);
        TextView nameTextView = columnForAssessment.findViewById(R.id.name_textView);
        TextView noteTextView = columnForAssessment.findViewById(R.id.note_textView);
        TextView studentNameView = columnForAssessment.findViewById(R.id.matricule_textView);
        (columnForAssessment.findViewById(R.id.note_container)).setBackgroundColor(getColor(R.color.blue));
        studentNameView.setText(student.getLastname() +" "+ student.getFirstname());
        nameTextView.setText(assessment.getNoteName());
        double nearestHalf = MathUtils.roundToNearestHalf(note.getNoteValue());
        noteTextView.setText(nearestHalf +"/"+ assessment.getNoteMaxValue().intValue());
        return columnForAssessment;
    }
}
