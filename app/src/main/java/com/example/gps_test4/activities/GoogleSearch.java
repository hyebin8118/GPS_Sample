package com.example.gps_test4.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gps_test4.R;
import com.example.gps_test4.adapter.LocationArrayAdapter;
import com.example.gps_test4.controller.GoogleMapController;
import com.example.gps_test4.controller._LocationController;
import com.example.gps_test4.model._Location;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;

public class GoogleSearch extends AppCompatActivity implements OnMapReadyCallback{
    _LocationController locationController;
    GoogleMapController googleMapController;
    ArrayList<_Location> locations;
    Spinner spinner_dropdown;
    TextView text_location;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_search);
        locationController = new _LocationController(this);
        locations = locationController.getLocationData();
        spinner_dropdown = (Spinner) findViewById(R.id.spinner_city);
        spinner_dropdown.setAdapter(new LocationArrayAdapter(this, locations));
        text_location = (TextView) findViewById(R.id.code_administrative_text);

        Log.d("MAIN_ACTIVITY:LOCATION_SIZE:", locations.size() + "");
        locations.forEach((location)->{
            //Log.d("DATA:CHECK:", location.toString());
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);
        setEvent();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMapController = new GoogleMapController(getApplicationContext(), googleMap);
    }

    public void setEvent(){
        Log.d("CALL_SETEVENT:", "OK");
        spinner_dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("SPINNER:SELECTED:", ": " + position);
                // onCreate()에서 setAdapter()해준 adapter를 참조하여, location 객체의 값을 가져와야한다.
                LocationArrayAdapter adapter = (LocationArrayAdapter) parent.getAdapter();
                _Location selectedLocation = (_Location) adapter.getItem(position);

                if(selectedLocation!=null){
                    String locationValue = selectedLocation.getLocation();
                    //대상이 될 TextView를 참조한다.
                    Log.d("SPINNER:RESULT:", ": " + locationValue);
                    text_location.setText(locationValue);
                    //구글맵을 조작하여 장소를 보여준다.
                    googleMapController.Search(locationValue);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}