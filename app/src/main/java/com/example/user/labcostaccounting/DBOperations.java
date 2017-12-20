package com.example.user.labcostaccounting;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBOperations {
    private static DBOperations singleton;
    private SQLiteDatabase db;

    static {
        singleton = new DBOperations();
    }

    public static DBOperations getInstance() {
        return singleton;
    }

    public void init(SQLiteDatabase db) {
        this.db = db;
        //this.db.execSQL("DROP TABLE IF EXISTS costs;");
        this.db.execSQL("CREATE TABLE IF NOT EXISTS costs(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name CHAR(100) NOT NULL," +
                "is_asset INTEGER NOT NULL," +
                "amount REAL NOT NULL," +
                "version INTEGER);");
    }

    public void createCost(DBRecord cost) {
        createCost(cost.getName(), cost.isAsset(), cost.getAmount());
    }

    public void createCost(String name, boolean isAsset, double amount) {
        db.execSQL("INSERT INTO costs ('name', 'is_asset', 'amount', 'version') VALUES ('" +
                name + "', " +
                (isAsset ? 1 : 0) + ", " +
                String.valueOf(amount) + ", null);");
    }

    public void removeCost(int id) {
        db.execSQL("DELETE FROM costs WHERE id = " + String.valueOf(id) + ";");
    }

    public List<DBRecord> getData() {
        List<DBRecord> result = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM costs;", null);
        if (cursor.moveToFirst()) {
            do {
                DBRecord cost = new DBRecord(cursor.getInt(0), cursor.getString(1),
                        cursor.getInt(2) == 1, cursor.getDouble(3), cursor.getInt(4));
                result.add(cost);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return result;
    }
}