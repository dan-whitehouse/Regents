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
        this.setValue(optionPrint);
    }
}
