package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.OptOut;

public interface OptOutDAO {

	OptOut findById(int id);
	
	OptOut findByUUID(String uuid);
	
	List<OptOut> findAllOptOuts();
	
	List<OptOut> findAllActiveOptOuts(int id);
	
	void save(OptOut optOut);
	
	void deleteById(int id);

	void deleteByUUID(String uuid);
}

