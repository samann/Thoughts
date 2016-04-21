package droidowl.thoughts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by droidowl on 4/20/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "thoughts.db";
    public static final String TABLE_NAME = "records";
    public static final String THOUGHTS_TRIGGER_COLUMN = "trigger";
    public static final String THOUGHTS_BEFORE_COLUMN = "before";
    public static final String THOUGHTS_UNHELPFUL_COLUMN = "unhelpful";
    public static final String THOUGHTS_SUPPORT_COLUMN = "support";
    public static final String THOUGHTS_OPPOSING_COLUMN = "opposing";
    public static final String THOUGHTS_PERSPECTIVE_COLUMN = "perspective";
    public static final String THOUGHTS_OUTCOME_COLUMN = "outcome";
    public static final String THOUGHTS_KEY_COLUMN = "id";
    public static final String THOUGHTS_DATE_COLUMN = "date";
    public static final String THOUGHTS_TIME_COLUMN = "time";
    public static final String THOUGHTS_ERRORS_COLUMN = "errors";
    public static final String THOUGHTS_BRATING_COLUMN = "brating";
    public static final String THOUGHTS_ARATING_COLUMN = "arating";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +
        "(" + THOUGHTS_KEY_COLUMN + " integer primary key, " +
                THOUGHTS_TRIGGER_COLUMN + " text, " + THOUGHTS_BEFORE_COLUMN
                + " text, " + THOUGHTS_UNHELPFUL_COLUMN + " text, "+
                THOUGHTS_SUPPORT_COLUMN + " text, " +
                THOUGHTS_OPPOSING_COLUMN + " text, " +
                THOUGHTS_PERSPECTIVE_COLUMN + " text, " +
                THOUGHTS_OUTCOME_COLUMN + " text, " + THOUGHTS_DATE_COLUMN +
                " text, " + THOUGHTS_TIME_COLUMN + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertRecord(ThoughtRecord record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(THOUGHTS_KEY_COLUMN, record.getKey());
        values.put(THOUGHTS_TRIGGER_COLUMN, record.getTrigger());
        values.put(THOUGHTS_BEFORE_COLUMN, record.getBeforeFeelings());
        values.put(THOUGHTS_UNHELPFUL_COLUMN, record.getUnhelpfulThoughts());
        values.put(THOUGHTS_SUPPORT_COLUMN, record.getSupportingFacts());
        values.put(THOUGHTS_OPPOSING_COLUMN, record.getOpposingFacts());
        values.put(THOUGHTS_PERSPECTIVE_COLUMN, record.getNewPerspective());
        values.put(THOUGHTS_OUTCOME_COLUMN, record.getOutCome());
        values.put(THOUGHTS_DATE_COLUMN, record.getDate());
        values.put(THOUGHTS_TIME_COLUMN, record.getTime());
        db.insert(TABLE_NAME, null, values);
        return true;
    }

    public Integer deleteRecord(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id = ?", new String[] {key} );
    }

    public ArrayList<ThoughtRecord> getAllRecords() {
        ArrayList<ThoughtRecord> records = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        res.moveToFirst();

        while(!res.isAfterLast()) {
            String trigger = res.getString(res.getColumnIndex(THOUGHTS_TRIGGER_COLUMN));
            String beforeFeelings = res.getString(res.getColumnIndex
                    (THOUGHTS_BEFORE_COLUMN));
            String unhelpfulThoughts = res.getString(res.getColumnIndex
                    (THOUGHTS_UNHELPFUL_COLUMN));
            String supportingFacts = res.getString(res.getColumnIndex(THOUGHTS_SUPPORT_COLUMN));
            String opposingFacts = res.getString(res.getColumnIndex(THOUGHTS_OPPOSING_COLUMN));
            String newPerspective = res.getString(res.getColumnIndex(THOUGHTS_PERSPECTIVE_COLUMN));
            String outCome = res.getString(res.getColumnIndex(THOUGHTS_OUTCOME_COLUMN));
            String key = res.getString(res.getColumnIndex(THOUGHTS_KEY_COLUMN));
            String date = res.getString(res.getColumnIndex(THOUGHTS_DATE_COLUMN));
            String time = res.getString(res.getColumnIndex(THOUGHTS_TIME_COLUMN));
            ThoughtRecord record = new ThoughtRecord(trigger,
                    beforeFeelings, unhelpfulThoughts, supportingFacts,
                    opposingFacts, newPerspective, outCome, date, time, key);
            records.add(record);
            res.moveToNext();
        }

        return records;
    }
}
