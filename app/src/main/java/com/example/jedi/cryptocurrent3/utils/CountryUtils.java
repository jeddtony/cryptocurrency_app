package com.example.jedi.cryptocurrent3.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jedi on 10/9/2017.
 */

public class CountryUtils {

    public static String BTC = null ;
    public static String  ETH = null;

    public static Map getEnglandRate(){
            return getLocalValue(0.76497);
    }

    public static Map getBrazilRate(){
        return getLocalValue(3.15410);
    }

    public static Map getChinaRate(){
        return getLocalValue(6.65344);
    }

    public static Map getSouthAfricaRate(){
        return getLocalValue(13.7099);
    }

    public static Map getCanadaRate(){
        return getLocalValue(1.25260);
    }

    public static Map getFrenchRate(){
        return getLocalValue(5.58981);
    }

    public static Map getJapanRate(){
        return getLocalValue(112.62);
    }

    public static Map getSaudiRate(){
        return getLocalValue(3.74610);
    }

    public static Map getUAERate(){
        return getLocalValue(3.67205);
    }

    public static Map getNigeriaRate(){
        return getLocalValue(356.650);
    }

    public static Map getSouthKoreaRate(){
        return getLocalValue(1144.17);
    }

    public static Map getGermanyRate(){
        return getLocalValue(1.66668);
    }

    public static Map getDenmarkRate(){
        return getLocalValue(6.34258);
    }

    public static Map getItalyRate(){
        return getLocalValue(1650.01);
    }

    public static Map getKuwaitRate(){
        return getLocalValue(0.30171);
    }

    public static Map getRussiaRate(){
        return getLocalValue(58.1241);
    }

    public static Map getIndiaRate(){
        return getLocalValue(65.4015);
    }

    public static Map getSingaporeRate(){
        return getLocalValue(1.36408);
    }

    public static Map getTaiwanRate(){
        return getLocalValue(30.3383);
    }


    public static Map getLocalValue( Double conversionRate){
        Double btc = Double.parseDouble(BTC);
        Double eth = Double.parseDouble(ETH);

        Double localBtcValue = btc * conversionRate;
        Double localEthValue = eth * conversionRate;

//        String[] localArray = {localBtcValue.toString(), localEthValue.toString()};
        Map<String, Double> localArray = new HashMap<>();
        localArray.put("btcValue" , localBtcValue );
        localArray.put("ethValue", localEthValue);
        return localArray;
    }

    public static Map[] getAllLocalCurrencies(){
//        Map<String, Double> countriesCurrencies = new HashMap<>();
        Map[] currencies = {getBrazilRate(), getChinaRate(), getCanadaRate(), getDenmarkRate(),
                getEnglandRate(), getFrenchRate(), getGermanyRate(), getSouthAfricaRate(), getSouthKoreaRate(),
                getIndiaRate(), getItalyRate(), getJapanRate(), getKuwaitRate(), getRussiaRate(), getSaudiRate(),
                getNigeriaRate(), getTaiwanRate(), getUAERate()};
        return currencies ;
    }
}
