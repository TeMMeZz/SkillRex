package com.stuhawe.geigertim.login;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.stuhawe.geigertim.login.Remote.IGoogleApi;




public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    public double lat,lng;
    private Button btnGo,btnList;
    private EditText edtPlace;
    private String destination;
    private TextView kilometer;







    IGoogleApi mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_maps);
        setContentView(R.layout.map);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        kilometer = (TextView) findViewById(R.id.kilometer);

        //permissions
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        btnGo = (Button)findViewById(R.id.btn_search);
        btnList = (Button)findViewById(R.id.btn_list);
        edtPlace = (EditText)findViewById(R.id.edtPlace);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destination = edtPlace.getText().toString();
                destination = destination.replace(" ","+"); //Replcae space with + for url
                mapFragment.getMapAsync(MapsActivity.this);
            }
        });
        btnList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsActivity.this, MenuActivity.class));
            }
        });

        mService = Common.getGoogleApi();


        getLocation();


        mapFragment.getMapAsync(MapsActivity.this);

    }





    private void getLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {

            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationManager.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);

            if (location != null) {
                lat = location.getLatitude();
                lng = location.getLongitude();

            } else  if (location1 != null) {
                lat = location1.getLatitude();
                lng = location1.getLongitude();

            } else  if (location2 != null) {
                lat = location2.getLatitude();
                lng = location2.getLongitude();

            }else{
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                    buildAlertMessageNoGps();
                }
                Toast.makeText(this,"Standort konnte nicht gefunden werden",Toast.LENGTH_SHORT).show();

            }
        }
    }

    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("No GPS-Signal detected")
                .setCancelable(false)
                .setPositiveButton("GPS-Settings", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
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

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setTrafficEnabled(false);
        mMap.setIndoorEnabled(false);
        mMap.setBuildingsEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(true);


        getLocation();


        // logic noch zu implementieren
        final LatLng heitersheim = new LatLng(lat, lng);
        //final LatLng heitersheim = new LatLng(47.875077, 7.654158);
        mMap.addMarker(new MarkerOptions().position(heitersheim).title("Du bist gerade hier!"));

        CameraUpdate center = CameraUpdateFactory.newLatLng(heitersheim);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(14);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
        //mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
         //               .target(heitersheim)
         //               .zoom(5)       //zoomstärke -> Nähe
                        //.tilt(45)       //winkel     -> Drehung
         //               .build()));

        //Kreis der angezeigt wird
        final Circle circle = mMap.addCircle(new CircleOptions()
                                            .center(heitersheim)
                                            .radius(0)
                                            .strokeColor(Color.RED)
                                            .strokeWidth(1)
                                            .fillColor(0x330000FF));


        //Seekbar die den radius einstellt
        SeekBar seekBar;
        seekBar=(SeekBar)findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressValue= 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressValue=progress;
                circle.setRadius(progressValue);
                changeKilometer(progressValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //circle.setRadius(progressValue);
            }
        });



        //mMap onClick

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                Marker marker = mMap.addMarker(new MarkerOptions().position(point));
                /*if(marker.getPosition() == point) {
                    marker.remove();
                }*/
            }
        });
    }

    private void changeKilometer(int progressValue) {
        kilometer.setText(Integer.toString(progressValue));
    }

}
