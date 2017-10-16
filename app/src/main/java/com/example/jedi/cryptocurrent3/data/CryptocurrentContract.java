package com.example.jedi.cryptocurrent3.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by jedi on 10/12/2017.
 */

public class CryptocurrentContract  {
    public static final String AUTHORITY = "com.example.jedi.cryptocurrent3";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_TASKS = "Cryptocurrency";

    private CryptocurrentContract(){}

    public static class CryptocurrentEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASKS).build();
        public static final String TABLE_NAME = "Cryptocurrencies";
        public static final String COLUMN_COUNTRY = "country";
        public static final String COLUMN_BTC = "Btc";
        public static final String COLUMN_ETH = "Eth";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }

}
