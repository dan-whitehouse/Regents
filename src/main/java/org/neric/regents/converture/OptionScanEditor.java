package org.neric.regents.converture;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;
import org.neric.regents.model.OptionPrint;
import org.neric.regents.model.OptionScan;
import org.neric.regents.service.OptionPrintService;
import org.neric.regents.service.OptionPrintServiceImpl;
import org.neric.regents.service.OptionScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("optionScanEditor")
@Service("optionScanEditor")
public class OptionScanEditor extends PropertyEditorSupport 
{	
	@Autowired
	OptionScanService optionScanService;
	
    @Override
    public void setAsText(String id) 
    {    	
        OptionScan optionPrint = null;
        Integer opId = Integer.parseInt(id);

        for(OptionScan os :  optionScanService.findAllOptionScans())
        {
        	if(os.getId().intValue() == opId.intValue())	
			{
        		optionPrint = new OptionScan(os.getId(), os.getName());
			}
        }
        this.setValue(optionPrint);
    }
}