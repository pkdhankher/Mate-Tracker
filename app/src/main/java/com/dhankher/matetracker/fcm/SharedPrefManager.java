package com.dhankher.matetracker.fcm;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Dhankher on 2/26/2017.
 */

public class SharedPrefManager {
    private static final String KEY_TOKEN = "token";
    private static Context context;
    private static SharedPrefManager sharedPrefManager;


    private SharedPrefManager(Context context) {
        this.context = context;
    }

    public static SharedPrefManager getInstance(Context context) {
        if (sharedPrefManager == null)
            sharedPrefManager = new SharedPrefManager(context);
        return sharedPrefManager;
    }
    public boolean storeToken(String token){
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.dhankher.sharedpreferencesdummy", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(KEY_TOKEN, token).commit();
        return true;
    }

    public String getToken(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.dhankher.sharedpreferencesdummy", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(KEY_TOKEN,null);
        return token;
    }
}
