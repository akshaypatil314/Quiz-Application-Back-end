package com.learn.repository;

import org.springframework.stereotype.Repository;

import com.learn.entity.Quiz;
import com.learn.entity.Questions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface QuestionRepository extends JpaRepository<Questions,Long> {

	@Query(value="SELECT * FROM questions q where q.technology=:technology",nativeQuery=true)
	List<Questions> findQuestionByTechnology(String technology);

}
