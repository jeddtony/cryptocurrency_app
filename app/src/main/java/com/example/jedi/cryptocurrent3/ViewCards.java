package com.example.jedi.cryptocurrent3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.jedi.cryptocurrent3.data.CryptocurrentContract;
import com.example.jedi.cryptocurrent3.data.CryptocurrentDbHelper;

public class ViewCards extends AppCompatActivity implements CardAdapter.CardAdapterOnClickListener, LoaderManager.LoaderCallbacks<Cursor>{
    private SQLiteDatabase mDb;
    private Cursor mCursor;
    private RecyclerView mRecyclerView;
    private CardAdapter mCardAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("ViewCards ", "E dey display view cards");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cards);

        mRecyclerView = (RecyclerView) findViewById(R.id.show_card_recycler_view);
        mCardAdapter = new CardAdapter(this, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);


        CryptocurrentDbHelper cryptocurrentDbHelper = new CryptocurrentDbHelper(this);
        mDb = cryptocurrentDbHelper.getReadableDatabase();
        cryptocurrentDbHelper.onCreate(mDb);

        mCursor = geAllCards();

        if(mCursor.getCount() > 0){
            mCardAdapter.swapCursor(mCursor);

            mRecyclerView.setAdapter(mCardAdapter);
        }
        else{
            // TODO: No data to display
        }
    }

    @Override
    public void onClick(String countryName) {
        Log.i("onClicked" , "" + countryName);
        ConvertCurrencyFragment convertCurrencyFragment = new ConvertCurrencyFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().
                add(R.id.convert_currency_fragment, convertCurrencyFragment).
                commit();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private Cursor geAllCards(){
        String query = " SELECT * FROM " + CryptocurrentContract.CryptocurrentEntry.TABLE_NAME + " ORDER BY "
                + CryptocurrentContract.CryptocurrentEntry.COLUMN_COUNTRY;

        Cursor cursor = mDb.rawQuery(query, null );
        if ( cursor != null){
            cursor.moveToFirst();
        }
            return cursor;
    }
}
