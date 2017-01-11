package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.Exam;


public interface ExamDao {

	Exam findById(int id);
	
	Exam findByName(String examName);
	
	List<Exam> findAllExams();
	
	void save(Exam exam);
	
	void deleteByExamName(String examName);
	
	void deleteByExamId(int id);

}

