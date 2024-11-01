package b3.mobile.nicolaschen.notetracker.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import b3.mobile.nicolaschen.notetracker.database.NoteBaseHelper;
import b3.mobile.nicolaschen.notetracker.database.NoteCursorWrapper;
import b3.mobile.nicolaschen.notetracker.database.NoteDbSchema;

public class StudentLab {
    public static StudentLab sStudentLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    public static StudentLab get (Context context) {
        if (sStudentLab == null) {
            sStudentLab = new StudentLab(context);
        }
        return sStudentLab;
    }

    private StudentLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new NoteBaseHelper(mContext).getWritableDatabase();
    }

    public void addStudent(Student student) {
        mDatabase.insert(NoteDbSchema.StudentTable.NAME, null,
                getContentValues(student));
    }

    public void updateStudent(Student student) {
        String uuidString = student.getId().toString();
        mDatabase.update(NoteDbSchema.StudentTable.NAME,
                getContentValues(student),
                NoteDbSchema.StudentTable.cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        NoteCursorWrapper cursor = queryStudents(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                students.add(cursor.getStudent());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return students;
    }

    public List<Student> getStudentsByBacYear(String bacYearId) {
        List<Student> students = new ArrayList<>();
        NoteCursorWrapper cursor = queryStudents(NoteDbSchema.StudentTable.cols.UUID_BAC_YEAR + " = ? ",
                new String[]{bacYearId}
        );
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                students.add(cursor.getStudent());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return students;
    }

    public Student getStudent(UUID id) {
        if (id == null) {
            return null;
        }
        NoteCursorWrapper cursor =
                queryStudents(NoteDbSchema.StudentTable.cols.UUID + " = ? ",
                        new String[]{id.toString()}
                );
        try {
            if (cursor.getCount() == 0)
                return null;
            cursor.moveToFirst();
            return cursor.getStudent();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(Student student) {
        ContentValues values = new ContentValues();
        values.put(NoteDbSchema.StudentTable.cols.UUID, student.getId().toString());
        values.put(NoteDbSchema.StudentTable.cols.MATRICULE, student.getMatricule());
        values.put(NoteDbSchema.StudentTable.cols.FIRST_NAME, student.getFirstname());
        values.put(NoteDbSchema.StudentTable.cols.LAST_NAME, student.getLastname());
        values.put(NoteDbSchema.StudentTable.cols.UUID_BAC_YEAR, student.getUuidBacYear());
        return values;
    }

    private NoteCursorWrapper queryStudents(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                NoteDbSchema.StudentTable.NAME,
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
