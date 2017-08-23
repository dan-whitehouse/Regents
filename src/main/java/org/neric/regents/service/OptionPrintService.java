/**
 * @author      Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version     x.x.x
 * @since       Nov 2, 2016
 * @filename	OptionScanService.java
 */
package org.neric.regents.service;

import java.util.List;

import org.neric.regents.model.OptionPrint;

public interface OptionPrintService
{
	OptionPrint findById(int id);
	
	OptionPrint findByUUID(String uuid);
	
	List<OptionPrint> findAllOptionPrints();
	
	List<OptionPrint> findAllActiveOptionPrints();
	
	void save(OptionPrint optionPrint);
	
	void update(OptionPrint optionPrint);
	
	void delete(int id);
	
	void deleteByUUID(String uuid);
	
	void lockByOptionPrintId(int id, Boolean isLocked);
	
	void lockByOptionPrintUUID(String uuid, boolean isLocked);
	
	void hideByOptionPrintId(int id, Boolean isHidden);
	
	void hideByOptionPrintUUID(String uuid, boolean isHidden);
}
