package org.neric.regents.task;

import org.neric.regents.model.OrderForm;
import org.neric.regents.service.OrderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderFormTask {
    @Autowired
    OrderFormService orderFormService;


    //@Scheduled(cron="0 59 23 1/1 * ?") //every day @ 11:59PM via http://www.cronmaker.com/
    @Scheduled(cron = "0 1 0 1/1 * ?") //every day @ 12:01AM via http://www.cronmaker.com/
    public void test() {
        for(OrderForm of : orderFormService.findAllOrderForms()) {
            if(of.isExpiredPeriod()) {
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