package org.skypro.exam.controller;

import org.skypro.exam.service.ExaminerService;
import org.skypro.exam.service.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {

    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }


    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getQuestions(@RequestParam int amount) {
        List<Question> questions = examinerService.getQuestions(amount);
        return ResponseEntity.ok(questions);
    }
}
