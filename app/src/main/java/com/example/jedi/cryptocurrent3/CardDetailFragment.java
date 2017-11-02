package com.example.jedi.cryptocurrent3;

import android.app.Activity;
import android.media.Image;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jedi.cryptocurrent3.dummy.DummyContent;
import com.example.jedi.cryptocurrent3.utils.CalcCurrencyUtils;
import com.example.jedi.cryptocurrent3.utils.CountryUtils;

/**
 * A fragment representing a single Card detail screen.
 * This fragment is either contained in a {@link CardListActivity}
 * in two-pane mode (on tablets) or a {@link CardDetailActivity}
 * on handsets.
 */
public class CardDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_COUNTRY_NAME = "COUNTRY_NAME";
    public static final String ARG_BTC_VALUE = "BTC_VALUE";
    public static final String ARG_ETH_VALUE = "ETH_VALUE";

    private String countryName;
    private String btcValue;
    private String ethValue;
    private EditText mInputValue;

    private TextView mBtcValue;
    private TextView mEthValue;
    /**
     * The dummy content this fragment is presenting.
     */
//    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CardDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_COUNTRY_NAME)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

            countryName = getArguments().getString(ARG_COUNTRY_NAME);
            btcValue = getArguments().getString(ARG_BTC_VALUE);
            ethValue = getArguments().getString(ARG_ETH_VALUE);

            Activity activity = this.getActivity();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.card_detail, container, false);

        ((TextView) rootView.findViewById(R.id.country_name)).setText(countryName);
        mBtcValue =  (TextView) rootView.findViewById(R.id.convert_btc_value);
        mEthValue = (TextView) rootView . findViewById(R.id.convert_eth_value);
        mInputValue = (EditText) rootView.findViewById(R.id.inputValue);
        ImageView countryFlag = ((ImageView) rootView.findViewById(R.id.detail_country_flag));
        countryFlag.setImageResource(CountryUtils.getCountryFlag(countryName));
        Button calculateButton = (Button) rootView.findViewById(R.id.calc_button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputValue = mInputValue.getText().toString();
                if(inputValue == null){
                    return;
                }
                else {
                    double btcResult = CalcCurrencyUtils.calcBtc(inputValue, btcValue);
                    double ethResult = CalcCurrencyUtils.calcEth(inputValue, ethValue);
                    Log.i("Jed here", "" + ethResult);
                    Log.i("Jed here", "" + btcResult);
                    mBtcValue.setText("" + btcResult);
                    mEthValue.setText("" + ethResult);
                }
            }
        });
        return rootView;
    }
}