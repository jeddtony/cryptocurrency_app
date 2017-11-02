package com.example.jedi.cryptocurrent3;

import android.content.AsyncTaskLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
import java.util.jar.Manifest;

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
//        getLoaderManager().initLoader(0, null, this);
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getBaseContext(), "In the context compat ", Toast.LENGTH_LONG).show();
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.INTERNET)){
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Need Storage Permission");
                builder.setMessage("This app needs storage permission.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.INTERNET}, 3);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
            else {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.INTERNET}, 0);
            }
        }
        else {
            proceedAfterPermission();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3) {
            if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                //Got Permission
                proceedAfterPermission();
            }
        }
    }

    private void proceedAfterPermission(){
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

        if (id == R.id.nav_view_cards) {
            // Handle the camera action
            Class cardListActivity = CardListActivity.class;
            Intent startCardList = new Intent(getBaseContext(),cardListActivity);
            startActivity(startCardList);

        } else if (id == R.id.nav_create_cards) {
            Class startCreateCard = CreateCard.class;
            Intent intent = new Intent(getBaseContext(), startCreateCard);
            // We didnt call put extra because we are not passing any string to the next
            startActivity(intent);

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

                if (mCryptoData != null){
                    deliverResult(mCryptoData);
                }
                else{
                    //TODO: display the loading bar
                    forceLoad();
                }

            }

            @Override
            public Map[] loadInBackground() {

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
               super.deliverResult(data);
            }


        };
    }

    @Override
    public void onLoadFinished(Loader<Map[]> loader, Map[] maps) {
        allMaps = maps;
        if(maps != null){
            showLoadedData();
            mCurrencyAdapter.swapMap(maps);
            }

        else {
            showErrorMessage();
        }

    }

    @Override
    public void onLoaderReset(Loader<Map[]> loader) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 3:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getBaseContext(), "We have the permission", Toast.LENGTH_LONG).show();
                    getLoaderManager().initLoader(0, null, this);

                }else {
                  Log.i("Permission Denied", "The permission was denied");
            }
//            return;
        }

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
    }
}


