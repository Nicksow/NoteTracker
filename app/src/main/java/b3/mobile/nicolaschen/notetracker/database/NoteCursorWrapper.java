package b3.mobile.nicolaschen.notetracker.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.models.Assessment;
import b3.mobile.nicolaschen.notetracker.models.BacYear;
import b3.mobile.nicolaschen.notetracker.models.NoteStudent;
import b3.mobile.nicolaschen.notetracker.models.Student;
import b3.mobile.nicolaschen.notetracker.models.SubNote;
import b3.mobile.nicolaschen.notetracker.models.SubSubNote;

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
        String firstName = getString(getColumnIndex(NoteDbSchema.StudentTable.cols.FIRST_NAME));
        String lastName = getString(getColumnIndex(NoteDbSchema.StudentTable.cols.LAST_NAME));
        String uuidBacYear = getString(getColumnIndex(NoteDbSchema.StudentTable.cols.UUID_BAC_YEAR));
        float globalNote = getFloat(getColumnIndex(NoteDbSchema.StudentTable.cols.GLOBAL_NOTE));
        Student student = new Student(UUID.fromString(uuidString));
        student.setFirstname(firstName);
        student.setLastname(lastName);
        student.setUuidBacYear(uuidBacYear);
        student.setGlobalNote(globalNote);
        return student;
    }

    public Assessment getAssessment() {
        String uuidString = getString(getColumnIndex(NoteDbSchema.AssessmentTable.cols.UUID));
        String noteName = getString(getColumnIndex(NoteDbSchema.AssessmentTable.cols.NOTE_NAME));
        String uuidBacYear = getString(getColumnIndex(NoteDbSchema.AssessmentTable.cols.UUID_BAC_YEAR));
        Assessment assessment = new Assessment(UUID.fromString(uuidString));
        assessment.setNoteName(noteName);
        assessment.setUuidBacYear(uuidBacYear);
        return assessment;
    }

    public NoteStudent getNoteStudent() {
        String uuidString = getString(getColumnIndex(NoteDbSchema.NoteStudentTable.cols.UUID));
        String uuidStudent = getString(getColumnIndex(NoteDbSchema.NoteStudentTable.cols.UUID_STUDENT));
        String uuidAssessment = getString(getColumnIndex(NoteDbSchema.NoteStudentTable.cols.UUID_ASSESSMENT));
        float note = getFloat(getColumnIndex(NoteDbSchema.NoteStudentTable.cols.NOTE_VALUE));
        NoteStudent noteStudent = new NoteStudent(UUID.fromString(uuidString));
        noteStudent.setUuidStudent(uuidStudent);
        noteStudent.setUuidAssessment(uuidAssessment);
        noteStudent.setNoteValue(note);
        return noteStudent;
    }

    public SubNote getSubNote() {
        String uuidString = getString(getColumnIndex(NoteDbSchema.SubNoteTable.cols.UUID));
        String noteName = getString(getColumnIndex(NoteDbSchema.SubNoteTable.cols.SUB_NOTE_NAME));
        String uuidNoteStudent = getString(getColumnIndex(NoteDbSchema.SubNoteTable.cols.UUID_NOTE_STUDENT));
        float note = getFloat(getColumnIndex(NoteDbSchema.SubNoteTable.cols.SUB_NOTE_VALUE));
        SubNote subNote = new SubNote(UUID.fromString(uuidString));
        subNote.setSubNoteName(noteName);
        subNote.setUuidNoteStudent(uuidNoteStudent);
        subNote.setSubNoteValue(note);
        return subNote;
    }

    public SubSubNote getSubSubNote() {
        String uuidString = getString(getColumnIndex(NoteDbSchema.SubSubNoteTable.cols.UUID));
        String noteName = getString(getColumnIndex(NoteDbSchema.SubSubNoteTable.cols.SUB_SUB_NOTE_NAME));
        String uuidSubNote = getString(getColumnIndex(NoteDbSchema.SubSubNoteTable.cols.UUID_SUB_NOTE));
        float note = getFloat(getColumnIndex(NoteDbSchema.SubSubNoteTable.cols.SUB_SUB_NOTE_VALUE));
        SubSubNote subSubNote = new SubSubNote(UUID.fromString(uuidString));
        subSubNote.setSubSubNoteName(noteName);
        subSubNote.setUuidSubNote(uuidSubNote);
        subSubNote.setSubSubNoteValue(note);
        return subSubNote;
    }

}