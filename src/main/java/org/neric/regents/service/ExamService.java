package org.neric.regents.service;

import java.util.List;

import org.neric.regents.model.Exam;


public interface ExamService {
	
	Exam findById(int id);
	
	Exam findByName(String examName);
	
	List<Exam> findAllExams();
	
	void saveExam(Exam exam);
	
	void updateExam(Exam exam);
	
	void deleteByExam(String examName);

	 

}