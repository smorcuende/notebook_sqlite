package sqlite.ddbb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteDbSQLiteHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "MyNoteBook";
    private static final int DATABASE_VERSION = 1;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("delete table if exists " + NoteTable.NOTE_TABLE);
        onCreate(db);
    }

    public NoteDbSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static class NoteTable{
        public static String NOTE_TABLE = "notes";
        public static String ID_COLUMN = "id";
        public static String TXT_COLUMN = "text";
    }
    private static final String DATABASE_CREATE = "create table "
            + NoteTable.NOTE_TABLE + "(" + NoteTable.ID_COLUMN
            + " integer primary key autoincrement, " + NoteTable.TXT_COLUMN
            + " text not null);";

}
