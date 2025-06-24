package com.example.foodies5;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class SetMyLocation extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "SetMyLocation";
    private GoogleMap mMap;
    FusedLocationProviderClient client;
    LocationRequest locationRequest;
    LocationCallback locationCallback;
    Marker userLocationMarker;
    SearchView searchView;
    //lcoation updates
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_my_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        searchView=findViewById(R.id.search_locations);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder(SetMyLocation.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        final Address address = Objects.requireNonNull(addressList).get(0);
                        Log.d(TAG, "Admin Area"+ address.getAdminArea()+
                                "\tSub Admin Area"+address.getSubAdminArea()+
                                "\tLocality:"+address.getLocality()+
                                "\tThrough Fare"+address.getThoroughfare()+
                                "\tSub Through Fare"+address.getSubThoroughfare()
                                +"\tPremises"+address.getPremises()
                                +"\tAddress Line"+address.getAddressLine(0));
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(latLng).title("My Location"));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
                        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(Marker marker) {
                                Intent intent=new Intent(SetMyLocation.this,home_screen_btmnav.class);
                                intent.putExtra("set_loc",address.getAddressLine(0));
                                intent.putExtra("CHECK_INTENT",1);
                                startActivity(intent);
                                return true;
                            }
                        });
                    }catch (Exception e)
                    {
                        Toast.makeText(SetMyLocation.this, "Enter appropriate location", Toast.LENGTH_SHORT).show();
                    }

                }
                return true;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("MY LOCATION");
             //   markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.pinmarker)));
                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15F));
            }
        });

    }

//    public void startLocationUpdates() {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        client.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
//
//    }
//    public void stopLocationUpdates()
//    {
//        client.removeLocationUpdates(locationCallback);
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED
//        && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED
//        )
//        {
//            startLocationUpdates();
//        }
//        else{
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},1234);
//        }
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        stopLocationUpdates();
//    }
}