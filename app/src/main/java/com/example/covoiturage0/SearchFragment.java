package com.example.covoiturage0;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class SearchFragment extends Fragment implements OnMapReadyCallback{

    FusedLocationProviderClient fusedLocationProviderClient;
    EditText textSearch;
    Button btn;
    GoogleMap map;
    SupportMapFragment mapFragment;
    private final static int REQUEST_CODE = 100;
    View view;


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_search, container, false);

        textSearch = view.findViewById(R.id.textSearch);
        btn = view.findViewById(R.id.btnSearch);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.googla_map);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = textSearch.getText().toString();
                List<Address> addressList = null;
                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder((getContext()));
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                        Address address = addressList.get(0);
                        LatLng marker = new LatLng(address.getLatitude(), address.getLongitude());
                        Log.d("TAG", String.valueOf(marker));
                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(marker, 15));
                        map.addMarker(new MarkerOptions().title(location).position(marker));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        mapFragment.getMapAsync(this);
        return view;
    }

    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
    }
}