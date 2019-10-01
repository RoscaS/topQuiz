package com.example.topquiz_kotlin.controllers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.topquiz_kotlin.R
import com.example.topquiz_kotlin.models.User

class MainActivity : AppCompatActivity() {

    private lateinit var greetingText: TextView
    private lateinit var nameInput: EditText
    private lateinit var playButton: Button

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        greetingText = findViewById(R.id.activity_main_greeting_txt)
        nameInput = findViewById(R.id.activity_main_name_input)
        playButton = findViewById(R.id.activity_main_play_btn)

        playButton.isClickable = false

        nameInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                playButton.isClickable = s?.length != 0
            }
        })

        playButton.setOnClickListener({
            user = User(nameInput.text.toString())
            val preferences: SharedPreferences = getPreferences(Context.MODE_PRIVATE)
            preferences.edit().putString("First name", user.firstName).apply()
            startActivityForResult(Intent(this, GameActivity::class.java), 1)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (Activity.RESULT_OK == resultCode && requestCode == 1) {
            //
        }
    }


}