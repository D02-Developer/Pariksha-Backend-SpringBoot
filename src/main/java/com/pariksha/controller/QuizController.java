package com.pariksha.controller;

import com.pariksha.model.exam.Category;
import com.pariksha.model.exam.Quiz;
import com.pariksha.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuizService quizService;

    // Add quiz

    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    // Update quiz

    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {
        return  ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    // Get quizzes

    @GetMapping("/")
    public ResponseEntity quizzes() {
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    // Get single quiz

    @GetMapping("/{qid}")
    public Quiz quiz(@PathVariable("qid") Long qid) {
        return this.quizService.getQuiz(qid);
    }

    // Delete quiz

    @DeleteMapping("/{qid}")
    public void deleteQuiz(@PathVariable("qid") Long qid) {
        this.quizService.deleteQuiz(qid);
    }

    // Get specific quiz

    @GetMapping("category/{cid}")
    public List<Quiz> getQuizzesOfCategory(@PathVariable("cid") Long cid) {
        Category category = new Category();
        category.setCid(cid);
        return this.quizService.getQuizzesOfCategory(category);
    }

    // Get active quizzes

    @GetMapping("/active")
    public List<Quiz> getActiveQuizzes() {
        return this.quizService.getActiveQuizzes();
    }

    // Get active quizzes of category
    @GetMapping("/category/active/{cid}")
    public List<Quiz> getActiveQuizzesOfCategory(@PathVariable("cid") Long cid) {
        Category category = new Category();
        category.setCid(cid);
        return this.quizService.getActiveQuizzesOfCategory(category);
    }

}
