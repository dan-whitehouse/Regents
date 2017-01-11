package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.Document;
import org.neric.regents.model.Exam;
import org.neric.regents.model.School;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("documentDAO")
public class DocumentDAOImpl extends AbstractDao<Integer, Document> implements DocumentDAO{

	public Document findById(int id) {
		return getByKey(id);
	}

//	public School findByType(String type) {
//		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.eq("type", type));
//		return (UserProfile) crit.uniqueResult();
//	}
	
	@SuppressWarnings("unchecked")
	public List<Document> findAll()
	{
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("id"));
		return (List<Document>)crit.list();
	}

	@Override
	public void deleteByDocumentId(int id)
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Document document = (Document)crit.uniqueResult();
		delete(document);
		
	}
	
}
