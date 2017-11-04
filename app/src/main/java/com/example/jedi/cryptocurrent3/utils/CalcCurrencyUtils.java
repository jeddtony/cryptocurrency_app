package com.example.jedi.cryptocurrent3.utils;

import android.util.Log;

import com.example.jedi.cryptocurrent3.MainActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;

/**
 * Created by jedi on 10/30/2017.
 */

public class CalcCurrencyUtils {

    public static String calcBtc(String inputBtc , String currentBtc){
        double convertInputBtc = Float.parseFloat(inputBtc);
        double convertCurrentBtc = Float.parseFloat(currentBtc);
        Log.i("INPUT BTC", ""+convertInputBtc);
        double result = convertInputBtc/ convertCurrentBtc;
        Log.i("BTC RESULT", "" + result);
        NumberFormat formatter = new DecimalFormat("0.00000");
        return formatter.format(result);
    }

    public static String calcEth(String inputEth , String currentEth){
        double convertInputEth = Float.parseFloat(inputEth);
        double convertCurrentEth = Float.parseFloat(currentEth);
        Log.i("INPUT ETH", "" + convertInputEth);
        double result = convertInputEth/convertCurrentEth;
        Log.i("ETH RESULT", "" + result);
        NumberFormat formatter = new DecimalFormat("0.00000");
        return  formatter.format(result);
    }
}
