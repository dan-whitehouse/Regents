/**
 * @author      Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version     x.x.x
 * @since       Nov 2, 2016
 * @filename	OptionScanService.java
 */
package org.neric.regents.service;

import java.util.List;

import org.neric.regents.model.Order;
import org.neric.regents.model.OrderForm;

public interface OrderFormService
{
	OrderForm findById(int id);
	
	OrderForm findByUUID(String uuid);
	
	List<OrderForm> findAllOrderForms();
	
	void saveOrderForm(OrderForm orderForm);
	
	void updateOrderForm(OrderForm orderForm);
	
	void deleteOrderForm(int id);
	
	void lockByOrderFormId(int id, Boolean locked);
	
	void hideByOrderFormId(int id, Boolean hidden);
	
	void lockByOrderFormUUID(String uuid, Boolean locked);
	
	void hideByOrderFormUUID(String uuid, Boolean hidden);
	
	void activateOrderFormUUID(String uuid, Boolean isActive);

	boolean hasActiveOrderForm();
}
