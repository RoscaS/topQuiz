package com.example.topquiz_kotlin.controllers

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.topquiz_kotlin.R
import com.example.topquiz_kotlin.models.Question
import com.example.topquiz_kotlin.models.QuestionBank
import com.example.topquiz_kotlin.questions

class GameActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var questionBank: QuestionBank

    private lateinit var questionText: TextView
    private lateinit var answer1Btn: Button
    private lateinit var answer2Btn: Button
    private lateinit var answer3Btn: Button
    private lateinit var answer4Btn: Button

    private var answerIdx = 0
    private var rounds = 3
    private var score = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        questionText = findViewById(R.id.activity_game_question_text)
        answer1Btn = findViewById(R.id.activity_game_answer1_btn)
        answer2Btn = findViewById(R.id.activity_game_answer2_btn)
        answer3Btn = findViewById(R.id.activity_game_answer3_btn)
        answer4Btn = findViewById(R.id.activity_game_answer4_btn)

        answer1Btn.tag = 0
        answer2Btn.tag = 1
        answer3Btn.tag = 2
        answer4Btn.tag = 3

        answer1Btn.setOnClickListener(this)
        answer2Btn.setOnClickListener(this)
        answer3Btn.setOnClickListener(this)
        answer4Btn.setOnClickListener(this)

        questionBank = QuestionBank(questions)
        displayQuestion(questionBank.getQuestion())
    }

    private fun displayQuestion(question: Question) {
        questionText.text = question.question
        answer1Btn.text = question.choices[0]
        answer2Btn.text = question.choices[1]
        answer3Btn.text = question.choices[2]
        answer4Btn.text = question.choices[3]
        answerIdx = question.answerIdx
    }

    private fun computeAnswer(answeredIdx: Int) {
        val text = if (answeredIdx == this.answerIdx) "Correct!" else "Wrong"
        if (answeredIdx == this.answerIdx) score++
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    private fun endGame() {
        AlertDialog.Builder(this)
            .setTitle("Well done!")
            .setMessage("Your score is $score")
            .setPositiveButton("Ok", { dialog, which ->
                val intent = Intent()
                intent.putExtra("BUNDLE_EXTRA_SCORE", score)
                setResult(Activity.RESULT_OK, intent)
                finish()
            })
            .create()
            .show()
    }

    override fun onClick(v: View?) {
        computeAnswer(v?.getTag() as Int)
        if (--rounds == 0) endGame() else displayQuestion(questionBank.getQuestion())
    }
}
