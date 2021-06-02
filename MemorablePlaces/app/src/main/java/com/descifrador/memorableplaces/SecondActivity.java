package com.descifrador.memorableplaces;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SecondActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                }
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void updateMap(LatLng loca){
        mMap.clear();
        Toast.makeText(this, loca.toString(), Toast.LENGTH_SHORT).show();
        mMap.addMarker(new MarkerOptions().position(loca).title("You are Here"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loca,10f));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        Intent prev = getIntent();
        int pos = prev.getIntExtra("position",0);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                updateMap(latLng);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Toast.makeText(SecondActivity.this, "Please Enable Location", Toast.LENGTH_SHORT).show();
            }
        };



        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }

        else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            Location lastknownLoacation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            LatLng loca = new LatLng(22.0,78.0);
            if (lastknownLoacation != null) {
                loca = new LatLng(lastknownLoacation.getLatitude(),lastknownLoacation.getLongitude());
            }
            updateMap(loca);
        }


        if(pos==0){//adding new memorable location
            mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {
                    updateMap(latLng);
                    Toast.makeText(SecondActivity.this, "Added to Memorable Places ", Toast.LENGTH_SHORT).show();

                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    String addr="";

                    if(geocoder.isPresent()){
                        try {
                            List<Address> list = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
                            Address address = list.get(0);

                            if(address.getAddressLine(0)!=null){
                                addr+=address.getAddressLine(0);
                            }
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }

                        MainActivity.places.add(addr);
                        MainActivity.loca.add(latLng);

                    }//if block end of geocoder

                    Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }//long click override function
            });//Long click end
        }

        else{//showing previous saved location
            updateMap(MainActivity.loca.get(pos));
        }

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                updateMap(latLng);
            }
        });
    }
}
