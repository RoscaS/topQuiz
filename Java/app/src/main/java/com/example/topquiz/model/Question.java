package com.example.topquiz.model;

import java.util.Arrays;
import java.util.List;

public class Question {

    private String question;
    private List<String> choices;
    private int answerIdx;

    public Question(String question, List<String> choices, int answerIdx) {
        setQuestion(question);
        setChoices(choices);
        setAnswerIdx(answerIdx);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        assert choices.size() > 1;
        this.choices = choices;
    }

    public int getAnswerIdx() {
        return answerIdx;
    }

    public void setAnswerIdx(int answerIdx) {
        assert Arrays.asList(new int[]{0, 1, 2, 3}).contains(answerIdx);
        this.answerIdx = answerIdx;
    }
}
