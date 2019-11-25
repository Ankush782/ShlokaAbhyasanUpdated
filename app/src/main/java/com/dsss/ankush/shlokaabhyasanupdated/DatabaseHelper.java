package com.dsss.ankush.shlokaabhyasanupdated;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "Hymns";

    // Table columns
    public static final String _ID = "name";
    public static final String ENGLISH = "english";
    public static final String HINDI = "hindi";
    public static final String TAMIL = "tamil";
    public static final String TELUGU = "telugu";
    public static final String MEANING = "meaning";


    // Database Information
    static final String DB_NAME = "JOURNALDEV_COUNTRIES.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " TEXT PRIMARY KEY , " + ENGLISH + " TEXT NOT NULL, " + HINDI + " TEXT NOT NULL, " + TAMIL + " TEXT NOT NULL, " + TELUGU + " TEXT NOT NULL, " + MEANING + " TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

