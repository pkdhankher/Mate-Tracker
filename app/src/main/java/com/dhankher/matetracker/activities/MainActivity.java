package com.dhankher.matetracker.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dhankher.matetracker.R;
import com.dhankher.matetracker.eventbus.UpdatedLocationEvent;
import com.dhankher.matetracker.location.Loc;
import com.dhankher.matetracker.location.LocationManagerClass;
import com.dhankher.matetracker.location.LocationUpdaeDetector;
import com.dhankher.matetracker.map.MapFragment;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity implements LocationUpdaeDetector {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MapFragment.class));
            }
        });



        int minTime = 2000;
        float minDistance = 1;
        LocationManagerClass myLocListener = new LocationManagerClass(getApplicationContext(),this);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setSpeedRequired(false);
        String bestProvider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(bestProvider, minTime, minDistance, myLocListener);
//        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, minTime, minDistance, myLocListener);

    }


    @Override
    public void onLocationUpdated(Loc location) {

        EventBus.getDefault().post(new UpdatedLocationEvent(location));

    }
}
