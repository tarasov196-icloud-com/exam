package org.skypro.exam.service;

import java.util.List;


public interface QuestionService {


    void addQuestion(String question, Question answer);
    void addQuestion(Question question);

    Question findQuestion(String question, String answer);

    List<Question> getAllQuestions();


    Question getRandomQuestion();

    boolean removeQuestion(Question question);
}