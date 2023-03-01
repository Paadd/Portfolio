package com.msa.cityfy.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.msa.cityfy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.msa.cityfy.Activities.PollutionActivity.response;

public class wetestingbois extends FragmentActivity {

    public static ArrayList<String> texts = new ArrayList<>();
    public static ArrayList<Integer> aqi = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        try {
            JSONObject response = new JSONObject(b.getString("response"));
            JSONArray dataArray = response.getJSONArray("data");
            for(int i =1; i < dataArray.length();i++){
                JSONObject data =dataArray.getJSONObject(i);

                texts.add(data.getString("timestamp_local")+":      "+data.getInt("aqi"));
                aqi.add(data.getInt("aqi"));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_wetestingbois);



    }
}