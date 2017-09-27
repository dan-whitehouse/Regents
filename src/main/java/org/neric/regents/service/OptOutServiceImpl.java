package org.neric.regents.service;

import java.util.List;

import org.neric.regents.dao.OptOutDAO;
import org.neric.regents.model.OptOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("optOutService")
@Transactional
public class OptOutServiceImpl implements OptOutService
{

	@Autowired
	private OptOutDAO dao;
	
	public OptOut findById(int id)
	{
		return dao.findById(id);
	}
	
	public OptOut findByUUID(String uuid)
	{
		return dao.findByUUID(uuid);
	}

	public List<OptOut> findAllOptOuts()
	{
		return dao.findAllOptOuts();
	}
	
	public List<OptOut> findAllActiveOptOuts(int orderFormId)
	{
		return dao.findAllActiveOptOuts(orderFormId);
	}
	
	public void save(OptOut optOut)
	{
		dao.save(optOut);
		
	}

	public void deleteById(int id)
	{
		dao.deleteById(id);
	}
	
	public void deleteByUUID(String uuid)
	{
		dao.deleteByUUID(uuid);
	}
}
