package com.example.numad24fa_jiachenliang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
                // Start AboutMeActivity
                Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
                startActivity(intent);
            }
        });

        // Set up the "Quick Calc" button and its click listener
        Button quickCalcButton = findViewById(R.id.quicCalcButton);
        quickCalcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start QuickCalcActivity
                Intent intent = new Intent(MainActivity.this, QuickCalcActivity.class);
                startActivity(intent);
            }
        });
        Button contactsCollectorButton = findViewById(R.id.contactsCollectorButton);
        contactsCollectorButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ContactsCollectorActivity.class);
            startActivity(intent);
        });

    }

}
