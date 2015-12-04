package ejemplosqlite.smorcuende.com.bdsqlite;

import java.util.ArrayList;
import java.util.List;

import sqlite.ddbb.NoteDbSQLiteHelper;
import sqlite.ddbb.NoteDbSQLiteHelper.NoteTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NoteDataSource {
    private SQLiteDatabase db;
    private NoteDbSQLiteHelper dbHelper;
    private String[] columns = { NoteTable.ID_COLUMN, NoteTable.TXT_COLUMN };

    public NoteDataSource(Context context) {
        dbHelper = new NoteDbSQLiteHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addNote(String note) {
        ContentValues values = new ContentValues();
        values.put(NoteTable.TXT_COLUMN, note);
        db.insert(NoteTable.NOTE_TABLE, null, values);
    }

    public List<Note> getListNotes() {
        List<Note> listNotes = new ArrayList<Note>();

        Cursor cursor = db.query(NoteTable.NOTE_TABLE, columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Note newNote = noteCursor(cursor);
            listNotes.add(newNote);
            cursor.moveToNext();
        }

        cursor.close();
        return listNotes;
    }

    public void deleteNote(Note note) {
        long id = note.getId();
        db.delete(NoteTable.NOTE_TABLE, NoteTable.ID_COLUMN + " = " + id, null);
    }

    private Note noteCursor(Cursor cursor) {
        Note note = new Note();
        note.setId(cursor.getLong(0));
        note.setText(cursor.getString(1));
        return note;
    }
}