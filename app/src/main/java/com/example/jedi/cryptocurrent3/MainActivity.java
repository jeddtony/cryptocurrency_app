package com.example.jedi.cryptocurrent3;

import android.content.AsyncTaskLoader;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jedi.cryptocurrent3.utils.JsonUtils;
import com.example.jedi.cryptocurrent3.utils.NetworkUtils;

import java.net.URL;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        android.app.LoaderManager.LoaderCallbacks<Map[]> {

       private ProgressBar mProgressBar;
       private TextView mErrorMessage;
      private   RecyclerView mRecyclerView;
        private CurrencyAdapter mCurrencyAdapter;
    public static Map[] allMaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Class startCreateCard = CreateCard.class;
                Intent intent = new Intent(getBaseContext(), startCreateCard);
                // We didnt call put extra because we are not passing any string to the next
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mProgressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        mErrorMessage = (TextView) findViewById(R.id.error_loading);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview_main) ;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mCurrencyAdapter = new CurrencyAdapter(this);
        mRecyclerView.setAdapter(mCurrencyAdapter);
        showLoading();
        getLoaderManager().initLoader(0, null, this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Toast.makeText(getBaseContext()," You tapped on view cards", Toast.LENGTH_LONG).show();
//            Class viewCards = ViewCards.class;
//            Intent startViewCards =  new Intent(getBaseContext(), viewCards);
//            startActivity(startViewCards);
            Class cardListActivity = CardListActivity.class;
            Intent startCardList = new Intent(getBaseContext(),cardListActivity);
            startActivity(startCardList);

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public Loader<Map[]> onCreateLoader(int i, Bundle bundle) {
        return new AsyncTaskLoader<Map[]>(this) {

            Map[] mCryptoData = null;

            @Override
            protected void onStartLoading() {
                Log.i("Den_Den", "Now on the onStartLoading");
                if (mCryptoData != null){
                    deliverResult(mCryptoData);
                }
                else{
//                    mProgressBar.setVisibility(View.VISIBLE);
                    Log.i("Den_Den", "Now on the onStartLoading else");
                    //TODO: display the loading bar
                    forceLoad();
                }

            }

            @Override
            public Map[] loadInBackground() {
                Log.i("Den_Den", "Now on the loadInBackground");
                URL multiplePrice = NetworkUtils.getMultiplePriceUrl(getContext());
                try {
                    String results = NetworkUtils.getResponseFromApi(multiplePrice);
                    Map[] jsonData = JsonUtils.getCurrentRatesFromJson(MainActivity.this, results);
                    Log.i("loadInBackgroud", ""+jsonData.length);
                    return jsonData;
                }
                catch (Exception exception){
                    exception.printStackTrace();
                    return null;
                }
            }

            public void deliverResult(Map[] data){
                mCryptoData = data;
//                Log.i("DeliverResult", ""+ data.length);
                super.deliverResult(data);
            }


        };
    }

    @Override
    public void onLoadFinished(Loader<Map[]> loader, Map[] maps) {
        allMaps = maps;
//        mCurrencyAdapter.swapMap(maps);
        if(maps != null){
            showLoadedData();
            mCurrencyAdapter.swapMap(maps);
//            for(Map string: maps){
//                Log.i("Ghen_Ghen ", " "+string.get("country") );

//                results = string;
//                mCurrencyAdapter.swapMap(string);
            }
//            mCurrencyAdapter.swapMap(maps);


        else {
            Log.i("Ogbeni ", "You get empty data");
            showErrorMessage();
        }

//        mTextView.setText(results);
    }

    @Override
    public void onLoaderReset(Loader<Map[]> loader) {

    }

    private void showLoading (){
        mRecyclerView.setVisibility(View.INVISIBLE);
        mProgressBar .setVisibility(View.VISIBLE);
        mErrorMessage.setVisibility(View.INVISIBLE);
    }

    private void showLoadedData(){
        mRecyclerView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    private void showErrorMessage(){
        mProgressBar.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }

    public void refreshData(View view){
//       Toast.makeText(getBaseContext(), "Refreshing Data", Toast.LENGTH_LONG).show();
////        showLoading();
//        Log.i("REFRESHING DATA", "IT IS REFRESHING DATA");
//       this.getLoaderManager().initLoader(0, null, this);

    }
}


