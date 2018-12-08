package com.example.android.randomtriviaapp;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final int NEW_CARD_REQUEST_CODE = 100;
    private final int EDIT_CARD_REQUEST_CODE = 200;

    FlashcardDatabase flashcardDatabase;        // Creation of database using Room Library
    List<Flashcard> allFlashcards;              // List of created flashcards
    int currentCardDisplayedIndex = 0;          // Current flashcard displayed
    Flashcard cardToEdit;

    public int getRandomNumber(int minNumber, int maxNumber) {
        Random rand = new Random();
        return rand.nextInt((maxNumber - minNumber) + 1) + minNumber;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(this);
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcardQ1_tv)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
            ((TextView) findViewById(R.id.flashcardA_tv)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
            ((TextView) findViewById(R.id.flashcardA1_tv)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
            ((TextView) findViewById(R.id.flashcardA2_tv)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
            ((TextView) findViewById(R.id.flashcardA3_tv)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
        }

        findViewById(R.id.flashcardQ1_tv).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                findViewById(R.id.flashcardA_tv).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcardQ1_tv).setVisibility(View.INVISIBLE);

                View answerSideView = findViewById(R.id.flashcardA_tv);
                View questionSideView = findViewById(R.id.flashcardQ1_tv);

                // get the center for the clipping circle
                int cx = answerSideView.getWidth() / 2;
                int cy = answerSideView.getHeight() / 2;

                // get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

                // create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

                // hide the question and show the answer to prepare for playing the animation!
                questionSideView.setVisibility(View.INVISIBLE);
                answerSideView.setVisibility(View.VISIBLE);

                anim.setDuration(1000);
                anim.start();

                findViewById(R.id.flashcardA1_tv).setBackgroundColor(getResources().getColor(R.color.lightOrange));
                findViewById(R.id.flashcardA2_tv).setBackgroundColor(getResources().getColor(R.color.lightOrange));
                findViewById(R.id.flashcardA3_tv).setBackgroundColor(getResources().getColor(R.color.lightOrange));
            }
        });

        findViewById(R.id.flashcardA_tv).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                findViewById(R.id.flashcardA_tv).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcardQ1_tv).setVisibility(View.VISIBLE);

                View answerSideView = findViewById(R.id.flashcardA_tv);
                View questionSideView = findViewById(R.id.flashcardQ1_tv);

                // get the center for the clipping circle
                int cx = questionSideView.getWidth() / 2;
                int cy = questionSideView.getHeight() / 2;

                // get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

                // create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(questionSideView, cx, cy, 0f, finalRadius);

                // hide the question and show the answer to prepare for playing the animation!
                questionSideView.setVisibility(View.VISIBLE);
                answerSideView.setVisibility(View.INVISIBLE);

                anim.setDuration(1000);
                anim.start();

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
                MainActivity.this.startActivityForResult(intent, NEW_CARD_REQUEST_CODE);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        findViewById(R.id.edit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardToEdit = allFlashcards.get(currentCardDisplayedIndex);
                System.out.println("Hello this: " + cardToEdit.getQuestion());

                String question = ((TextView) findViewById(R.id.flashcardQ1_tv)).getText().toString();
                String correctAnswer = ((TextView) findViewById(R.id.flashcardA_tv)).getText().toString();
                String wrongAnswerOne = ((TextView) findViewById(R.id.flashcardA2_tv)).getText().toString();
                String wrongAnswerTwo = ((TextView) findViewById(R.id.flashcardA3_tv)).getText().toString();

                Intent intent = new Intent(MainActivity.this, NewCardActivity.class);
                intent.putExtra("question", question);
                intent.putExtra("answer", correctAnswer);
                intent.putExtra("wrongOne", wrongAnswerOne);
                intent.putExtra("wrongTwo", wrongAnswerTwo);
                MainActivity.this.startActivityForResult(intent, EDIT_CARD_REQUEST_CODE);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        findViewById(R.id.next_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation leftOutAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.left_out);
                final Animation rightInAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.right_in);

                currentCardDisplayedIndex = getRandomNumber(0, allFlashcards.size() - 1);

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
//                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
//                    currentCardDisplayedIndex = 0;
//                }

                if (findViewById(R.id.flashcardQ1_tv).getVisibility() == View.VISIBLE) {
                    findViewById(R.id.flashcardQ1_tv).startAnimation(leftOutAnim);
                } else {
                    findViewById(R.id.flashcardA_tv).startAnimation(leftOutAnim);
                }

                findViewById(R.id.flashcardA1_tv).startAnimation(leftOutAnim);
                findViewById(R.id.flashcardA2_tv).startAnimation(leftOutAnim);
                findViewById(R.id.flashcardA3_tv).startAnimation(leftOutAnim);

                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // this method is called when the animation first starts
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // this method is called when the animation is finished playing
                        findViewById(R.id.flashcardA_tv).setVisibility(View.INVISIBLE);
                        findViewById(R.id.flashcardQ1_tv).setVisibility(View.VISIBLE);

                        findViewById(R.id.flashcardQ1_tv).startAnimation(rightInAnim);
                        findViewById(R.id.flashcardA1_tv).startAnimation(rightInAnim);
                        findViewById(R.id.flashcardA2_tv).startAnimation(rightInAnim);
                        findViewById(R.id.flashcardA3_tv).startAnimation(rightInAnim);

                        ((TextView) findViewById(R.id.flashcardQ1_tv)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                        ((TextView) findViewById(R.id.flashcardA_tv)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                        ((TextView) findViewById(R.id.flashcardA1_tv)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                        ((TextView) findViewById(R.id.flashcardA2_tv)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                        ((TextView) findViewById(R.id.flashcardA3_tv)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }
                });
            }
        });

        findViewById(R.id.delete_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcardQ1_tv)).getText().toString());
                allFlashcards = flashcardDatabase.getAllCards();
                currentCardDisplayedIndex++;

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }

                ((TextView) findViewById(R.id.flashcardQ1_tv)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                ((TextView) findViewById(R.id.flashcardA_tv)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                ((TextView) findViewById(R.id.flashcardA1_tv)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                ((TextView) findViewById(R.id.flashcardA2_tv)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                ((TextView) findViewById(R.id.flashcardA3_tv)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            String question = data.getExtras().getString("question");
            String correctAnswer = data.getExtras().getString("ans");
            String wrongAnswerOne = data.getExtras().getString("wrongOne");
            String wrongAnswerTwo = data.getExtras().getString("wrongTwo");
            if (requestCode == NEW_CARD_REQUEST_CODE && resultCode == RESULT_OK) {
                ((TextView) findViewById(R.id.flashcardQ1_tv)).setText(question);
                ((TextView) findViewById(R.id.flashcardA_tv)).setText(correctAnswer);
                ((TextView) findViewById(R.id.flashcardA1_tv)).setText(correctAnswer);
                ((TextView) findViewById(R.id.flashcardA2_tv)).setText(wrongAnswerOne);
                ((TextView) findViewById(R.id.flashcardA3_tv)).setText(wrongAnswerTwo);

                Snackbar.make(findViewById(R.id.flashcardQ1_tv),
                        "Card Successfully Created!",
                        Snackbar.LENGTH_SHORT)
                        .show();

                flashcardDatabase.insertCard(new Flashcard(question, correctAnswer, wrongAnswerOne, wrongAnswerTwo));
            } else if (requestCode == EDIT_CARD_REQUEST_CODE && resultCode == RESULT_OK) {
                cardToEdit.setQuestion(question);
                cardToEdit.setAnswer(correctAnswer);
                cardToEdit.setWrongAnswer1(wrongAnswerOne);
                cardToEdit.setWrongAnswer2(wrongAnswerTwo);

                Snackbar.make(findViewById(R.id.flashcardQ1_tv),
                        "Card Successfully Updated!",
                        Snackbar.LENGTH_SHORT)
                        .show();

                flashcardDatabase.updateCard(cardToEdit);

                ((TextView) findViewById(R.id.flashcardQ1_tv)).setText(question);
                ((TextView) findViewById(R.id.flashcardA_tv)).setText(correctAnswer);
                ((TextView) findViewById(R.id.flashcardA1_tv)).setText(correctAnswer);
                ((TextView) findViewById(R.id.flashcardA2_tv)).setText(wrongAnswerOne);
                ((TextView) findViewById(R.id.flashcardA3_tv)).setText(wrongAnswerTwo);
            }
        }

        allFlashcards = flashcardDatabase.getAllCards();
    }
}
