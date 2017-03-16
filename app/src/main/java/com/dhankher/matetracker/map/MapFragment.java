package com.dhankher.matetracker.map;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.dhankher.matetracker.R;
import com.dhankher.matetracker.eventbus.UpdatedLocationEvent;
import com.dhankher.matetracker.location.Loc;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Dhankher on 3/16/2017.
 */

public class MapFragment extends FragmentActivity implements OnMapReadyCallback {
    private LatLng pawanPosition = new LatLng(28.476994168013334, 77.05816187895834);
    private GoogleMap map;
    private Marker pawan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_fragment);

        EventBus.getDefault().register(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
         pawan = googleMap.addMarker(new MarkerOptions().position(pawanPosition)
                .title("Pawan")
                .snippet("Pawan is here"));

//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(pawanPosition));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pawanPosition, 12.0f));

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(UpdatedLocationEvent event) {
        Loc location = event.updatedLocation();
        LatLng pawanPosition = new LatLng(location.getLat(), location.getLng());
//        map.moveCamera(CameraUpdateFactory.newLatLng(pawanPosition));
        pawan.setPosition(pawanPosition);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(pawanPosition, 18.0f));

    }

}

