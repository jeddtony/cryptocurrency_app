package com.example.jedi.cryptocurrent3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by jedi on 10/30/2017.
 */

public class CustomSpinnerAdapter extends BaseAdapter {
    Context mContext;
    String[] mCountryNames;
    int[] mCountryFlags;
    LayoutInflater layoutInflater;


    public CustomSpinnerAdapter(Context context, int[] countryFlag, String[] countryNames){
        mContext = context;
        mCountryFlags = countryFlag;
        mCountryNames = countryNames;
        layoutInflater = (LayoutInflater.from(context));

    }


    @Override
    public int getCount() {
        return mCountryFlags.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
            view = layoutInflater.inflate(R.layout.custom_spinner_items, null);
        ImageView icon = (ImageView) view.findViewById(R.id.spinner_image_view);
        TextView names = (TextView) view.findViewById(R.id.spinner_text_view);
        icon.setImageResource(mCountryFlags[i]);
        names.setText(mCountryNames[i]);
        return view;
    }
}
