package com.learn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learn.entity.Questions;
import com.learn.repository.QuestionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	
	public QuestionService(QuestionRepository questionRepository) {
		super();
		this.questionRepository = questionRepository;
	}

	public ResponseEntity<List<Questions>> getAllQuestions()
	{
		try {
			return new ResponseEntity<>(this.questionRepository.findAll(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Questions> createQuestion(Questions question) {
		return new ResponseEntity<>(this.questionRepository.save(question),HttpStatus.CREATED);
	}

	public Questions getQuestionById(Long id) {
		return this.questionRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Question not Found"));
	}

	public Questions updateQuestion(Long id,Questions question) {
		Questions a=getQuestionById(id);
		a.setQuestionText(question.getQuestionText());
		a.setOption1(question.getOption1());
		a.setOption2(question.getOption2());
		a.setOption3(question.getOption3());
		a.setOption4(question.getOption4());
		a.setCorrectOption(question.getCorrectOption());
		a.setTechnology(question.getTechnology());
		return this.questionRepository.save(a);
	}

	public void deleteQuestion(Long id) {
		Questions a=getQuestionById(id);
		this.questionRepository.delete(a);
	}

}
