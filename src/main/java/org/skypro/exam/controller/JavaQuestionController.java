package org.skypro.exam.controller;

import org.skypro.exam.service.Question;
import org.skypro.exam.service.QuestionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {

    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/add")
    public String addQuestion(@RequestParam String question, @RequestParam String answer) {
        Question newQuestion = new Question(question, answer);
        questionService.addQuestion("Question ", newQuestion);
        return "Question added successfully";
    }

    @GetMapping("/remove")
    public String removeQuestion(@RequestParam String question, @RequestParam String answer) {
        Question questionToRemove = new Question(question, answer);
        boolean removed = questionService.removeQuestion(questionToRemove);
        if (removed) {
            return "Question removed successfully";
        } else {
            return "Question not found";
        }
    }
    @GetMapping("/find")
    public Question findQuestion(@RequestParam String question, @RequestParam String answer) {
        Question foundQuestion = questionService.findQuestion(question, answer);
        if (foundQuestion != null) {
            return foundQuestion;
        } else {
            throw new RuntimeException("Question not found");
        }
    }
}