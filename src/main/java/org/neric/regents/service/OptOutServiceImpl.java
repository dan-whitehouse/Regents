package org.neric.regents.service;

import java.util.List;

import org.neric.regents.dao.OptOutDAO;
import org.neric.regents.model.OptOut;
import org.neric.regents.model.OrderForm;
import org.neric.regents.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("optOutService")
@Transactional
public class OptOutServiceImpl implements OptOutService
{

	@Autowired
	private OptOutDAO dao;
	
	@Override
	public OptOut findById(int id)
	{
		return dao.findById(id);
	}
	
	@Override
	public OptOut findByUUID(String uuid)
	{
		return dao.findByUUID(uuid);
	}

	@Override
	public List<OptOut> findAllOptOuts()
	{
		return dao.findAllOptOuts();
	}
	
	@Override
	public List<OptOut> findAllOptOutsByUsername(String username)
	{
		return dao.findAllOptOutsByUsername(username);
	}
	
	@Override
	public List<OptOut> findAllActiveOptOuts(int orderFormId)
	{
		return dao.findAllActiveOptOuts(orderFormId);
	}
	
	@Override
	public List<OptOut> findAllOptOutsByUserAndOrderForm(User user, OrderForm orderForm)
	{
		return dao.findAllOptOutsByUserAndOrderForm(user, orderForm);
	}
	
	@Override
	public void save(OptOut optOut)
	{
		dao.save(optOut);
	}

	@Override
	public void deleteById(int id)
	{
		dao.deleteById(id);
	}
	
	@Override
	public void deleteByUUID(String uuid)
	{
		dao.deleteByUUID(uuid);
	}
}
