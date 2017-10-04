package org.neric.regents.service;

import java.util.List;

import org.neric.regents.model.OptOut;

public interface OptOutService
{	
	OptOut findById(int id);
	
	OptOut findByUUID(String uuid);
	
	List<OptOut> findAllOptOuts();
	
	List<OptOut> findAllOptOutsByUsername(String username);
	
	List<OptOut> findAllActiveOptOuts(int orderFormId);
	
	void save(OptOut optOut);
	
	void deleteById(int id);

	void deleteByUUID(String uuid);
}
