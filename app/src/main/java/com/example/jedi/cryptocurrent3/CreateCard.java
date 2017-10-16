package com.example.jedi.cryptocurrent3;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import com.example.jedi.cryptocurrent3.data.CryptocurrentContract;
import com.example.jedi.cryptocurrent3.data.CryptocurrentDbHelper;

public class CreateCard extends AppCompatActivity implements OnItemSelectedListener {
    private Spinner mSpinnerCountries;
    private String[] countries = { "USA" , "Brazil","Canada", "China","Denmark",
                                "England","France", "Germany","India", "Italy", "Japan",
                            "Kuwait", "Nigeria", "Russia", "Saudi Arabia", "Singapore","South Africa",
                            "South Korea", "Taiwan", "UAE"};
    public static String selectedCountry = null;
    public static boolean btcSelected = false;
    public static boolean ethSelected = false;

    private SQLiteDatabase mdb ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);
        mSpinnerCountries = (Spinner) findViewById(R.id.spinner_countries);
        ArrayAdapter <String> countryAdapter = new ArrayAdapter<>(CreateCard.this,
                                                android.R.layout.simple_spinner_item , countries);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mSpinnerCountries.setAdapter(countryAdapter);
        mSpinnerCountries.setOnItemSelectedListener(this);
        CryptocurrentDbHelper cryptocurrentDbHelper = new CryptocurrentDbHelper(this);
        mdb = cryptocurrentDbHelper.getWritableDatabase();
        cryptocurrentDbHelper.onCreate(mdb);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position){
            case 0:
                selectedCountry = "USA";
                break;
            case 1:
                selectedCountry = "Brazil";
                break;
            case 2:
                selectedCountry = "Canada";
                break;
            case 3:
                selectedCountry = "China";
                break;
            case 4:
                selectedCountry = "Denmark";
                break;
            case 5:
                selectedCountry = "England";
                break;
            case 6:
                selectedCountry = "France";
                break;
            case 7:
                selectedCountry = "Germany";
                break;
            case 8:
                selectedCountry = "India";
                break;
            case 9:
                selectedCountry = "Italy";
                break;
            case 10:
                selectedCountry = "Japan";
                break;
            case 11:
                selectedCountry = "Kuwait";
                break;
            case 12:
                selectedCountry = "Nigeria";
                break;
            case 13:
                selectedCountry = "Russia";
                break;
            case 14:
                selectedCountry = "Saudi Arabia";
                break;
            case 15:
                selectedCountry = "Singapore";
                break;
            case 16:
                selectedCountry = "South Africa";
                break;
            case 17:
                selectedCountry = "South Korea";
                break;
            case 18:
                selectedCountry = "Taiwan";
                break;
            case 19:
                selectedCountry = "UAE";
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void setBtcSelected(View view){
        btcSelected = ((CheckBox) view).isChecked();
    }

    public void setEthSelected(View view){
        ethSelected = ((CheckBox) view).isChecked();
    }

    public void saveCard(View view){
        if(selectedCountry != null ){
            if(btcSelected != false || ethSelected != false){
                long insertSuccess =     insertCard();
                if(insertSuccess != -1){
                    Toast.makeText(this, "Card created successfully", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(this, "Error creating card ", Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(this, "Select either btc or eth ", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this, "Please select a country ", Toast.LENGTH_LONG).show();
        }
    }


    public long insertCard(){
        ContentValues cv = new ContentValues();
        cv.put(CryptocurrentContract.CryptocurrentEntry.COLUMN_COUNTRY, selectedCountry);
        cv.put(CryptocurrentContract.CryptocurrentEntry.COLUMN_BTC, btcSelected);
        cv.put(CryptocurrentContract.CryptocurrentEntry.COLUMN_ETH, ethSelected);

        return mdb.insert(CryptocurrentContract.CryptocurrentEntry.TABLE_NAME, null, cv);
    }
}
