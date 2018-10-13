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
                findViewById(R.id.flashcardA1_tv).setBackgroundColor(getResources().getColor(R.color.lightOrange));
                findViewById(R.id.flashcardA2_tv).setBackgroundColor(getResources().getColor(R.color.lightOrange));
                findViewById(R.id.flashcardA3_tv).setBackgroundColor(getResources().getColor(R.color.lightOrange));
            }
        });

        findViewById(R.id.flashcardA1_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.flashcardA1_tv).setBackgroundColor(getResources().getColor(R.color.lightGreen));
            }
        });

        findViewById(R.id.flashcardA2_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.flashcardA1_tv).setBackgroundColor(getResources().getColor(R.color.lightGreen));
                findViewById(R.id.flashcardA2_tv).setBackgroundColor(getResources().getColor(R.color.lightRed));
            }
        });

        findViewById(R.id.flashcardA3_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.flashcardA1_tv).setBackgroundColor(getResources().getColor(R.color.lightGreen));
                findViewById(R.id.flashcardA3_tv).setBackgroundColor(getResources().getColor(R.color.lightRed));
            }
        });

        findViewById(R.id.toggle_choices_invisible).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.toggle_choices_invisible).setVisibility(View.INVISIBLE);
                findViewById(R.id.toggle_choices_visible).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcardA1_tv).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcardA2_tv).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcardA3_tv).setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.toggle_choices_visible).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.toggle_choices_invisible).setVisibility(View.VISIBLE);
                findViewById(R.id.toggle_choices_visible).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcardA1_tv).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcardA2_tv).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcardA3_tv).setVisibility(View.VISIBLE);
            }
        });
    }
}
