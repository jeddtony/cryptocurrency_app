package com.example.jedi.cryptocurrent3.utils;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jedi on 10/6/2017.
 */

public class JsonUtils {

        private static String BTC = "BTC";
        private static String USD = "USD";
        private static String EUR = "EUR";

        private static String ETH = "ETH";

        public static String[] getCurrentRatesFromJson(Context context, String currencyJsonStr)
            throws JSONException{

            String[] parsedCurrentRate = new String[2];

            JSONObject cryptoResults = new JSONObject(currencyJsonStr);



            JSONObject btcRates = cryptoResults.getJSONObject(BTC);
            String usdBtcRate = btcRates.getString(USD);
            String eurBtcRate = btcRates.getString(EUR);

            JSONObject ethRates = cryptoResults.getJSONObject(ETH);
            String usdEthRate = ethRates.getString(USD);
            String eurEthRate = ethRates.getString(EUR);

            parsedCurrentRate[0] = usdBtcRate + " - " + eurBtcRate;
            parsedCurrentRate[1] = usdEthRate + " - " + eurEthRate;
            return parsedCurrentRate;
        }
}
