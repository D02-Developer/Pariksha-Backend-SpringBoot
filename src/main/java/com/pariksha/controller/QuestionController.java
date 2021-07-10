package com.pariksha.controller;

import com.pariksha.model.exam.Question;
import com.pariksha.model.exam.Quiz;
import com.pariksha.service.QuestionService;
import com.pariksha.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    // Add question

    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    // Update question

    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    // Get questions of any quiz

    @GetMapping("/quiz/{qid}")
    public ResponseEntity getQuestionsOfQuiz(@PathVariable("qid") Long qid) {

        // Give all the questions in particular quiz
//        Quiz quiz = new Quiz();
//        quiz.setqId(qid);
//        Set<Question> questions = this.questionService.getQuestionsOfQuiz(quiz);
//        return ResponseEntity.ok(questions);

        Quiz quiz = this.quizService.getQuiz(qid);
        Set<Question> questions = quiz.getQuestions();
        List list = new ArrayList(questions);

        if (list.size() > Integer.parseInt(quiz.getNumOfQues())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumOfQues() + 1));
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);

    }

    // Give all the questions in particular quiz Admin side

    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid) {

        Quiz quiz = new Quiz();
        quiz.setqId(qid);
        Set<Question> questions = this.questionService.getQuestionsOfQuiz(quiz);
        return ResponseEntity.ok(questions);

//        Quiz quiz = this.quizService.getQuiz(qid);
//        Set<Question> questions = quiz.getQuestions();
//        List list = new ArrayList(questions);
//
//        if (list.size() > Integer.parseInt(quiz.getNumOfQues())) {
//            list = list.subList(0, Integer.parseInt(quiz.getNumOfQues() + 1));
//        }
//        Collections.shuffle(list);
//        return ResponseEntity.ok(list);

    }

    // Get single question

    @GetMapping("/{quesId}")
    public Question getQuestion(@PathVariable("quesId") Long quesId) {
        return this.questionService.getQuestion(quesId);
    }

    // Delete question

    @DeleteMapping("/{quesId}")
    public void deleteQuestion(@PathVariable("quesId") Long quesId) {
        this.questionService.deleteQuestion(quesId);
    }

    // Evaluate quiz

    @PostMapping("/eval-quiz")
    public ResponseEntity evalQuiz(@RequestBody List<Question> questions) {
        System.out.println(questions);
        double marks_got = 0;
        int correct_ans = 0;
        int attempted = 0;
        for(Question q: questions) {
//            System.out.println(q.getGivenAns());
            // single question
            Question question = this.questionService.get(q.getQuesId());
            if(question.getAns().equals(q.getGivenAns())) {
                // correct
                correct_ans++;
                double mark_single = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / questions.size();
                    marks_got += mark_single;
            }
            if(q.getGivenAns() != null) {
                attempted++;
            }
        }

        Map<String, Object> map = Map.of("marks", marks_got, "correct", correct_ans, "attempted", attempted);
        return ResponseEntity.ok(map);
    }
}
