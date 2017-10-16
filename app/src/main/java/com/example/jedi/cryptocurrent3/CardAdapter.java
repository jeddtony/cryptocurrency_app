package com.example.jedi.cryptocurrent3;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jedi.cryptocurrent3.data.CryptocurrentContract;

import java.util.Map;

/**
 * Created by jedi on 10/14/2017.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardAdapterViewHolder> {
    private final Context mContext;
    private Cursor mCursor;

    private final CardAdapterOnClickListener mClickHandler;

    public interface  CardAdapterOnClickListener {
        void onClick(String countryName);
    }


    public CardAdapter(Context context, CardAdapterOnClickListener clickHandler){
        mContext = context;
        mClickHandler = clickHandler;
    }

    @Override
    public CardAdapter.CardAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.card_list_item, parent, false);
        view.setFocusable(true);
        return new CardAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardAdapter.CardAdapterViewHolder holder, int position) {

        mCursor.moveToPosition(position);
        int countryColumnIndex = mCursor.getColumnIndex(CryptocurrentContract.CryptocurrentEntry.COLUMN_COUNTRY);
        String countryName = mCursor.getString(countryColumnIndex);
        Log.i("Country name", ""+ countryName);
        Map[] allMaps = MainActivity.allMaps;
        for (Map map: allMaps) {
            if(map.get("country").equals(countryName) ){
                Log.i("looping", "" + countryName + " " + map.get("country"));
                String country = map.get("country").toString();
                String btcValue = map.get("btcValue").toString();
                String ethValue = map.get("ethValue").toString();
                Log.i("BtcValue", ""+btcValue);
                Log.i("EthValue", ""+ethValue);
             holder.mCardCountryName.setText(country);
                holder.mCardBtcValue.setText(btcValue);
                holder.mCardEthValue.setText(ethValue);
            }
//            else {
//                Log.i("Na lie", "Map country does not match any country name");
//                Log.i("looping", "" + countryName + " " + map.get("country"));
//            }
        }
    }

    @Override
    public int getItemCount() {
        if(mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

    void swapCursor(Cursor cursor){
        mCursor = cursor;
        notifyDataSetChanged();
    }



    class CardAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final TextView mCardCountryName;
        final TextView mCardBtcValue;
        final TextView mCardEthValue;

        CardAdapterViewHolder(View view){
            super(view);
            mCardCountryName = (TextView) view.findViewById(R.id.card_country_name);
            mCardBtcValue = (TextView) view.findViewById(R.id.card_btc_value);
            mCardEthValue = (TextView) view.findViewById(R.id.card_eth_value);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(mContext,"You clicked on a recyclerview", Toast.LENGTH_LONG).show();
            int adapterPosition = getAdapterPosition();
            mCursor.moveToPosition(adapterPosition);
            int countryColumnIndex = mCursor.getColumnIndex(CryptocurrentContract.CryptocurrentEntry.COLUMN_COUNTRY);
            String countryName = mCursor.getString(countryColumnIndex);
            mClickHandler.onClick(countryName);
        }
    }
}
