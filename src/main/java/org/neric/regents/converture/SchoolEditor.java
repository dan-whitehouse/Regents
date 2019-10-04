package org.neric.regents.converture;

import org.neric.regents.model.School;
import org.neric.regents.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.beans.PropertyEditorSupport;

@Component("schoolEditor")
@Service("schoolEditor")
public class SchoolEditor extends PropertyEditorSupport {
    @Autowired
    SchoolService schoolService;

    @Override
    public void setAsText(String id) {
        School school;
        int sId = Integer.parseInt(id);
        school = schoolService.findById(sId);
        this.setValue(school);
    }
	
    /*@Override
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
    }*/
}