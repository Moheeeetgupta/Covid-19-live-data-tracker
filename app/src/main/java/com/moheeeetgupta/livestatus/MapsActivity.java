package com.moheeeetgupta.livestatus;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback , LocationListener ,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    GoogleApiClient client;
    LocationRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager ()
                .findFragmentById (R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync (this);
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
    public void Findhospitals(View view){
            StringBuilder stringBuilder =new StringBuilder ("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");


            stringBuilder.append ("&radius="+1000);
            stringBuilder.append ("&keyword="+"Hospital");
            stringBuilder.append ("&key="+getResources ().getString (R.string.google_places_key));

            String url =stringBuilder.toString ();

            Object dataTransfer[]= new Object[2];
            dataTransfer[0]=mMap;
            dataTransfer[1]=url;


            Getnearbyhospitals getnearbyhospitals=new Getnearbyhospitals (this);
            getnearbyhospitals.execute (dataTransfer);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        client = new GoogleApiClient.Builder (this)
                .addApi (LocationServices.API)
                .addConnectionCallbacks (this)
                .addOnConnectionFailedListener (this)
                .build ();
        client.connect ();


    }

    @Override
    public void onLocationChanged(Location location) {
        if(location==null)
        {
            Toast.makeText (this, "Location not found", Toast.LENGTH_SHORT).show ();
        }else{
            LatLng latLngCurrent =new LatLng (location.getLatitude (),location.getLongitude ());
            CameraUpdate update=CameraUpdateFactory.newLatLngZoom (latLngCurrent,15);
            mMap.animateCamera (update);
            MarkerOptions options= new MarkerOptions ();
            options.position (latLngCurrent);
            options.title ("Current Location");
            mMap.addMarker(options);
        }
    }




    @Override
    public void onConnected(@Nullable Bundle bundle) {
            request = new LocationRequest ().create ();
            request.setInterval (1000);
            request.setPriority (LocationRequest.PRIORITY_HIGH_ACCURACY);
            if(ActivityCompat.checkSelfPermission (this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
            {
                return;
            }
        LocationServices.FusedLocationApi.requestLocationUpdates (client, request, this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
