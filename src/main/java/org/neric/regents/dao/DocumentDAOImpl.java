package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.Document;
import org.neric.regents.model.Exam;
import org.neric.regents.model.School;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("documentDAO")
public class DocumentDAOImpl extends AbstractDao<Integer, Document> implements DocumentDAO{

	
	public Document findById(int id) 
	{
		Document document = getByKey(id);
		if(document!=null){
			Hibernate.initialize(document.getId());
		}
		return document;
	}

	
	@SuppressWarnings("unchecked")
	public List<Document> findAll()
	{
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("id"));
		return (List<Document>)crit.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Document> findAllActiveDocuments()
	{
		Criteria criteria = getSession().createCriteria(Document.class, "d");
		criteria.createAlias("d.orderFormDocuments", "ofd");
		criteria.createAlias("ofd.orderForm", "of");
		criteria.add(Restrictions.eq("of.active", true));
		criteria.addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Document> documents = (List<Document>) criteria.list();
		
		return documents;
	}
	

	@Override
	public void deleteByDocumentId(int id)
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Document document = (Document)crit.uniqueResult();
		delete(document);
		
	}

	@Override
	public void save(Document document)
	{
		persist(document);
	}
}
