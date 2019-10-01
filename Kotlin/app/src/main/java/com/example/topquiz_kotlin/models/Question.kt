package com.example.topquiz_kotlin.models

data class Question(var question:String,
                    var choices:MutableList<String>,
                    var answerIdx: Int)