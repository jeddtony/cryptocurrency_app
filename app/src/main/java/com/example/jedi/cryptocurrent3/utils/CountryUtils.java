package com.example.jedi.cryptocurrent3.utils;


import com.example.jedi.cryptocurrent3.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;
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
        Map<String, String> localRate = new HashMap<>();
        localRate.putAll(getLocalValue(0.76497));
        localRate.put("country", "England");
        return localRate;
    }

    public static Map getBrazilRate(){
        Map<String, String> localRate = new HashMap<>();
        localRate.putAll(getLocalValue(3.15410));
        localRate.put("country" , "Brazil");
        return localRate;
    }

    public static Map getChinaRate(){
        Map<String, String> localRate = new HashMap<>();
        localRate.putAll(getLocalValue(6.65344));
        localRate.put("country" , "China");
        return localRate;
    }

    public static Map getSouthAfricaRate(){
        Map<String, String> localRate = new HashMap<>();
        localRate.putAll(getLocalValue(13.7099));
        localRate.put("country" , "South Africa");
        return localRate;
    }

    public static Map getCanadaRate(){
        Map<String, String> localRate = new HashMap<>();
        localRate.putAll(getLocalValue(1.25260));
        localRate.put("country" , "Canada");
        return localRate;
    }

    public static Map getFrenchRate(){
        Map<String, String> localRate = new HashMap<>();
        localRate.putAll(getLocalValue(5.58981));
        localRate.put("country" , "France");
        return localRate;

    }

    public static Map getJapanRate(){
        Map<String, String> localRate = new HashMap<>();
        localRate.putAll(getLocalValue(112.62));
        localRate.put("country" , "Japan");
        return localRate;
    }

    public static Map getSaudiRate(){
        Map<String, String> localRate = new HashMap<>();
        localRate.putAll(getLocalValue(3.74610));
        localRate.put("country" , "Saudi Arabia");
        return localRate;
    }

    public static Map getUAERate(){
        Map<String, String> localRate = new HashMap<>();
        localRate.putAll(getLocalValue(3.67205));
        localRate.put("country" , "UAE");
        return localRate;
    }

    public static Map getNigeriaRate(){
        Map<String, String> localRate = new HashMap<>();
        localRate.putAll(getLocalValue(356.650));
        localRate.put("country" , "Nigeria");
        return localRate;
    }

    public static Map getSouthKoreaRate(){
        Map<String, String> localRate = new HashMap<>();
        localRate.putAll(getLocalValue(1144.17));
        localRate.put("country" , "South Korea");
        return localRate;
    }

    public static Map getGermanyRate(){
        Map<String, String> localRate = new HashMap<>();
        localRate.putAll(getLocalValue(1.66668));
        localRate.put("country" , "Germany");
        return localRate;

    }

    public static Map getDenmarkRate(){
        Map<String, String> localRate = new HashMap<>();
        localRate.putAll(getLocalValue(6.34258));
        localRate.put("country" , "Denmark");
        return localRate;
    }

    public static Map getAmericanRate(){
        Map<String, String> localRate = new HashMap<>();
        localRate.putAll(getLocalValue(1.0));
        localRate.put("country" , "USA" );
        return localRate;
    }

    public static Map getItalyRate(){
        Map<String, String> localRate = new HashMap<>();
        localRate.putAll(getLocalValue(1650.01));
        localRate.put("country" , "Italy");
        return localRate;

    }

    public static Map getKuwaitRate(){
        Map<String, String> localRate = new HashMap<>();
        localRate.putAll(getLocalValue(0.30171));
        localRate.put("country" , "Kuwait");
        return localRate;
    }

    public static Map getRussiaRate(){
        Map<String, String> localRate = new HashMap<>();
        localRate.putAll(getLocalValue(58.1241));
        localRate.put("country" , "Russia");
        return localRate;
    }

    public static Map getIndiaRate(){
        Map<String, String> localRate = new HashMap<>();
        localRate.putAll(getLocalValue(65.4015));
        localRate.put("country" , "India");
        return localRate;
    }

    public static Map getSingaporeRate(){
        Map<String, String> localRate = new HashMap<>();
        localRate.putAll(getLocalValue(1.36408));
        localRate.put("country" , "Singapore");
        return localRate;
    }

    public static Map getTaiwanRate(){
        Map<String, String> localRate = new HashMap<>();
        localRate.putAll(getLocalValue(30.3383));
        localRate.put("country" , "Taiwan");
        return localRate;
    }


    public static Map getLocalValue( Double conversionRate){
        Double btc = Double.parseDouble(BTC);
        Double eth = Double.parseDouble(ETH);

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        Double btcValue = (btc * conversionRate);
        Double ethValue = eth * conversionRate;

        String localBtcValue = df.format(btcValue);
        String localEthValue = df.format(ethValue);


//        String[] localArray = {localBtcValue.toString(), localEthValue.toString()};
        Map<String, String > localArray = new HashMap<>();
        localArray.put("btcValue" , localBtcValue );
        localArray.put("ethValue", localEthValue);
        return localArray;
    }

    public static Map[] getAllLocalCurrencies(){
//        Map<String, Double> countriesCurrencies = new HashMap<>();
        Map[] currencies = {getAmericanRate(),getBrazilRate(), getChinaRate(), getCanadaRate(), getDenmarkRate(),
                getEnglandRate(), getFrenchRate(), getGermanyRate(), getSouthAfricaRate(), getSouthKoreaRate(),
                getIndiaRate(), getItalyRate(), getJapanRate(), getKuwaitRate(), getRussiaRate(), getSaudiRate(),
                getNigeriaRate(), getTaiwanRate(), getUAERate(), getSingaporeRate()};
        return currencies ;
    }

    public static int getCountryFlag(String countryName){
        int countryFlag =0 ;
        switch (countryName){
            case "USA":
                countryFlag =   R.drawable.usa_flag;
                break;
            case "Brazil":
                countryFlag = R.drawable.brazil_flag;
                break;
            case "China":
                countryFlag = R.drawable.china_flag;
                break;
            case "Canada":
                countryFlag = R.drawable.canada_flag;
                break;
            case "Denmark":
                countryFlag = R.drawable.denmark_flag;
                break;
            case "England":
                countryFlag = R.drawable.england_flag;
                break;
            case "France":
                countryFlag = R.drawable.france_flag;
                break;
            case "Germany":
                countryFlag = R.drawable.germany_flag;
                break;
            case "India":
                countryFlag = R.drawable.indian_flag;
                break;
            case "Italy":
                countryFlag = R.drawable.italy_flag;
                break;
            case "Japan":
                countryFlag = R.drawable.japan_flag;
                break;
            case "Kuwait":
                countryFlag = R.drawable.kuwait_flag;
                break;
            case "Russia":
                countryFlag = R.drawable.russian_flag;
                break;
            case "Saudi Arabia":
                countryFlag = R.drawable.saudi_flag;
                break;
            case "Nigeria":
                countryFlag = R.drawable.naija_flag;
                break;
            case "Taiwan":
                countryFlag = R.drawable.taiwan_flag;
                break;
            case "UAE":
                countryFlag = R.drawable.uae_flag;
                break;
            case "Singapore":
                countryFlag = R.drawable.singapore;
                break;
            case "South Africa":
                countryFlag = R.drawable.south_african_flag;
                break;
            case "South Korea":
                countryFlag = R.drawable.south_korea;
                break;
            default:
//                countryFlag = 0;
        }
        return countryFlag;
    }

}
