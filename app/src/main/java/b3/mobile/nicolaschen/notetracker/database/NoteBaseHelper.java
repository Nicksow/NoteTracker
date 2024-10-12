package b3.mobile.nicolaschen.notetracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import b3.mobile.nicolaschen.notetracker.database.NoteDbSchema.BacYearTable;
import b3.mobile.nicolaschen.notetracker.database.NoteDbSchema.StudentTable;
import b3.mobile.nicolaschen.notetracker.database.NoteDbSchema.AssessmentTable;
import b3.mobile.nicolaschen.notetracker.database.NoteDbSchema.NoteStudentTable;
import b3.mobile.nicolaschen.notetracker.database.NoteDbSchema.SubNoteTable;
import b3.mobile.nicolaschen.notetracker.database.NoteDbSchema.SubSubNoteTable;


public class NoteBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "noteBase.db";
    public NoteBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ BacYearTable.NAME + "("
                + "_id integer PRIMARY KEY AUTOINCREMENT, " + BacYearTable.cols.UUID + ", "
                + BacYearTable.cols.NAME + ")"
        );
        db.execSQL("CREATE TABLE "+ StudentTable.NAME + "("
                + "_id integer PRIMARY KEY AUTOINCREMENT, " + StudentTable.cols.UUID + ", "
                + StudentTable.cols.FIRST_NAME + ", " + StudentTable.cols.LAST_NAME + ", "
                + StudentTable.cols.UUID_BAC_YEAR + ", " + StudentTable.cols.GLOBAL_NOTE + ")"
        );
        db.execSQL("CREATE TABLE "+ AssessmentTable.NAME + "("
                + "_id integer PRIMARY KEY AUTOINCREMENT, " + AssessmentTable.cols.UUID + ", "
                + AssessmentTable.cols.NOTE_NAME + ", " + AssessmentTable.cols.UUID_BAC_YEAR + ")"
        );
        db.execSQL("CREATE TABLE "+ NoteStudentTable.NAME + "("
                + "_id integer PRIMARY KEY AUTOINCREMENT, " + NoteStudentTable.cols.UUID + ", "
                + NoteStudentTable.cols.UUID_STUDENT + ", " + NoteStudentTable.cols.UUID_ASSESSMENT + ", "
                + NoteStudentTable.cols.NOTE_VALUE + ")"
        );
        db.execSQL("CREATE TABLE "+ SubNoteTable.NAME + "("
                + "_id integer PRIMARY KEY AUTOINCREMENT, " + SubNoteTable.cols.UUID + ", "
                + SubNoteTable.cols.SUB_NOTE_NAME + ", " + SubNoteTable.cols.SUB_NOTE_VALUE + ", "
                + SubNoteTable.cols.UUID_NOTE_STUDENT + ")"
        );
        db.execSQL("CREATE TABLE "+ SubSubNoteTable.NAME + "("
                + "_id integer PRIMARY KEY AUTOINCREMENT, " + SubSubNoteTable.cols.UUID + ", "
                + SubSubNoteTable.cols.SUB_SUB_NOTE_NAME + ", " + SubSubNoteTable.cols.SUB_SUB_NOTE_VALUE + ", "
                + SubSubNoteTable.cols.UUID_SUB_NOTE + ")"
        );

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
    }
}
