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
        db.execSQL("CREATE TABLE "+ BacYearTable.NAME + "("
                + "_id integer PRIMARY KEY AUTOINCREMENT, " + BacYearTable.cols.UUID + ", "
                + BacYearTable.cols.NAME + ")"
        );
        db.execSQL("CREATE TABLE "+ StudentTable.NAME + "("
                + "_id integer PRIMARY KEY AUTOINCREMENT, " + StudentTable.cols.UUID + ", "
                + StudentTable.cols.MATRICULE + ", " + StudentTable.cols.FIRST_NAME + ", "
                + StudentTable.cols.LAST_NAME + ", " + StudentTable.cols.UUID_BAC_YEAR + ")"
        );
        db.execSQL("CREATE TABLE "+ AssessmentTable.NAME + "("
                + "_id integer PRIMARY KEY AUTOINCREMENT, " + AssessmentTable.cols.UUID + ", "
                + AssessmentTable.cols.NOTE_NAME + ", " + AssessmentTable.cols.UUID_BAC_YEAR + ", "
                + AssessmentTable.cols.PARENT_ID + ", " + AssessmentTable.cols.MAX_NOTE + ")"
        );
        db.execSQL("CREATE TABLE "+ NoteTable.NAME + "("
                + "_id integer PRIMARY KEY AUTOINCREMENT, " + NoteTable.cols.UUID + ", "
                + NoteTable.cols.UUID_ASSESSMENT + ", " + NoteTable.cols.UUID_STUDENT + ", "
                + NoteTable.cols.NOTE + ")"
        );

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
    }
}