package b3.mobile.nicolaschen.notetracker.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.database.NoteBaseHelper;
import b3.mobile.nicolaschen.notetracker.database.NoteCursorWrapper;
import b3.mobile.nicolaschen.notetracker.database.NoteDbSchema;

public class AssessmentLab {
    public static AssessmentLab sAssessmentLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    public static AssessmentLab get(Context context) {
        if (sAssessmentLab == null) {
            sAssessmentLab = new AssessmentLab(context);
        }
        return sAssessmentLab;
    }

    private AssessmentLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new NoteBaseHelper(mContext).getWritableDatabase();
    }

    public void addAssessment(Assessment assessment) {
        mDatabase.insert(NoteDbSchema.AssessmentTable.NAME, null,
                getContentValues(assessment));
    }



    public List<Assessment> getAssessmentsByBacYear(String bacYearId) {
        ArrayList<Assessment> assessments = new ArrayList<>();
        NoteCursorWrapper cursor = queryAssessments(
                NoteDbSchema.AssessmentTable.cols.UUID_BAC_YEAR + " = ? AND " +
                        NoteDbSchema.AssessmentTable.cols.PARENT_ID + " IS NULL",
                new String[]{bacYearId},
                NoteDbSchema.AssessmentTable.cols.NOTE_NAME + " ASC"
        );
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                assessments.add(cursor.getAssessment());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return assessments;
    }

    private static ContentValues getContentValues(Assessment assessment) {
        ContentValues values = new ContentValues();
        values.put(NoteDbSchema.AssessmentTable.cols.UUID, assessment.getId().toString());
        values.put(NoteDbSchema.AssessmentTable.cols.NOTE_NAME, assessment.getNoteName());
        values.put(NoteDbSchema.AssessmentTable.cols.PARENT_ID, assessment.getParentUuid());
        values.put(NoteDbSchema.AssessmentTable.cols.UUID_BAC_YEAR, assessment.getUuidBacYear());
        values.put(NoteDbSchema.AssessmentTable.cols.MAX_NOTE, assessment.getNoteMaxValue());
        return values;
    }

    public NoteCursorWrapper queryAssessments(String whereClause, String[] whereArgs, String orderBy) {
        return new NoteCursorWrapper(mDatabase.query(
                NoteDbSchema.AssessmentTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                orderBy
        ));
    }


    public List<Assessment> getAssessmentsByParentId(String parentId) {
        ArrayList<Assessment> assessments = new ArrayList<>();
        NoteCursorWrapper cursor = queryAssessments(NoteDbSchema.AssessmentTable.cols.PARENT_ID + " = ?",
                new String[]{parentId},NoteDbSchema.AssessmentTable.cols.NOTE_NAME + " ASC");
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                assessments.add(cursor.getAssessment());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return assessments;
    }

    public Assessment getAssessment(UUID assessmentId) {
        NoteCursorWrapper cursor = queryAssessments(NoteDbSchema.AssessmentTable.cols.UUID + " = ?",
                new String[]{assessmentId.toString()},null);
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getAssessment();
        } finally {
            cursor.close();
        }
    }

    public Assessment[] getSubAssessments(String bacYear, String mParentId) {
        ArrayList<Assessment> assessments = new ArrayList<>();
        NoteCursorWrapper cursor = queryAssessments(
                NoteDbSchema.AssessmentTable.cols.UUID_BAC_YEAR + " = ? AND " +
                        NoteDbSchema.AssessmentTable.cols.PARENT_ID + " = ?",
                new String[]{bacYear, mParentId},null
        );
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                assessments.add(cursor.getAssessment());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return assessments.toArray(new Assessment[assessments.size()]);
    }
}
