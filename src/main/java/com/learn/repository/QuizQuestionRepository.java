package com.learn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.entity.QuizQuestion;
import com.learn.entity.Quiz;

public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long>{
	public List<QuizQuestion> findByQuiz(Quiz quiz);
}
