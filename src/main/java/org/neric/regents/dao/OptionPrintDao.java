/**
 * @author      Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version     x.x.x
 * @since       Nov 2, 2016
 * @filename	OptionScan.java
 */
package org.neric.regents.dao;

import java.util.List;

import org.neric.regents.model.OptionPrint;

public interface OptionPrintDao
{
	OptionPrint findById(int id);
	
	List<OptionPrint> findAllOptionPrints();
	
	void save(OptionPrint optionPrint);
	
	void update(OptionPrint optionPrint);
	
	void delete(int id);
}
