package com.example.topquiz.model;

import java.util.Collections;
import java.util.List;

public class QuestionBank {
    private List<Question> questions;
    private int nextQuestionIdx;

    public QuestionBank(List<Question> questions) {
        this.questions = questions;
        Collections.shuffle(this.questions);
        nextQuestionIdx = 0;
    }

    public Question getQuestion() {
        if (nextQuestionIdx == questions.size()) {
            nextQuestionIdx = 0;
        }
        return questions.get(nextQuestionIdx++);
    }
}
