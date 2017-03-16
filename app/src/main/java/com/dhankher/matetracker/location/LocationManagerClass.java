package com.dhankher.matetracker.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by Dhankher on 3/15/2017.
 */

public class LocationManagerClass implements LocationListener {

    private static final String TAG = "LocationManagerClass";
    private LocationUpdaeDetector locationUpdateDetector;
    private Context context;
    private Loc location = new Loc();

    public LocationManagerClass(Context context, LocationUpdaeDetector locationUpdateDetector) {
        this.context = context;
        this.locationUpdateDetector = locationUpdateDetector;
    }

    @Override
    public void onLocationChanged(Location loc) {
        if (loc != null) {
            location.setLat(loc.getLatitude());
            location.setLng(loc.getLongitude());
            locationUpdateDetector.onLocationUpdated(location);
            Log.d("longitude: " + loc.getLongitude(),"latitude"+loc.getLatitude());
            Toast.makeText(context, "lat:-"+loc.getLatitude()+"lng:-"+loc.getLongitude(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onProviderDisabled(String arg0) {
        // Do something here if you would like to know when the provider is disabled by the user
    }

    @Override
    public void onProviderEnabled(String arg0) {
        // Do something here if you would like to know when the provider is enabled by the user
    }

    @Override
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
        // Do something here if you would like to know when the provider status changes
    }
}
