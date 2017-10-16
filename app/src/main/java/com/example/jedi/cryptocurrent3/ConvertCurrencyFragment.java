package com.example.jedi.cryptocurrent3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jedi.cryptocurrent3.R;

/**
 * Created by jedi on 10/14/2017.
 */

public class ConvertCurrencyFragment extends Fragment {

    public ConvertCurrencyFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_convert_currency, container, false);
        ConstraintLayout constraintLayout = (ConstraintLayout) rootView.findViewById(R.id.convert_currency);
        return rootView;
    }
}
