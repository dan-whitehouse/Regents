package org.neric.regents.service;

import java.util.List;

import org.neric.regents.model.Exam;


public interface ExamService {
	
	Exam findById(int id);
	
	Exam findByName(String examName);
	
	List<Exam> findAllExams();
	
	List<Exam> findAllActiveExams();
	
	void saveExam(Exam exam);
	
	void updateExam(Exam exam);
	
	void deleteByExamName(String examName);
	
	void deleteByExamId(int id);
	
	void lockByExamId(int id, Boolean locked);
	
	void hideByExamId(int id, Boolean hidden);

}