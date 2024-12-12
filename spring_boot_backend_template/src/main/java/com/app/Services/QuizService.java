package com.app.Services;

import java.io.InputStream;
import java.util.List;

import com.app.Entities.Quiz;

public interface QuizService {
	public List<Quiz> uploadQuiz(InputStream is) throws Exception;
	 public List<Quiz> getAllQuizzes();
}
