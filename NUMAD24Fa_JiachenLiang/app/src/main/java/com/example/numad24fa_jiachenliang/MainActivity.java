package com.example.numad24fa_jiachenliang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the "About Me" button and its click listener
        Button aboutMeButton = findViewById(R.id.aboutMeButton);
        aboutMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display the Toast with your name and email
                Toast.makeText(MainActivity.this, "Name: Jiachen Liang, Email: liang.jiac@northeastern.edu", Toast.LENGTH_LONG).show();
            }
        });

        // Set up the "Quic Calc" button and its click listener
        Button quickCalcButton = findViewById(R.id.quicCalcButton);
        quickCalcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start QuickCalcActivity
                Intent intent = new Intent(MainActivity.this, QuickCalcActivity.class);
                startActivity(intent);
            }
        });
    }
}
