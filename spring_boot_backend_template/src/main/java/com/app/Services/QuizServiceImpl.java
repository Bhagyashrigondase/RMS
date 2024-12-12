package com.app.Services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Entities.Quiz;
import com.app.Repositories.QuizRepository;


@Service
public class QuizServiceImpl implements QuizService {

	
	@Autowired
    private QuizRepository quizRepository;

    public List<Quiz> uploadQuiz(InputStream is) throws Exception {
        List<Quiz> quizzes = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header row
            Quiz quiz = new Quiz();
            quiz.setQuestion(row.getCell(0).getStringCellValue());
            quiz.setOptionA(row.getCell(1).getStringCellValue());
            quiz.setOptionB(row.getCell(2).getStringCellValue());
            quiz.setOptionC(row.getCell(3).getStringCellValue());
            quiz.setOptionD(row.getCell(4).getStringCellValue());
            quiz.setCorrectAnswer(row.getCell(5).getStringCellValue());
            quizzes.add(quiz);
        }
        quizRepository.saveAll(quizzes);
        return quizzes;
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }
}
