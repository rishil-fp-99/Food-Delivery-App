package com.example.foodies5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodies5.ui.home.HomeFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Google_Maps extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mMap;
    static int REQUEST_CODE = 1001;
    private static final String TAG = "Google_Maps";
    FusedLocationProviderClient fusedLocationProviderClient;
    SupportMapFragment mapFragment;
    public String stringBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google__maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            getCurrentLocation();


        } else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_CODE);
        }
    }

    public void getCurrentLocation() {
        //Intialize task location

        @SuppressLint("MissingPermission") Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
            if(location!=null)
            {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    final LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());

                    MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("User location");
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                    googleMap.addMarker(markerOptions);
                    googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {

                            Geocoder geocoder=new Geocoder(Google_Maps.this,Locale.getDefault());
                            try {
                                        List<Address> addresses=geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);

                               stringBuffer = addresses.get(0).getAdminArea() + " " +
                                        addresses.get(0).getLocality() + " " +
                                        addresses.get(0).getSubLocality();

                                Intent intent=new Intent(Google_Maps.this,home_screen_btmnav.class);
                                intent.putExtra("delivery_address",stringBuffer);
                                intent.putExtra("CHECK_INTENT",0);

                                startActivity(intent);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                            return true;
                        }
                    });
                }
            });
            }
            }
        });
    }

    public final String getCompleteAddress()
    {
        return stringBuffer;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.style_google_maps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.normal:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;

            case R.id.hybrid:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.terrain:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            case R.id.satellite:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            getLastLocation();
//        } else {
//            ask_location_permission();
//        }
//    }

//    private void ask_location_permission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(Google_Maps.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
//            } else {
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
//
//            }
//        }
//    }

//    private void getLastLocation() {
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
//        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
//        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//                if(location!=null)
//                {
//                    Log.d(TAG, String.valueOf(location.getLatitude()+location.getLongitude()));
//                    Toast.makeText(Google_Maps.this, ""+location.getLongitude()+location.getLatitude(), Toast.LENGTH_SHORT).show();
//                    mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//                        @Override
//                        public void onMapClick(final LatLng latLng) {
//                            mMap.clear();
//                            mMap.addMarker(new MarkerOptions().position(latLng).title("USER LOCATION"));
//                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,10F));
//
//                            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//                                @Override
//                                public boolean onMarkerClick(Marker marker) {
//
//                                    Geocoder geocoder=new Geocoder(Google_Maps.this,Locale.getDefault());
//                                    try {
//                                        List<Address> addresses=geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
//
//                                        StringBuilder stringBuffer=new StringBuilder();
//                                        stringBuffer.append(addresses.get(0).getAdminArea()+" ");
//                                        stringBuffer.append(addresses.get(0).getLocality()+"");
//                                        stringBuffer.append(addresses.get(0).getSubLocality());
//                                        Toast.makeText(Google_Maps.this, ""+stringBuffer.toString(), Toast.LENGTH_SHORT).show();
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
//                                    return true;
//                                }
//                            });
//
//                        }
//                    });
//                }
//                else{
//                    Log.e(TAG,"Null location");
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.e(TAG,""+e.getMessage());
//            }
//        });
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_CODE)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
               getCurrentLocation();

            }
//            else {
//                Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
//            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        TextView tv= findViewById(R.id.select_delivery_address);

    }
}