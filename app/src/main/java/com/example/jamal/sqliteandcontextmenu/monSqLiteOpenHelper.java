package com.example.jamal.sqliteandcontextmenu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jamal on 05/05/2018.
 */

public class monSqLiteOpenHelper extends SQLiteOpenHelper {
    private final String dbName="data.db";
    private final int version=1;
    public monSqLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

@Override public void onCreate(SQLiteDatabase db) {
    String req="create table produit(code integer primary key autoincrement," + "description text,prix real);";
    db.execSQL(req);
}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists produit");
        onCreate(db);
    }
}