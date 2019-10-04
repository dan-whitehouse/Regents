package org.neric.regents.converture;

import org.neric.regents.model.OptionScan;
import org.neric.regents.service.OptionScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.beans.PropertyEditorSupport;

@Component("optionScanEditor")
@Service("optionScanEditor")
public class OptionScanEditor extends PropertyEditorSupport {
    @Autowired
    OptionScanService optionScanService;

    @Override
    public void setAsText(String id) {
        OptionScan optionPrint = null;
        int opId = Integer.parseInt(id);

        for(OptionScan os : optionScanService.findAllOptionScans()) {
            if(os.getId() == opId) {
                optionPrint = new OptionScan(os.getId(), os.getName());
            }
        }
        this.setValue(optionPrint);
    }
}