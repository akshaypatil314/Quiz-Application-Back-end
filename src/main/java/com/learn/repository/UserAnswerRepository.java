package com.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.entity.UserAnswers;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswers,Long> {

}
