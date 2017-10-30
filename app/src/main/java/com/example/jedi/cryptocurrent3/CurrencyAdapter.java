package com.example.jedi.cryptocurrent3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jedi.cryptocurrent3.utils.CountryUtils;

import java.util.Map;

/**
 * Created by jedi on 10/9/2017.
 */

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyAdapterViewHolder> {
    private Context mContext;
    private Map[] mCurrencyData;

    public CurrencyAdapter(Context context){
        mContext = context;
    }

    @Override
    public CurrencyAdapter.CurrencyAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.currency_list_item, parent, false);
        view.setFocusable(true);
        return new CurrencyAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CurrencyAdapter.CurrencyAdapterViewHolder holder, int position) {
        Map<String, String> currencyForThisCountry = mCurrencyData[position];
        String countryName = currencyForThisCountry.get("country");
        holder.mCountryFlag.setImageResource(CountryUtils.getCountryFlag(countryName));
        holder.mCountry.setText(currencyForThisCountry.get("country"));
        holder.mBtcValue.setText(currencyForThisCountry.get("btcValue"));
        holder.mEthValue.setText(currencyForThisCountry.get("ethValue"));

    }

    @Override
    public int getItemCount() {
        if (mCurrencyData == null) {
            return 0;
        }
        return mCurrencyData.length;
    }

   public void swapMap(Map[] newMap){
        mCurrencyData = newMap;
        notifyDataSetChanged();
    }

    class CurrencyAdapterViewHolder extends RecyclerView.ViewHolder{
        final TextView mCountry;
        final TextView mEthValue;
        final TextView mBtcValue;
        final ImageView mCountryFlag;

        public CurrencyAdapterViewHolder(View itemView) {
            super(itemView);
            mCountry = (TextView)itemView.findViewById(R.id.country_value);
            mBtcValue = (TextView) itemView.findViewById(R.id.btc_value);
            mEthValue = (TextView) itemView.findViewById(R.id.eth_value);
            mCountryFlag = (ImageView) itemView.findViewById(R.id.list_country_flag);

        }

    }
}
