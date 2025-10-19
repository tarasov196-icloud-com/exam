package org.skypro.exam.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        examinerService = new ExaminerServiceImpl(questionService);
    }

    @Test
    void getQuestions_ShouldReturnRequestedAmount_WhenEnoughQuestionsAvailable() {
        List<Question> questions = Arrays.asList(
                new Question("Q1", "A1"),
                new Question("Q2", "A2"),
                new Question("Q3", "A3")
        );
        when(questionService.getAllQuestions()).thenReturn(questions);

        int amount = 2;
        List<Question> result = examinerService.getQuestions(amount);

        assertNotNull(result);
        assertEquals(amount, result.size());
        assertTrue(questions.containsAll(result));
    }

    @Test
    void getQuestions_ShouldThrowException_WhenNoQuestionsAvailable() {
        when(questionService.getAllQuestions()).thenReturn(Collections.emptyList());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> examinerService.getQuestions(1));

        assertEquals("Нет доступных вопросов", exception.getReason());
    }

    @Test
    void getQuestions_ShouldThrowException_WhenRequestedMoreThanAvailable() {
        List<Question> questions = Arrays.asList(
                new Question("Q1", "A1")
        );
        when(questionService.getAllQuestions()).thenReturn(questions);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> examinerService.getQuestions(5));

        assertEquals("Запрошено больше вопросов, чем есть в базе", exception.getReason());
    }
}