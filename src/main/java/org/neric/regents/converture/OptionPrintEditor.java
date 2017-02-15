package org.neric.regents.converture;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;
import org.neric.regents.model.OptionPrint;
import org.neric.regents.service.OptionPrintService;
import org.neric.regents.service.OptionPrintServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("optionPrintEditor")
@Service("optionPrintEditor")
public class OptionPrintEditor extends PropertyEditorSupport 
{	
	@Autowired
	OptionPrintService optionPrintService;
	
    @Override
    public void setAsText(String id) 
    {    	
        OptionPrint optionPrint = null;
        Integer opId = Integer.parseInt(id);

        for(OptionPrint op :  optionPrintService.findAllOptionPrints())
        {
        	if(op.getId().intValue() == opId.intValue())	
			{
        		optionPrint = new OptionPrint(op.getId(), op.getName());
			}
        }
        System.err.println(optionPrint);
        this.setValue(optionPrint);
    }
}




//This will be called when user HTTP Post to server a field bound to DepartmentVO
//@Override
//public void setAsText(String id) 
//{
//  OptionPrint d;
//  switch(Integer.parseInt(id))
//  {
//      case 1: d = new OptionPrint(1,  "Human Resource"); break;
//      case 2: d = new OptionPrint(2,  "Finance"); break;
//      case 3: d = new OptionPrint(3,  "Information Technology"); break;
//      default: d = null;
//  }
//  this.setValue(d);
//}