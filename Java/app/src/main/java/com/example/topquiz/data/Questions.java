package com.example.topquiz.data;

import com.example.topquiz.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Questions {

    public static final List<Question> QUESTIONS = new ArrayList<>(Arrays.asList(
            new Question(
                    "Who is the creator of Android?",
                    Arrays.asList("Andy Rubin", "Steve Wozniak", "Dave Rubin", "Jake Wharton"),
                    0
            ),
            new Question(
                    "When did the first man land on the moon?",
                    Arrays.asList("1958", "1962", "1967", "1969"),
                    3
            ),
            new Question(
                    "What is the house number of the Simpson family?",
                    Arrays.asList("42", "101", "666", "742"),
                    3
            )
    ));
}
