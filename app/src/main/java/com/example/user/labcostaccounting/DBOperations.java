package com.example.user.labcostaccounting;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DBOperations extends SQLiteOpenHelper {
    private static DBOperations _Instance = null;

    private MainActivity _Activity;
    private SQLiteDatabase _Database;
    private int size = 0;

    public static DBOperations getDB() {
        return _Instance;
    }

    public static void Initialize(Context context) {
        _Instance = new DBOperations(context);
    }

    private DBOperations(Context context) {
        super(context, "elementsDB", null, 2);
        _Activity = (MainActivity) context;
        _Database = getWritableDatabase();
    }

    public void addElement(String name, boolean isActive, int value) {
        _Database.execSQL("INSERT INTO elements VALUES (" +
                "\"" + name + "\"," + (isActive ? "1" : "0") + "," + value +  ");");
        size++;
    }

    public int getCount() {return size;}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        _Database = sqLiteDatabase;
        _Database.execSQL("CREATE TABLE elements (ELNAME TEXT, ELTYPE INT, ELVALUE INT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        _Activity.UpdateElements();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        _Database = db;
    }

    public ArrayList<DBRecord> getElements() {
        ArrayList<DBRecord> result = new ArrayList<>();
        Cursor cursor = _Database.rawQuery(
                "SELECT * FROM elements WHERE 1", new String[]{});
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            result.add(new DBRecord(cursor.getString(0), cursor.getInt(1) != 0, cursor.getInt(2)));
            cursor.moveToNext();
        }
        return result;
    }

    public void deleteElement(int activeElement) {
        if(size > 0) {
            DBRecord deleteEl = DBOperations.getDB().getElements().get(activeElement);
            _Database.execSQL("DELETE FROM elements WHERE ELNAME=\"" + deleteEl.getInformation() + "\";");
            size--;
            _Activity.UpdateElements();
        }
    }

}