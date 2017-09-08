package org.neric.regents.task;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.neric.regents.model.OrderForm;
import org.neric.regents.service.OrderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderFormTask 
{
	@Autowired
	OrderFormService orderFormService;
	
	
	@Scheduled(cron="0 59 23 1/1 * ?") //every day @ 11:59PM via http://www.cronmaker.com/
    public void test() 
    {
    	Date now = new Date();
    	for(OrderForm of : orderFormService.findAllOrderForms())
    	{
    		if(of.getEndDate().after(now));
    		{
    			of.setActive(false);
    			of.setLocked(true);
    			of.setVisible(false);
    			orderFormService.updateOrderForm(of);
    		}
    	}
    }
	
    /*@Scheduled(fixedRate = 20000) //20 seconds
    public void test2() 
    {
    	
    	for(OrderForm of : orderFormService.findAllOrderForms())
    	{
    		if(!isBetween(of.getStartDate(), of.getEndDate()));
    		{
    			of.setActive(false);
    			of.setLocked(true);
    			of.setVisible(false);
    			orderFormService.updateOrderForm(of);
    		}
    	}
    }*/
    
}