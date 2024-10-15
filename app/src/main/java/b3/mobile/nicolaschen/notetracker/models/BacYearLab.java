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

public class BacYearLab {
    public static BacYearLab sBacYearLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    public static BacYearLab get(Context context) {
        if (sBacYearLab == null) {
            sBacYearLab = new BacYearLab(context);
        }
        return sBacYearLab;
    }

    private BacYearLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new NoteBaseHelper(mContext).getWritableDatabase();
    }

    public void addBacYear(BacYear bacYear) {
        mDatabase.insert(NoteDbSchema.BacYearTable.NAME, null,
                getContentValues(bacYear));
    }

    public void updateBacYear(BacYear bacYear) {
        String uuidString = bacYear.getId().toString();
        ContentValues values = getContentValues(bacYear);
        mDatabase.update(NoteDbSchema.BacYearTable.NAME,
                values,
                NoteDbSchema.BacYearTable.cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public BacYear getBacYear(UUID id) {
        NoteCursorWrapper cursor =
                queryBacYears(NoteDbSchema.BacYearTable.cols.UUID + " = ? ",
                        new String[]{id.toString()}
                );
        try {
            if (cursor.getCount() == 0)
                return null;
            cursor.moveToFirst();
            return cursor.getBacYear();
        } finally {
            cursor.close();
        }
    }

    public List<BacYear> getBacYears() {
        ArrayList<BacYear> bacYears = new ArrayList<>();
        NoteCursorWrapper cursor = queryBacYears(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                bacYears.add(cursor.getBacYear());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return bacYears;
    }

    private static ContentValues getContentValues(BacYear bacYear) {
        ContentValues values = new ContentValues();
        values.put(NoteDbSchema.BacYearTable.cols.UUID, bacYear.getId().toString());
        values.put(NoteDbSchema.BacYearTable.cols.NAME, bacYear.getName());
        return values;
    }

    private NoteCursorWrapper queryBacYears(String whereClause, String[] whereArgs) {
        return new NoteCursorWrapper(mDatabase.query(
                NoteDbSchema.BacYearTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        ));
    }

}