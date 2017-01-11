package org.neric.regents.service;

import java.util.List;

import org.neric.regents.dao.ExamDao;
import org.neric.regents.model.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("examService")
@Transactional
public class ExamServiceImpl implements ExamService{

	@Autowired
	private ExamDao dao;
	
	public Exam findById(int id) 
	{
		return dao.findById(id);
	}
	
	public Exam findByName(String examName) 
	{
		Exam exam = dao.findByName(examName);
		return exam;
	}
	
	public List<Exam> findAllExams() 
	{
		return dao.findAllExams();
	}

	public void saveExam(Exam exam) 
	{
		dao.save(exam);
	}

	public void updateExam(Exam exam)
	{
		Exam entity = dao.findById(exam.getId());
		if(entity != null)
		{
			entity.setCode(entity.getCode());
			entity.setExams(entity.getExams());
			entity.setName(entity.getName());
		}
	}

	public void deleteByExamName(String examName) 
	{
		dao.deleteByExamName(examName);
	}

	@Override
	public void deleteByExamId(int id)
	{
		dao.deleteByExamId(id);
	}
	
}
