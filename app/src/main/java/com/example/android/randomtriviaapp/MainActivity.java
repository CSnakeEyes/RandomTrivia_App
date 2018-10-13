package com.example.android.randomtriviaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.flashcardQ1_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.flashcardQ1_tv).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcardA1_tv).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.flashcardA1_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.flashcardA1_tv).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcardQ1_tv).setVisibility(View.VISIBLE);
            }
        });
    }
}
