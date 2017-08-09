package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.Exam;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;



@Repository("examDao")
public class ExamDaoImpl extends AbstractDao<Integer, Exam> implements ExamDao {

	static final Logger logger = LoggerFactory.getLogger(ExamDaoImpl.class);
	
	public Exam findById(int id) 
	{
		Exam exam = getByKey(id);
		if(exam!=null){
			Hibernate.initialize(exam.getId());
		}
		return exam;
	}

	public Exam findByName(String examName) 
	{
		logger.info("Exam Name : {}", examName);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("name", examName));
		Exam exam = (Exam)crit.uniqueResult();
		if(exam != null)
		{
			Hibernate.initialize(exam.getExams());
		}
		return exam;
	}

	@SuppressWarnings("unchecked")
	public List<Exam> findAllExams()
	{
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Exam> exams = (List<Exam>) criteria.list();
		return exams;
	}
	
	@SuppressWarnings("unchecked")
	public List<Exam> findAllActiveExams()
	{
		Criteria criteria = getSession().createCriteria(Exam.class, "e");
		criteria.createAlias("e.orderFormExams", "ofe");
		criteria.createAlias("ofe.orderForm", "of");
		criteria.add(Restrictions.eq("of.active", true));
		criteria.add(Restrictions.eq("e.visible", true));
		criteria.addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Exam> exams = (List<Exam>) criteria.list();
		return exams;
	}
	

	public void save(Exam exam) 
	{
		persist(exam);
	}

	public void deleteByExamName(String examName) 
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("name", examName));
		Exam exam = (Exam)crit.uniqueResult();
		delete(exam);
	}
	
	public void deleteByExamId(int id) 
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Exam exam = (Exam)crit.uniqueResult();
		delete(exam);
	}

}
