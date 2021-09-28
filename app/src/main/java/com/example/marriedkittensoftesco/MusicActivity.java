package com.example.marriedkittensoftesco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class MusicActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN = 20000;

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";

    private TextView title;
    private TextView question;
    private TextView score_;
    private TextView count;
    private TextView timer;
    private RadioGroup group;
    private RadioButton a1;
    private RadioButton a2;
    private RadioButton a3;
    private RadioButton a4;
    private Button b;
    private Button o;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeft;

    private ArrayList<Question> questions;
    private int counter;
    private int total;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        title = findViewById(R.id.quiz);
        question = findViewById(R.id.question);
        score_ = findViewById(R.id.score);
        count = findViewById(R.id.question_count);
        timer = findViewById(R.id.timer);
        group = findViewById(R.id.radio_group);
        a1 = findViewById(R.id.radio_button1);
        a2 = findViewById(R.id.radio_button2);
        a3 = findViewById(R.id.radio_button3);
        a4 = findViewById(R.id.radio_button4);
        b = findViewById(R.id.button);
        o = findViewById(R.id.fifty);

        textColorDefaultRb = a1.getTextColors();
        textColorDefaultCd = timer.getTextColors();

        Intent intent = getIntent();
        String category = intent.getStringExtra(SecondActivity.EXTRA_CATEGORY);

        title.setText(category + " Quiz");

        if (savedInstanceState == null) {
            QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
            questions = dbHelper.getQuestions(category);
            total = questions.size();
            Collections.shuffle(questions);

            showNextQuestion();
        } else {
            questions = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            total = questions.size();
            counter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questions.get(counter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            timeLeft = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);

            if (!answered) {
                startCountDown();
            } else {
                updateCountDownText();
                showSolution();
            }
        }

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (a1.isChecked() || a2.isChecked() || a3.isChecked() || a4.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(MusicActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });

        o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> p = new ArrayList<>();
                if (currentQuestion.getAnswerNr() == 1) {
                    p.add(2);
                    p.add(3);
                    p.add(4);
                    Collections.shuffle(p);
                    for(int i = 0; i < p.size() - 1; i++) {
                        if (p.get(i) == 2) {
                            a2.setVisibility(View.INVISIBLE);
                        } else if (p.get(i) == 3) {
                            a3.setVisibility(View.INVISIBLE);
                        } else if (p.get(i) == 4) {
                            a4.setVisibility(View.INVISIBLE);
                        }
                    }
                } else if (currentQuestion.getAnswerNr() == 2) {
                    p.add(1);
                    p.add(3);
                    p.add(4);
                    Collections.shuffle(p);
                    for(int i = 0; i < p.size() - 1; i++) {
                        if (p.get(i) == 1) {
                            a1.setVisibility(View.INVISIBLE);
                        } else if (p.get(i) == 3) {
                            a3.setVisibility(View.INVISIBLE);
                        } else if (p.get(i) == 4) {
                            a4.setVisibility(View.INVISIBLE);
                        }
                    }
                } else if (currentQuestion.getAnswerNr() == 3) {
                    p.add(1);
                    p.add(2);
                    p.add(4);
                    Collections.shuffle(p);
                    for (int i = 0; i < p.size() - 1; i++) {
                        if (p.get(i) == 1) {
                            a1.setVisibility(View.INVISIBLE);
                        } else if (p.get(i) == 2) {
                            a2.setVisibility(View.INVISIBLE);
                        } else if (p.get(i) == 4) {
                            a4.setVisibility(View.INVISIBLE);
                        }
                    }
                }  else if (currentQuestion.getAnswerNr() == 4) {
                    p.add(1);
                    p.add(2);
                    p.add(3);
                    Collections.shuffle(p);
                    for (int i = 0; i < p.size() - 1; i++) {
                        if (p.get(i) == 1) {
                            a1.setVisibility(View.INVISIBLE);
                        } else if (p.get(i) == 2) {
                            a2.setVisibility(View.INVISIBLE);
                        } else if (p.get(i) == 3) {
                            a3.setVisibility(View.INVISIBLE);
                        }
                    }
                }
                o.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void showNextQuestion() {
        a1.setTextColor(textColorDefaultRb);
        a2.setTextColor(textColorDefaultRb);
        a3.setTextColor(textColorDefaultRb);
        a4.setTextColor(textColorDefaultRb);
        group.clearCheck();

        if (counter < total) {
            currentQuestion = questions.get(counter);

            question.setText(currentQuestion.getQuestion());
            a1.setText(currentQuestion.getOption1());
            a2.setText(currentQuestion.getOption2());
            a3.setText(currentQuestion.getOption3());
            a4.setText(currentQuestion.getOption4());

            counter++;
            count.setText("Question: " + counter + "/" + total);
            answered = false;
            b.setText("Confirm");

            timeLeft = COUNTDOWN;
            startCountDown();
        } else {
            finishQuiz();
        }
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeft = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText() {
        int seconds = (int) (timeLeft / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d", seconds);
        timeFormatted = timeFormatted + 's';

        timer.setText(timeFormatted);

        if (timeLeft < 10000) {
            timer.setTextColor(Color.RED);
        } else {
            timer.setTextColor(textColorDefaultCd);
        }
    }

    private void checkAnswer() {
        answered = true;

        countDownTimer.cancel();

        RadioButton selected = findViewById(group.getCheckedRadioButtonId());
        int nr = group.indexOfChild(selected) + 1;

        if (nr == currentQuestion.getAnswerNr()) {
            score++;
            score_.setText("Score: " + score);
        }

        showSolution();
    }

    private void showSolution() {
        a1.setVisibility(View.VISIBLE);
        a2.setVisibility(View.VISIBLE);
        a3.setVisibility(View.VISIBLE);
        a4.setVisibility(View.VISIBLE);

        a1.setTextColor(Color.RED);
        a2.setTextColor(Color.RED);
        a3.setTextColor(Color.RED);
        a4.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()) {
            case 1:
                a1.setTextColor(Color.GREEN);
                question.setText("Answer 1 is correct");
                break;
            case 2:
                a2.setTextColor(Color.GREEN);
                question.setText("Answer 2 is correct");
                break;
            case 3:
                a3.setTextColor(Color.GREEN);
                question.setText("Answer 3 is correct");
                break;
            case 4:
                a4.setTextColor(Color.GREEN);
                question.setText("Answer 4 is correct");
                break;
        }

        if (counter < total) {
            b.setText("Next");
        } else {
            b.setText("Finish");
        }
    }

    private void finishQuiz() {
        Intent intent = getIntent();
        String category = intent.getStringExtra(SecondActivity.EXTRA_CATEGORY);
        int id = intent.getIntExtra(MainActivity.EXTRA_ID, 0);
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        Intent result = new Intent();
        if(dbHelper.updateHighscore(score, category.toLowerCase(), id)) {
            result.putExtra(EXTRA_SCORE, score);
            setResult(RESULT_OK, result);
            updateLevel(score);
        }
        else {
            setResult(RESULT_CANCELED, result);
        }
        finish();
    }

    public void updateLevel(int score) {
        if(score == total) {
            Intent intent = getIntent();
            int id = intent.getIntExtra(MainActivity.EXTRA_ID, 0);
            QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
            dbHelper.updateLevel(id);
        }
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, counter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeft);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questions);
    }
}