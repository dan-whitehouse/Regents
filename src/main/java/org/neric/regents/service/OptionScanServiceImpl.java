/**
 * @author      Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version     x.x.x
 * @since       Nov 2, 2016
 * @filename	OptionScanServiceImpl.java
 */
package org.neric.regents.service;

import java.util.List;

import org.neric.regents.dao.OptionScanDao;
import org.neric.regents.model.OptionScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("optionScanService")
@Transactional
public class OptionScanServiceImpl implements OptionScanService
{

	@Autowired
	private OptionScanDao dao;
	
	public OptionScan findById(int id)
	{
		return dao.findById(id);
	}

	public List<OptionScan> findAllOptionScans()
	{
		return dao.findAllOptionScans();
	}

	public void save(OptionScan optionScan)
	{
		dao.save(optionScan);
		
	}

	public void update(OptionScan optionScan)
	{
		OptionScan entity = dao.findById(optionScan.getId());
		if(entity != null)
		{
			entity.setName(optionScan.getName());
		}
	}

	public void delete(int id)
	{
		dao.delete(id);
		
	}
}
