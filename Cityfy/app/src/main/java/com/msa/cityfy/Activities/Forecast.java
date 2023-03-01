package com.msa.cityfy.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.msa.cityfy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.msa.cityfy.Activities.Forecast.*;


public class Forecast extends AppCompatActivity {

    public static boolean done = false;
    public static JSONObject response = new JSONObject();
    public static JSONObject response2 = new JSONObject();
    public static int airQualityIndex = -1;
    public static double carbonMonOxide = -1.00;
    public static int AQISEV = -1;
    public static int COSEV = -1;
    public static double lat = 0;
    public static double longt = 0;
    public static Button button;
    public static Button citylocc1;
    public static String cityName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        button = findViewById(R.id.AQI_button4);






        LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //LOCATION TODO
        FusedLocationProviderClient client =  LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(Forecast.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION},1);
            return;
        }
        client.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                System.out.println(location);
                lat = location.getLatitude();
                longt = location.getLongitude();

                AsyncPollution2 asyncPollution2 = new AsyncPollution2();
                asyncPollution2.execute(response, done);
            }
        });




    }
}

class AsyncPollution2 extends AsyncTask {
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    @Override
    protected Object doInBackground(Object[] object) {
        String s = "";

        try {

            URL url = new URL("https://api.weatherbit.io/v2.0/forecast/airquality?lat="+lat+"&lon="+longt+"&key=fedda944778048cca1d683bcbee20f57");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responsecode = connection.getResponseCode();
            String x = "";
            if (responsecode != 200) {
                System.err.println("oh no!");
                throw new RuntimeException("HttpResponseCode:" + responsecode);

            } else {
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    s += sc.nextLine();

                }
                sc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            response = new JSONObject(s);

            JSONArray dataArray = response.getJSONArray("data");
            JSONObject data = dataArray.getJSONObject(0);
//            for(int i =0; i<dataArray.length();i++){
//
//            }
            airQualityIndex=data.getInt("aqi");
            carbonMonOxide = data.getDouble("co");
            cityName = response.getString("city_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //severity of AQI https://www.airnow.gov/aqi/aqi-basics/
        AQISEV = airQualityIndex/50;
        //AQISEV = 2;

        citylocc1.setText(""+cityName);
        button.setText(""+airQualityIndex);


        //TODO: coloring
        //TODO: 0 green, 1 yellow, 2 orange, etc.
        if(AQISEV==0){
            button.setBackgroundColor(Color.rgb(0,240,10));
        }
        if(AQISEV==1){
            button.setBackgroundColor(Color.rgb(245,245,10));
        }
        if(AQISEV==2){
            button.setBackgroundColor(Color.rgb(245,165,0));
        }

        if(AQISEV==3){
            button.setBackgroundColor(Color.rgb(245,0,0));
        }

        return null;
    }
}
