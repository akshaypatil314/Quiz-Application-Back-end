package com.learn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.entity.Questions;
import com.learn.entity.UserAnswers;
import com.learn.entity.UserAnswersDao;
import com.learn.entity.Users;
import com.learn.repository.QuestionRepository;
import com.learn.repository.UserAnswerRepository;
import com.learn.repository.UserRepository;
import com.learn.service.UserAnswerService;

@RestController
@RequestMapping("/api/user-answers")
@CrossOrigin(origins = "http://localhost:3000")
public class UserAnswerController {
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private UserAnswerRepository userAnswerRepository;
    
    @Autowired
    private UserAnswerService userAnswerService;
    
	
	@PostMapping
	public UserAnswers createUserAnswer(@RequestBody UserAnswersDao userAnswerDao)
	{
		Users user=userRepository.findById(userAnswerDao.getUserId()).orElseThrow(()-> new IllegalArgumentException("User not found"));
		Questions question=questionRepository.findById(userAnswerDao.getQuestionId()).orElseThrow(()-> new IllegalArgumentException("Question not found"));
		
		UserAnswers userAnswer=new UserAnswers();
		userAnswer.setUsers(user);
		userAnswer.setQuestions(question);
		userAnswer.setSelectedOption(userAnswerDao.getSelectedOption());
		
		return userAnswerRepository.save(userAnswer);
	}
	
	@GetMapping("/{id}")
    public UserAnswers getUserAnswerById(@PathVariable Long id) {
        return userAnswerService.getUserAnswerById(id);
    }

    @PutMapping("/{id}")
    public UserAnswers updateUserAnswer(@PathVariable Long id, @RequestBody UserAnswers userAnswer) {
        return userAnswerService.updateUserAnswer(id, userAnswer);
    }

    @DeleteMapping("/{id}")
    public void deleteUserAnswer(@PathVariable Long id) {
        userAnswerService.deleteUserAnswer(id);
    }

    @GetMapping
    public List<UserAnswers> getAllUserAnswers() {
        return userAnswerService.getAllUserAnswers();
    }

}
