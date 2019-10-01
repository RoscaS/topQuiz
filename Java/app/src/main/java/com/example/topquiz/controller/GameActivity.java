package com.example.topquiz.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topquiz.R;
import com.example.topquiz.data.Questions;
import com.example.topquiz.model.Question;
import com.example.topquiz.model.QuestionBank;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";

    private QuestionBank questionBank;

    private TextView questionText;
    private Button answer1Btn;
    private Button answer2Btn;
    private Button answer3Btn;
    private Button answer4Btn;

    private int answerIdx;
    private int rounds;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        rounds = 3;

        questionText = findViewById(R.id.activity_game_question_text);
        answer1Btn = findViewById(R.id.activity_game_answer1_btn);
        answer2Btn = findViewById(R.id.activity_game_answer2_btn);
        answer3Btn = findViewById(R.id.activity_game_answer3_btn);
        answer4Btn = findViewById(R.id.activity_game_answer4_btn);

        answer1Btn.setTag(0);
        answer2Btn.setTag(1);
        answer3Btn.setTag(2);
        answer4Btn.setTag(3);

        answer1Btn.setOnClickListener(this);
        answer2Btn.setOnClickListener(this);
        answer3Btn.setOnClickListener(this);
        answer4Btn.setOnClickListener(this);

        questionBank = new QuestionBank(Questions.QUESTIONS);
        displayQuestion(questionBank.getQuestion());

    }

    private void displayQuestion(final Question question) {
        questionText.setText(question.getQuestion());
        answer1Btn.setText(question.getChoices().get(0));
        answer2Btn.setText(question.getChoices().get(1));
        answer3Btn.setText(question.getChoices().get(2));
        answer4Btn.setText(question.getChoices().get(3));
        answerIdx = question.getAnswerIdx();
    }

    private void computeAnswer(int answeredIdx) {
        Boolean isCorrect = checkAnswer(answeredIdx);
        String text = isCorrect ? "Correct!" : "Wrong!";
        score += isCorrect ? 1 : 0;
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Well done!")
                .setMessage("Your score is " + score)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, score);
                        setResult(RESULT_OK, intent);
                        finish(); // close current activity and show parent activity.
                    }
                })
                .create()
                .show();
    }

    private Boolean checkAnswer(int answeredIdx) {
        return answerIdx == answeredIdx;
    }

    @Override
    public void onClick(View v) {
        computeAnswer((int) v.getTag());
        if (--rounds == 0) {
            endGame();
        }
        else {
            displayQuestion(questionBank.getQuestion());
        }
    }


}
