package com.pariksha.repository;

import com.pariksha.model.exam.Question;
import com.pariksha.model.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionRepo extends JpaRepository<Question, Long> {
    Set<Question> findByQuiz(Quiz quiz);
}
