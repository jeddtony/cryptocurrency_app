package com.example.jedi.cryptocurrent3.utils;

import com.example.jedi.cryptocurrent3.MainActivity;

import java.util.Map;

/**
 * Created by jedi on 10/30/2017.
 */

public class CalcCurrencyUtils {

    public static double calcBtc(String inputBtc , String currentBtc){
        double convertInputBtc = Double.parseDouble(inputBtc);
        double convertCurrentBtc = Double.parseDouble(currentBtc);
        return convertInputBtc / convertCurrentBtc;
    }

    public static double calcEth(String inputEth , String currentEth){
        double convertInputEth = Double.parseDouble(inputEth);
        double convertCurrentEth = Double.parseDouble(currentEth);
        return convertInputEth / convertCurrentEth;
    }
}
