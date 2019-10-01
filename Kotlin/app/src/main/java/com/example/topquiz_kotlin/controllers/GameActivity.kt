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
import com.example.topquiz_kotlin.models.QuestionBank
import kotlinx.android.synthetic.main.activity_game.*


class GameActivity : AppCompatActivity(), View.OnClickListener {

    private val questionBank = QuestionBank()
    private lateinit var buttons: Array<Button>
    private lateinit var questionText: TextView

    private var answerIdx = 0
    private var rounds = 3
    private var score = 0

    companion object { // ~ Java's static final field
        const val BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE"
        const val REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        displayQuestion()
    }

    override fun onClick(v: View?) {
        val tag = v?.tag as Int
        computeAnswer(tag)
        if (--rounds == 0) endGame()
        else displayQuestion()
    }

    private fun initLayout() {
        setContentView(R.layout.activity_game)
        questionText = activity_game_question_text
        buttons = arrayOf(
            activity_game_answer1_btn,
            activity_game_answer2_btn,
            activity_game_answer3_btn,
            activity_game_answer4_btn
        )
        for ((c, btn) in buttons.withIndex()) {
            btn.setOnClickListener(this)
            btn.tag = c
        }
    }

    private fun displayQuestion() {
        val question = questionBank.getQuestion()
        questionText.text = question.question
        answerIdx = question.answerIdx
        for ((c, btn) in buttons.withIndex()){
            btn.text = question.choices[c]
        }
    }

    private fun computeAnswer(answeredIdx: Int) {
        val isCorrect = answeredIdx == this.answerIdx
        val text = if (isCorrect) "Correct!" else "Wrong"
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
        if (isCorrect) score++
    }

    private fun endGame() {
        val onClickAction = { dialog: DialogInterface, which: Int ->
            Intent().putExtra(BUNDLE_EXTRA_SCORE, score)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        AlertDialog.Builder(this)
            .setTitle("Well done!")
            .setMessage("Your score is $score")
            .setPositiveButton("Ok", onClickAction)
            .create()
            .show()
    }
}
