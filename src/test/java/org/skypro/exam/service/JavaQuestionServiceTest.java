package org.skypro.exam.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private JavaQuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = new JavaQuestionService();
    }

    @Test
    void addQuestion_ShouldAddQuestion_WhenQuestionIsNotNull() {
        Question question = new Question("Что такое JVM?", "Java Virtual Machine");
        questionService.addQuestion(question);
        assertTrue(questionService.getAllQuestions().contains(question));
    }

    @Test
    void addQuestion_ShouldNotAddNullQuestion() {
        questionService.addQuestion((Question) null);

        assertFalse(questionService.getAllQuestions().contains(null));
    }

    @Test
    void findQuestion_ShouldReturnQuestion_WhenQuestionExists() {
        Question q = questionService.findQuestion("Что такое JDK?", "Java Development Kit");
        assertNotNull(q);
        assertEquals("Что такое JDK?", q.getQuestion());
        assertEquals("Java Development Kit", q.getAnswer());
    }

    @Test
    void findQuestion_ShouldReturnNull_WhenQuestionDoesNotExist() {
        Question q = questionService.findQuestion("Несуществующий вопрос", "Ответ");
        assertNull(q);
    }

    @Test
    void getAllQuestions_ShouldReturnAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        assertNotNull(questions);

        assertEquals(3, questions.size());

        assertTrue(questions.stream()
                .anyMatch(q -> q.getQuestion().contains("Что такое JDK?")));
    }

    @Test
    void getRandomQuestion_ShouldReturnQuestion_WhenQuestionsExist() {
        Question q = questionService.getRandomQuestion();
        assertNotNull(q);
        assertTrue(questionService.getAllQuestions().contains(q));
    }

    @Test
    void getRandomQuestion_ShouldReturnNull_WhenNoQuestions() {
        for (Question q : questionService.getAllQuestions()) {
            questionService.removeQuestion(q);
        }
        Question q = questionService.getRandomQuestion();
        assertNull(q);
    }

    @Test
    void removeQuestion_ShouldReturnTrue_WhenQuestionExists() {
        Question q = questionService.getAllQuestions().get(0);
        boolean removed = questionService.removeQuestion(q);
        assertTrue(removed);
        assertFalse(questionService.getAllQuestions().contains(q));
    }

    @Test
    void removeQuestion_ShouldReturnFalse_WhenQuestionDoesNotExist() {
        Question q = new Question("Некоторые вопрос", "Некоторый ответ");
        boolean removed = questionService.removeQuestion(q);
        assertFalse(removed);
    }
}