package com.example.jedi.cryptocurrent3.dummy;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.jedi.cryptocurrent3.data.CryptocurrentContract;
import com.example.jedi.cryptocurrent3.data.CryptocurrentDbHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    public  Cursor mCursor;
    private SQLiteDatabase mDb;

    public DummyContent(Context context) {

        CryptocurrentDbHelper cryptocurrentDbHelper = new CryptocurrentDbHelper(context);
        mDb = cryptocurrentDbHelper.getWritableDatabase();
        cryptocurrentDbHelper.onCreate(mDb);

        mCursor = getAllCards(context);

    }

    public Cursor getAllCards(Context context){
        String query = " SELECT * FROM " + CryptocurrentContract.CryptocurrentEntry.TABLE_NAME + " ORDER BY "
                + CryptocurrentContract.CryptocurrentEntry.COLUMN_COUNTRY;

        Cursor cursor = mDb.rawQuery(query, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        else{
            Toast.makeText(context, "THE CURSOR RETURNED NOTHING", Toast.LENGTH_LONG).show();
        }
        return cursor;
    }


    /**
     * An array of sample (dummy) items.
     */
    // TODO: Convert this List to a cursor
//    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();
//
//    /**
//     * A map of sample (dummy) items, by ID.
//     */
//    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();
//
//    private static final int COUNT = 25;
//
//    static {
//        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addItem(createDummyItem(i));
//        }
//    }
//
//    private static void addItem(DummyItem item) {
//        ITEMS.add(item);
//        ITEM_MAP.put(item.id, item);
//    }
//
//    // TODO: Replace this with
//    private static DummyItem createDummyItem(int position) {
//        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
//    }
//
//    private static String makeDetails(int position) {
//        StringBuilder builder = new StringBuilder();
//        builder.append("Details about Item: ").append(position);
//        for (int i = 0; i < position; i++) {
//            builder.append("\nMore details information here.");
//        }
//        return builder.toString();
//    }
//
//    /**
//     * A dummy item representing a piece of content.
//     */
//    public static class DummyItem {
//        public final String id;
//        public final String content;
//        public final String details;
//
//        public DummyItem(String id, String content, String details) {
//            this.id = id;
//            this.content = content;
//            this.details = details;
//        }
//
//        @Override
//        public String toString() {
//            return content;
//        }
//    }

}
