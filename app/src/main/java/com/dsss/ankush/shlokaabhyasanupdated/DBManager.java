package com.dsss.ankush.shlokaabhyasanupdated;





import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String english,String hindi,String tamil,String telugu,String meaning) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper._ID, name);
        contentValue.put(DatabaseHelper.ENGLISH, english);
        contentValue.put(DatabaseHelper.HINDI, hindi);
        contentValue.put(DatabaseHelper.TAMIL, tamil);
        contentValue.put(DatabaseHelper.TELUGU, telugu);
        contentValue.put(DatabaseHelper.MEANING, meaning);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.ENGLISH, DatabaseHelper.HINDI,DatabaseHelper.TAMIL,DatabaseHelper.TELUGU,DatabaseHelper.MEANING };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            //cursor.moveToFirst();
        }
        return cursor;
    }



    public void delete(String _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

}
