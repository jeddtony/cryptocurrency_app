package com.example.jedi.cryptocurrent3;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jedi.cryptocurrent3.dummy.DummyContent;

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
//            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_COUNTRY_NAME));

            countryName = getArguments().getString(ARG_COUNTRY_NAME);
            btcValue = getArguments().getString(ARG_BTC_VALUE);
            ethValue = getArguments().getString(ARG_ETH_VALUE);

            Activity activity = this.getActivity();
//            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
//            if (appBarLayout != null) {
//                appBarLayout.setTitle(mItem.content);
            }
        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.card_detail, container, false);

        ((TextView) rootView.findViewById(R.id.country_name)).setText(countryName);
        ((TextView) rootView.findViewById(R.id.convert_btc_value)).setText(btcValue);
        ((TextView) rootView . findViewById(R.id.convert_eth_value)).setText(ethValue);

        // Show the dummy content as text in a TextView.
//        if (mItem != null) {
//            ((TextView) rootView.findViewById(R.id.card_detail)).setText(mItem.details);
//        }
        return rootView;
    }
}