package b3.mobile.nicolaschen.notetracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import b3.mobile.nicolaschen.notetracker.database.NoteDbSchema.BacYearTable;
import b3.mobile.nicolaschen.notetracker.database.NoteDbSchema.StudentTable;
import b3.mobile.nicolaschen.notetracker.database.NoteDbSchema.AssessmentTable;
import b3.mobile.nicolaschen.notetracker.database.NoteDbSchema.NoteTable;


public class NoteBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "noteBase.db";
    public NoteBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + BacYearTable.NAME + " ("
                + BacYearTable.cols.UUID + " TEXT PRIMARY KEY, "
                + BacYearTable.cols.NAME + " TEXT )"
        );
        db.execSQL("CREATE TABLE " + StudentTable.NAME + " ("
                + StudentTable.cols.UUID + " TEXT PRIMARY KEY, "
                + StudentTable.cols.MATRICULE + " TEXT , "
                + StudentTable.cols.FIRST_NAME + " TEXT, "
                + StudentTable.cols.LAST_NAME + " TEXT , "
                + StudentTable.cols.UUID_BAC_YEAR + " TEXT, "
                + "FOREIGN KEY (" + StudentTable.cols.UUID_BAC_YEAR + ") REFERENCES " + BacYearTable.NAME + "(" + BacYearTable.cols.UUID + "))"
        );
        db.execSQL("CREATE TABLE " + AssessmentTable.NAME + " ("
                + AssessmentTable.cols.UUID + " TEXT PRIMARY KEY, "
                + AssessmentTable.cols.NOTE_NAME + " TEXT, "
                + AssessmentTable.cols.UUID_BAC_YEAR + " TEXT, "
                + AssessmentTable.cols.PARENT_ID + " TEXT, "  // Allow NULL values
                + AssessmentTable.cols.MAX_NOTE + " DOUBLE , "
                + "FOREIGN KEY (" + AssessmentTable.cols.UUID_BAC_YEAR + ") REFERENCES " + BacYearTable.NAME + "(" + BacYearTable.cols.UUID + "), "
                + "FOREIGN KEY (" + AssessmentTable.cols.PARENT_ID + ") REFERENCES " + AssessmentTable.NAME + "(" + AssessmentTable.cols.UUID + "))"
        );
        db.execSQL("CREATE TABLE " + NoteTable.NAME + " ("
                + NoteTable.cols.UUID_ASSESSMENT + " TEXT PRIMARY KEY, "
                + NoteTable.cols.UUID_STUDENT + " TEXT , "
                + NoteTable.cols.NOTE + " DOUBLE , "
                + "FOREIGN KEY (" + NoteTable.cols.UUID_ASSESSMENT + ") REFERENCES " + AssessmentTable.NAME + "(" + AssessmentTable.cols.UUID + "), "
                + "FOREIGN KEY (" + NoteTable.cols.UUID_STUDENT + ") REFERENCES " + StudentTable.NAME + "(" + StudentTable.cols.UUID + "))"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
    }
}