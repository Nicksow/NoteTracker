package b3.mobile.nicolaschen.notetracker.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import b3.mobile.nicolaschen.notetracker.database.NoteBaseHelper;
import b3.mobile.nicolaschen.notetracker.database.NoteCursorWrapper;
import b3.mobile.nicolaschen.notetracker.database.NoteDbSchema;


public class NoteLab {
    public static NoteLab sNoteLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static NoteLab get(Context context) {
        if (sNoteLab == null) {
            sNoteLab = new NoteLab(context);
        }
        return sNoteLab;
    }

    private NoteLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new NoteBaseHelper(mContext).getWritableDatabase();
    }

    public void addNote(Note note) {
        mDatabase.insert(NoteDbSchema.NoteTable.NAME, null, getContentValues(note));
    }


    public Note getNoteByStudentAndAssessment(String AssessmentId, String studentId) {
        NoteCursorWrapper cursor = queryNotes(
                NoteDbSchema.NoteTable.cols.UUID_ASSESSMENT + " = ? AND " +
                        NoteDbSchema.NoteTable.cols.UUID_STUDENT + " = ?",
                new String[]{AssessmentId, studentId}
        );
        try {
            if (cursor.getCount() == 0)
                return null;
            cursor.moveToFirst();
            return cursor.getNote();
        } finally {
            cursor.close();
        }
    }


    public void updateNoteValue(Note note) {
        String assessmentId = note.getAssessmentUuid();
        String studentId = note.getStudentUuid();
        ContentValues values = getContentValues(note);
        mDatabase.update(NoteDbSchema.NoteTable.NAME,
                values,
                NoteDbSchema.NoteTable.cols.UUID_ASSESSMENT + " = ? AND " +
                        NoteDbSchema.NoteTable.cols.UUID_STUDENT + " = ?",
                new String[]{assessmentId, studentId}
        );
    }

    public Note[] getNotesByStudent(String string) {
        NoteCursorWrapper cursor = queryNotes(
                NoteDbSchema.NoteTable.cols.UUID_STUDENT + " = ?",
                new String[]{string}
        );
        Note[] notes = new Note[cursor.getCount()];
        try {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                notes[i] = cursor.getNote();
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return notes;
    }

    private static ContentValues getContentValues(Note note) {
        ContentValues values = new ContentValues();
        values.put(NoteDbSchema.NoteTable.cols.UUID_ASSESSMENT, note.getAssessmentUuid());
        values.put(NoteDbSchema.NoteTable.cols.UUID_STUDENT, note.getStudentUuid());
        values.put(NoteDbSchema.NoteTable.cols.NOTE, note.getNoteValue());
        return values;
    }

    private NoteCursorWrapper queryNotes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                NoteDbSchema.NoteTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new NoteCursorWrapper(cursor);
    }

}