package b3.mobile.nicolaschen.notetracker.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.models.Assessment;
import b3.mobile.nicolaschen.notetracker.models.BacYear;
import b3.mobile.nicolaschen.notetracker.models.Note;
import b3.mobile.nicolaschen.notetracker.models.Student;

public class NoteCursorWrapper extends CursorWrapper {
    public NoteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public BacYear getBacYear() {
        String uuidString = getString(getColumnIndex(NoteDbSchema.BacYearTable.cols.UUID));
        String name = getString(getColumnIndex(NoteDbSchema.BacYearTable.cols.NAME));
        BacYear bacYear = new BacYear(UUID.fromString(uuidString));
        bacYear.setName(name);
        return bacYear;
    }

    public Student getStudent() {
        String uuidString = getString(getColumnIndex(NoteDbSchema.StudentTable.cols.UUID));
        String matricule = getString(getColumnIndex(NoteDbSchema.StudentTable.cols.MATRICULE));
        String firstName = getString(getColumnIndex(NoteDbSchema.StudentTable.cols.FIRST_NAME));
        String lastName = getString(getColumnIndex(NoteDbSchema.StudentTable.cols.LAST_NAME));
        String uuidBacYear = getString(getColumnIndex(NoteDbSchema.StudentTable.cols.UUID_BAC_YEAR));
        Student student = new Student(UUID.fromString(uuidString));
        student.setMatricule(matricule);
        student.setFirstname(firstName);
        student.setLastname(lastName);
        student.setUuidBacYear(uuidBacYear);
        return student;
    }

    public Assessment getAssessment() {
        String uuidString = getString(getColumnIndex(NoteDbSchema.AssessmentTable.cols.UUID));
        String noteName = getString(getColumnIndex(NoteDbSchema.AssessmentTable.cols.NOTE_NAME));
        String uuidBacYear = getString(getColumnIndex(NoteDbSchema.AssessmentTable.cols.UUID_BAC_YEAR));
        String parentId = getString(getColumnIndex(NoteDbSchema.AssessmentTable.cols.PARENT_ID));
        Double maxNote = getDouble(getColumnIndex(NoteDbSchema.AssessmentTable.cols.MAX_NOTE));
        Assessment assessment = new Assessment(UUID.fromString(uuidString));
        assessment.setNoteName(noteName);
        assessment.setUuidBacYear(uuidBacYear);
        assessment.setParentUuid(parentId);
        assessment.setNoteMaxValue(maxNote);
        return assessment;
    }

    public Note getNote() {
        String uuidAssessment = getString(getColumnIndex(NoteDbSchema.NoteTable.cols.UUID_ASSESSMENT));
        String uuidStudent = getString(getColumnIndex(NoteDbSchema.NoteTable.cols.UUID_STUDENT));
        Double noteValue = getDouble(getColumnIndex(NoteDbSchema.NoteTable.cols.NOTE));
        Note note = new Note();
        note.setAssessmentUuid(uuidAssessment);
        note.setStudentUuid(uuidStudent);
        note.setNoteValue(noteValue);
        return note;
    }
}