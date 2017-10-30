package com.example.jedi.cryptocurrent3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.jedi.cryptocurrent3.data.CryptocurrentContract;
import com.example.jedi.cryptocurrent3.dummy.DummyContent;
import com.example.jedi.cryptocurrent3.utils.CountryUtils;

import java.util.List;
import java.util.Map;

/**
 * An activity representing a list of Cards. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link CardDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class CardListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle(getTitle());

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        View recyclerView = findViewById(R.id.card_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.card_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        // TODO: Replace the DummyContent.ITEMS with what is gotten from the loaderManager Callback
        DummyContent dc = new DummyContent(getBaseContext());
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(dc.mCursor));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final Cursor mValues;

        // TODO: This is where you it receives the cursor
        public SimpleItemRecyclerViewAdapter(Cursor items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            // TODO: Replace this with variable that will be used with the views using the cursor
            // TODO: Dont delete the dummyContent instead modify it to fetch from the database
            Map[] allMaps = MainActivity.allMaps;
            mValues.moveToPosition(position);
//            holder.mItem = mValues
//            holder.mIdView.setText(mValues.get(position).id);
//            holder.mContentView.setText(mValues.get(position).content);
            int countryColumnIndex = mValues.getColumnIndex(CryptocurrentContract.CryptocurrentEntry.COLUMN_COUNTRY);
            String countryName = mValues.getString(countryColumnIndex);
            Log.i("Country name", ""+ countryName);
            for (Map map: allMaps) {
                if(map.get("country").equals(countryName) ){
                    Log.i("looping", "" + countryName + " " + map.get("country"));
                    String country = map.get("country").toString();
                    String btcValue = map.get("btcValue").toString();
                    String ethValue = map.get("ethValue").toString();
                    Log.i("BtcValue", ""+btcValue);
                    Log.i("EthValue", ""+ethValue);
                    holder.mCountryName.setText(country);
                    holder.mBtcValue.setText(btcValue);
                    holder.mEthValue.setText(ethValue);
                    holder.mCountryFlag.setImageResource(CountryUtils.getCountryFlag(countryName));
                }
//            else {
//                Log.i("Na lie", "Map country does not match any country name");
//                Log.i("looping", "" + countryName + " " + map.get("country"));
//            }
            }

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        // TODO: Replace ARG_ITEM_ID with the column of the cursor
                        arguments.putString(CardDetailFragment.ARG_COUNTRY_NAME, holder.mCountryName.getText().toString());
                        arguments.putString(CardDetailFragment.ARG_BTC_VALUE, holder.mBtcValue.getText().toString());
                        arguments.putString(CardDetailFragment.ARG_ETH_VALUE, holder.mEthValue.getText().toString());
                        CardDetailFragment fragment = new CardDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.card_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, CardDetailActivity.class);
                        // TODO: Replace ARG_ITEM_ID with the column of the cursor
                        intent.putExtra(CardDetailFragment.ARG_COUNTRY_NAME, holder.mCountryName.getText().toString());
                        intent.putExtra(CardDetailFragment.ARG_BTC_VALUE, holder.mBtcValue.getText().toString());
                        intent.putExtra(CardDetailFragment.ARG_ETH_VALUE, holder.mEthValue.getText().toString());
                        context.startActivity(intent);

                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.getCount();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mCountryName;
            public final TextView mBtcValue;
            public final TextView mEthValue;
            public final ImageView mCountryFlag;


            public ViewHolder(View view) {
                super(view);
                mView = view;
                mCountryName = (TextView) view.findViewById(R.id.card_country_name);
                mBtcValue = (TextView) view.findViewById(R.id.card_btc_value);
                mEthValue = (TextView) view.findViewById(R.id.card_eth_value);
                mCountryFlag = (ImageView) view.findViewById(R.id.card_country_flag);
            }

            @Override
            public String toString() {
                return super.toString()
//                        + " '" + mContentView.getText() + "'"
                        ;
            }
        }
    }
}