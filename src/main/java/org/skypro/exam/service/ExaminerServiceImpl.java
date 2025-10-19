package org.skypro.exam.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public List<Question> getQuestions(int amount) {
        List<Question> availableQuestions = questionService.getAllQuestions();

        int totalQuestions = availableQuestions.size();

        if (totalQuestions == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Нет доступных вопросов");
        }

        if (amount > totalQuestions) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Запрошено больше вопросов, чем есть в базе");
        }

        Set<Question> selectedQuestions = new HashSet<>();
        Random random = new Random();

        while (selectedQuestions.size() < amount) {
            Question question = questionService.getRandomQuestion();
            selectedQuestions.add(question);
        }

        return new ArrayList<>(selectedQuestions);
    }
}
