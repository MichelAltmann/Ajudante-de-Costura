package com.example.ajudantedecostura.home.pedido.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ajudantedecostura.R;

import java.util.ArrayList;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {
    private Context context;
    private ArrayList<String> values;

    public CustomSpinnerAdapter(Context context, int textViewResourceId, ArrayList<String> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    public int getCount(){
        return values.size();
    }

    public String getItem(int position){
        return values.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(context.getResources().getColor(R.color.black));
        label.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        label.setText(values.get(position));

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(context.getResources().getColor(R.color.black));
        label.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        label.setPadding( (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 10, context.getResources().getDisplayMetrics()
        ), (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 14, context.getResources().getDisplayMetrics()
        ), (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 10, context.getResources().getDisplayMetrics()
        ), (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 14, context.getResources().getDisplayMetrics()
        ));
        label.setText(values.get(position));

        return label;
    }
}