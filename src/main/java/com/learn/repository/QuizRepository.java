package com.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.learn.entity.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long>{

}
