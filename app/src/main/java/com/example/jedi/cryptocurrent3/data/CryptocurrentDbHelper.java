package com.example.jedi.cryptocurrent3.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jedi on 10/12/2017.
 */

public class CryptocurrentDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cryptocurrent.db";

    private static final int DATABASE_VERSION = 1;

    public CryptocurrentDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_CRYPTOCURRENT_TABLE = "CREATE TABLE IF NOT EXISTS " + CryptocurrentContract.CryptocurrentEntry.TABLE_NAME
                + " ( " + CryptocurrentContract.CryptocurrentEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CryptocurrentContract.CryptocurrentEntry.COLUMN_COUNTRY + " TEXT NOT NULL, " +
                CryptocurrentContract.CryptocurrentEntry.COLUMN_BTC + " BOOLEAN, " +
                CryptocurrentContract.CryptocurrentEntry.COLUMN_ETH + " BOOLEAN, " +
                CryptocurrentContract.CryptocurrentEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP " + " ); ";
                sqLiteDatabase.execSQL(SQL_CREATE_CRYPTOCURRENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
