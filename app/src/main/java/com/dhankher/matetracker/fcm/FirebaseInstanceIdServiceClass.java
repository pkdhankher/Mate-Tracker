package com.dhankher.matetracker.fcm;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Dhankher on 3/16/2017.
 */

public class FirebaseInstanceIdServiceClass extends FirebaseInstanceIdService {
    private static final String TAG = "FirebaseInstanceIdServiceClass";
    public static final String TOKEN_BROADCAST = "FirebaseInstanceIdServiceClass";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        getApplicationContext().sendBroadcast(new Intent(TOKEN_BROADCAST));
        storeToken(refreshedToken);
    }

    private void storeToken(String token) {
        SharedPrefManager.getInstance(getApplicationContext()).storeToken(token);
    }
}
