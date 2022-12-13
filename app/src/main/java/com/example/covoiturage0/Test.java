package com.example.covoiturage0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Test extends AppCompatActivity implements OnMapReadyCallback {

    FusedLocationProviderClient fusedLocationProviderClient;
    EditText textSearch;
    Button btn;
    GoogleMap map;
    SupportMapFragment mapFragment;

    private final static int REQUEST_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textSearch = findViewById(R.id.textSearch1);
        btn = findViewById(R.id.btnSearch1);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googla_map1);


       btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = textSearch.getText().toString();
                List<Address> addressList = null;
                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder((Test.this));
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng marker = new LatLng(address.getLatitude(), address.getLongitude());
                    Log.d("TAG", String.valueOf(marker));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(marker, 15));
                    map.addMarker(new MarkerOptions().title(location).position(marker));

                }
            }


        });

        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
    }

    /*public void getLastLocation(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                Geocoder geocoder = new Geocoder(Test.this, Locale.getDefault());
                                try {
                                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                                    Address address = addresses.get(0);
                                    LatLng marker = new LatLng(address.getLatitude(), address.getLongitude());
                                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(marker, 15));
                                    map.addMarker(new MarkerOptions().title("location").position(marker));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                            }
                        }
                    });
        }else
        {
            askPermission();
        }
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(Test.this,new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                Toast.makeText(this, "Requied Permission", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }*/
}