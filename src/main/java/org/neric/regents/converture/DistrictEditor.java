package org.neric.regents.converture;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;
import org.neric.regents.model.District;
import org.neric.regents.model.OptionPrint;
import org.neric.regents.model.OptionScan;
import org.neric.regents.service.DistrictService;
import org.neric.regents.service.OptionPrintService;
import org.neric.regents.service.OptionPrintServiceImpl;
import org.neric.regents.service.OptionScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("districtEditor")
@Service("districtEditor")
public class DistrictEditor extends PropertyEditorSupport 
{	
	@Autowired
	DistrictService districtService;
	
	@Override
    public void setAsText(String id) 
    {    	
    	District district = null;
        Integer dId = Integer.parseInt(id);
        district = districtService.findById(dId);
        this.setValue(district);
    }
	
	
   /* @Override
    public void setAsText(String id) 
    {    	
    	District district = null;
        Integer dId = Integer.parseInt(id);

        for(District d :  districtService.findAllDistricts())
        {
        	if(d.getId().intValue() == dId.intValue())	
			{
        		district = new District(d.getId(), d.getName(), d.getBedsCode());
			}
        }
        this.setValue(district);
    }*/
}