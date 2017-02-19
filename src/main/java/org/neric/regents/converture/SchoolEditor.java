package org.neric.regents.converture;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;
import org.neric.regents.model.District;
import org.neric.regents.model.OptionPrint;
import org.neric.regents.model.OptionScan;
import org.neric.regents.model.School;
import org.neric.regents.service.DistrictService;
import org.neric.regents.service.OptionPrintService;
import org.neric.regents.service.OptionPrintServiceImpl;
import org.neric.regents.service.OptionScanService;
import org.neric.regents.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("schoolEditor")
@Service("schoolEditor")
public class SchoolEditor extends PropertyEditorSupport 
{	
	@Autowired
	SchoolService schoolService;
	
    @Override
    public void setAsText(String id) 
    {    	
    	School school = null;
        Integer sId = Integer.parseInt(id);

        for(School sch :  schoolService.findAll())
        {
        	if(sch.getId().intValue() == sId.intValue())	
			{
        		school = new School(sch.getId(), sch.getDistrict(), sch.getName());
			}
        }
        this.setValue(school);
    }
}