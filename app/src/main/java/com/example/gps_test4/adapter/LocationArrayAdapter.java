package com.example.gps_test4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gps_test4.model._Location;
import com.example.gps_test4.R;

import java.util.ArrayList;

public class LocationArrayAdapter extends BaseAdapter {

    Context context;
    private ArrayList<_Location> items;

    public LocationArrayAdapter(Context context, ArrayList<_Location> locations){
        this.context = context;
        this.items = locations;
    }
    @Override
    public int getCount() {
        return (items==null)? 0 : items.size();
    }
    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.location_spinner_item, parent, false);
        }
        _Location location = items.get(position);
        TextView spinner_text = convertView.findViewById(R.id.spinner_text);
        spinner_text.setText(location.getCode());
        return convertView;
    }
}