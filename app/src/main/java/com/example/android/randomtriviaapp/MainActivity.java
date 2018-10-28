package com.example.android.randomtriviaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.flashcardQ1_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.flashcardA_tv).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcardQ1_tv).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcardA1_tv).setBackgroundColor(getResources().getColor(R.color.lightOrange));
                findViewById(R.id.flashcardA2_tv).setBackgroundColor(getResources().getColor(R.color.lightOrange));
                findViewById(R.id.flashcardA3_tv).setBackgroundColor(getResources().getColor(R.color.lightOrange));
            }
        });

        findViewById(R.id.flashcardA_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.flashcardA_tv).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcardQ1_tv).setVisibility(View.VISIBLE);
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

        findViewById(R.id.create_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

        findViewById(R.id.edit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = ((TextView) findViewById(R.id.flashcardQ1_tv)).getText().toString();
                String correctAnswer = ((TextView) findViewById(R.id.flashcardA_tv)).getText().toString();
                String wrongAnswerOne = ((TextView) findViewById(R.id.flashcardA2_tv)).getText().toString();
                String wrongAnswerTwo = ((TextView) findViewById(R.id.flashcardA3_tv)).getText().toString();

                Intent intent = new Intent(MainActivity.this, NewCardActivity.class);
                intent.putExtra("question", question);
                intent.putExtra("answer", correctAnswer);
                intent.putExtra("wrongOne", wrongAnswerOne);
                intent.putExtra("wrongTwo", wrongAnswerTwo);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == RESULT_OK){
            ((TextView) findViewById(R.id.flashcardQ1_tv)).setText(data.getExtras().getString("question"));
            ((TextView) findViewById(R.id.flashcardA_tv)).setText(data.getExtras().getString("ans"));
            ((TextView) findViewById(R.id.flashcardA1_tv)).setText(data.getExtras().getString("ans"));
            ((TextView) findViewById(R.id.flashcardA2_tv)).setText(data.getExtras().getString("wrongOne"));
            ((TextView) findViewById(R.id.flashcardA3_tv)).setText(data.getExtras().getString("wrongTwo"));
        }
    }
}
