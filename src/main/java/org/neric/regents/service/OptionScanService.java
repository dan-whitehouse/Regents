/**
 * @author      Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version     x.x.x
 * @since       Nov 2, 2016
 * @filename	OptionScanService.java
 */
package org.neric.regents.service;

import java.util.List;

import org.neric.regents.model.OptionScan;

public interface OptionScanService
{
	OptionScan findById(int id);
	
	OptionScan findByUUID(String uuid);
	
	List<OptionScan> findAllOptionScans();
	
	void save(OptionScan optionScan);
	
	void update(OptionScan optionScan);
	
	void delete(int id);
	
	void deleteByUUID(String uuid);
	
	void lockByOptionScanId(int id, Boolean isLocked);
	
	void lockByOptionScanUUID(String uuid, Boolean isLocked);
	
	void hideByOptionScanId(int id, Boolean isHidden);
	
	void hideByOptionScanUUID(String uuid, Boolean isHidden);

	List<OptionScan> findAllActivelOptionScans();	
}
