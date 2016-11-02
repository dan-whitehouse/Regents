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
	
	List<OptionScan> findAllOptionScans();
	
	void save(OptionScan optionScan);
	
	void update(OptionScan optionScan);
	
	void delete(int id);
}
