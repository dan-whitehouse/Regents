/**
 * @author      Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version     x.x.x
 * @since       Nov 2, 2016
 * @filename	OptionScanServiceImpl.java
 */
package org.neric.regents.service;

import java.util.List;

import org.neric.regents.dao.OptionPrintDao;
import org.neric.regents.model.OptionPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("optionPrintService")
@Transactional
public class OptionPrintServiceImpl implements OptionPrintService
{

	@Autowired
	private OptionPrintDao dao;
	
	public OptionPrint findById(int id)
	{
		return dao.findById(id);
	}

	public List<OptionPrint> findAllOptionPrints()
	{
		return dao.findAllOptionPrints();
	}

	public void save(OptionPrint optionPrint)
	{
		dao.save(optionPrint);
		
	}

	public void update(OptionPrint optionPrint)
	{
		OptionPrint entity = dao.findById(optionPrint.getId());
		if(entity != null)
		{
			entity.setName(optionPrint.getName());
		}
	}

	public void delete(int id)
	{
		dao.delete(id);
		
	}
}
