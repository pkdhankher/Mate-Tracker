package com.dhankher.matetracker.workers;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Dhankher on 3/16/2017.
 */

public class UpdateUserLocation extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;
    String TAG ="UpdateUserLocation";

    public UpdateUserLocation(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {

        String type = params[0];


        if (type.equals("updatelocation"))
        {
            String logurl = "http://10.1.0.80/mateTracker/updateLocation.php";   //for localhost -pawan
//            String logurl = "http://pawan.esy.es/pp1.php";

            try {
                String lat = params[1];
                String lng = params[2];
                String token = params[3];
                Log.d("lat"+lat,"token: "+token);
                URL url = new URL(logurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postdata = URLEncoder.encode("lat", "UTF-8") + "=" + URLEncoder.encode(lat, "UTF-8") + "&"
                     +  URLEncoder.encode("lng", "UTF-8") + "=" + URLEncoder.encode(lng, "UTF-8") + "&"
                        +  URLEncoder.encode("token", "UTF-8") + "=" + URLEncoder.encode(token, "UTF-8");
                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        return null;
    }

    @Override
    protected void onPreExecute() {

//        alertDialog = new AlertDialog.Builder(context).create();
//        alertDialog.setTitle("SignUp Status");

    }


    @Override
    protected void onPostExecute(String result) {
//        alertDialog.setMessage(result);
//        alertDialog.show();
    }
    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }


}
