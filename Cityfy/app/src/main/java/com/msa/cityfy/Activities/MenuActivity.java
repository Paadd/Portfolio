package com.msa.cityfy.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.msa.cityfy.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button pointButton = findViewById(R.id.pointsButton);
        pointButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, PointActivity.class));
            }

        });
        Button newstButton = findViewById(R.id.newsButton);
        newstButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, NewsActivity.class));
            }

        });
        Button help =findViewById(R.id.helpButton);
        help.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, Help.class));
            }

        });




        Button pollutionButton = findViewById(R.id.pollutionButton);
        pollutionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, PollutionActivity.class));
            }

        });

    }
}
