package org.neric.regents.converture;

import org.neric.regents.model.OptionPrint;
import org.neric.regents.service.OptionPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.beans.PropertyEditorSupport;

@Component("optionPrintEditor")
@Service("optionPrintEditor")
public class OptionPrintEditor extends PropertyEditorSupport {
    @Autowired
    OptionPrintService optionPrintService;

    @Override
    public void setAsText(String id) {
        OptionPrint optionPrint = null;
        int opId = Integer.parseInt(id);

        for(OptionPrint op : optionPrintService.findAllOptionPrints()) {
            if(op.getId() == opId) {
                optionPrint = new OptionPrint(op.getId(), op.getName());
            }
        }
        this.setValue(optionPrint);
    }
}
