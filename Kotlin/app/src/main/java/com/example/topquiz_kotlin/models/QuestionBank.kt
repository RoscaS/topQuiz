package com.example.topquiz_kotlin.models

class QuestionBank(var questions: MutableList<Question>) {
    var nextQuestionIdx: Int = 0

    init {
        questions.shuffle();
    }

    public fun getQuestion() : Question {
        if (nextQuestionIdx == questions.size) nextQuestionIdx = 0
        return questions.get(nextQuestionIdx++)
    }
}