/**
 * @author      Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version     x.x.x
 * @since       Nov 2, 2016
 * @filename	OptionScan.java
 */
package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.OptionScan;

public interface OptionScanDao
{
	OptionScan findById(int id);
	
	OptionScan findByUUID(String uuid);
	
	List<OptionScan> findAllOptionScans();
	
	List<OptionScan> findAllActiveOptionScans();
	
	void save(OptionScan optionScan);
	
	void update(OptionScan optionScan);
	
	void delete(int id);

	void deleteByUUID(String uuid);

}
