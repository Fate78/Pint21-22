package com.pint.roombookerapp2.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.pint.roombookerapp2.Models.CentroGeo;

public class SpinAdapterCentros extends ArrayAdapter<CentroGeo> {
    private final Context context;
    private final CentroGeo[] values;

    public SpinAdapterCentros(Context context, int textViewResourceId, CentroGeo[] values)
    {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount(){
        return values.length;
    }

    @Override
    public CentroGeo getItem(int position){
        return values[position];
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setTextSize(20);
        label.setHeight(55);
        label.setWidth(228);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(values[position].getNomeCentro().toString());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getDropDownView(int position, View convertView,
                                @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(values[position].getNomeCentro().toString());

        return label;
    }

}
