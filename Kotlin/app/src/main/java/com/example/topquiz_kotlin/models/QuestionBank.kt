package com.example.topquiz_kotlin.models
import com.example.topquiz_kotlin.public.questionList

class QuestionBank {
    private var nextQuestionIdx = 0
    private val questions = mutableListOf(*questionList)

    init {
        questions.shuffle()
    }

    fun getQuestion() : Question {
        if (nextQuestionIdx == questions.size) nextQuestionIdx = 0
        return questions[nextQuestionIdx++]
    }
}

