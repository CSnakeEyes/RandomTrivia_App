package com.example.android.randomtriviaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card);

        ((EditText) findViewById(R.id.question_et)).setText(getIntent().getStringExtra("question"));
        ((EditText) findViewById(R.id.correct_answer_et)).setText(getIntent().getStringExtra("answer"));
        ((EditText) findViewById(R.id.incorrect_answerone_et)).setText(getIntent().getStringExtra("wrongOne"));
        ((EditText) findViewById(R.id.incorrect_answertwo_et)).setText(getIntent().getStringExtra("wrongTwo"));


        // Save date and send it back to Main Activity to use it for new question and answer cards
        findViewById(R.id.save_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save data in String variables
                String question = ((EditText) findViewById(R.id.question_et)).getText().toString();
                String correctAnswer = ((EditText) findViewById(R.id.correct_answer_et)).getText().toString();
                String wrongAnswerOne = ((EditText) findViewById(R.id.incorrect_answerone_et)).getText().toString();
                String wrongAnswerTwo = ((EditText) findViewById(R.id.incorrect_answertwo_et)).getText().toString();

                if(question.equals("") || correctAnswer.equals("")
                        || wrongAnswerOne.equals("") || wrongAnswerTwo.equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Error: Missing Information, Please Fill Out All Fields", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }else{
                    // Create intent to and allocate data in it
                    Intent data = new Intent();
                    data.putExtra("question", question);
                    data.putExtra("ans", correctAnswer);
                    data.putExtra("wrongOne", wrongAnswerOne);
                    data.putExtra("wrongTwo", wrongAnswerTwo);

                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });

        findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
